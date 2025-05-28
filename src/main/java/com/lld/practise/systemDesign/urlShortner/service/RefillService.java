package com.lld.practise.systemDesign.urlShortner.service;

import com.lld.practise.systemDesign.urlShortner.entity.AvailableId;
import com.lld.practise.systemDesign.urlShortner.entity.UrlDetails;
import com.lld.practise.systemDesign.urlShortner.enums.AvailabilityStatus;
import com.lld.practise.systemDesign.urlShortner.repository.AvailableIdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lld.practise.systemDesign.urlShortner.util.Constants.BASE64_ALPHABET;
import static com.lld.practise.systemDesign.urlShortner.util.Constants.REDIS_QUEUE_NAME;

@Service
public class RefillService {

    private static final int MAX_URL_LENGTH = 6;
    private static final int REDIS_MAX_SIZE=10;

    @Autowired
    private AvailableIdRepository availableIdRepository;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    private final static Logger logger= LoggerFactory.getLogger(RefillService.class);

    /**Refills a set of new unique Ids int available_ids table
     *  @param count: number of new ids needed to be inserted
     *  @return  status of update
     *
     */
    public Map<String, String> refillDb(int count) {
        if(count<0)
            throw new IllegalArgumentException("Count of ids to be added should be positive");
        logger.info("Starting refilling of db with count {}", count);
        Optional<Long> maxUsedId = availableIdRepository.findTopUsedId();
        long startId = maxUsedId.map(id -> id + 1).orElse(1L);

        Map<String, String> idMap = new HashMap<>();

        List<AvailableId> idsToSave = new ArrayList<>();

        while (count-- > 0) {
            String base64 = convertToBase64(startId);

            AvailableId availableId = new AvailableId(startId, base64, AvailabilityStatus.UNUSED);
            idsToSave.add(availableId);
            idMap.put(String.valueOf(startId), base64);
            startId++;
        }
        logger.info("Saved new available ids to database {}",idsToSave);
        availableIdRepository.saveAll(idsToSave);
        return idMap;
    }

    private String convertToBase64(long startId) {
    StringBuilder base64No= new StringBuilder();
    while (startId>0){
        long rem=startId%64;
        base64No.append(BASE64_ALPHABET.charAt((int)rem));
        startId/=64;
    }
    while (base64No.length()<MAX_URL_LENGTH)
    {
        base64No.append('0');
    }
    return String.valueOf(base64No.reverse());
    }

    public String refillRedis(){
    long requiredKeys=REDIS_MAX_SIZE-redisTemplate.opsForList().size(REDIS_QUEUE_NAME);
    if(requiredKeys==0){
        return "No refill needed";
    }
    List<AvailableId> unusedIds=availableIdRepository.findUnusedAvailablityStatus(AvailabilityStatus.UNUSED,(int)requiredKeys);
    if(unusedIds.isEmpty()){
        return "Free keys not available in db";
    }
    List<String> unusedBase64Ids=unusedIds.stream().map(AvailableId::getBase64Id).toList();
    redisTemplate.opsForList().rightPushAll(REDIS_QUEUE_NAME,unusedBase64Ids);
    unusedIds.forEach(id->id.setAvailabilityStatus(AvailabilityStatus.LOADED));
    availableIdRepository.saveAll(unusedIds);
    return ("Following ids are loaded "+unusedIds.toString());

    }
}
