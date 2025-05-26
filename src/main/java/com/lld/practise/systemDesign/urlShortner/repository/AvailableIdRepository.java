package com.lld.practise.systemDesign.urlShortner.repository;

import com.lld.practise.systemDesign.urlShortner.entity.AvailableId;
import com.lld.practise.systemDesign.urlShortner.entity.UrlDetails;
import com.lld.practise.systemDesign.urlShortner.enums.AvailabilityStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableIdRepository extends JpaRepository<AvailableId, Long> {

    @Query("SELECT MAX(a.id) FROM AvailableId a")
    public Optional<Long> findTopUsedId();

    @Query(value = "SELECT * from available_ids WHERE status= :status LIMIT :limit",nativeQuery = true)
    public List<AvailableId> findUnusedAvailablityStatus(@Param("status")AvailabilityStatus status,@Param("limit") int limit);
}
