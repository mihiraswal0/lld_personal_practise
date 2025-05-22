package com.lld.practise.urlShortnerTest.service;

import com.lld.practise.systemDesign.urlShortner.repository.AvailableIdRepository;
import com.lld.practise.systemDesign.urlShortner.service.RefillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RefillServiceTest {

    @Mock
    private AvailableIdRepository availableIdRepository;

    @InjectMocks
    private RefillService refillService;

    @Test
    void refillDbWithZeroMaxId(){
        when(availableIdRepository.findTopUsedId()).thenReturn(Optional.empty());
        Map<String,String> result=refillService.refillDb(100);

        assertEquals(100, result.size());
        System.out.println(result.toString());

    }

    void refillDBWithValidMaxId(){

    }



}
