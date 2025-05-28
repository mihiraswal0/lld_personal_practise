package com.lld.practise.urlShortnerTest.controller;

import com.lld.practise.systemDesign.urlShortner.repository.AvailableIdRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class UlrShortnerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AvailableIdRepository repository;

    @Test
    void shouldCreateIdsAndReturnThemAsJson() throws Exception {
        MvcResult result=mockMvc.perform(post("/api/v1/url_shortener/refill")
                        .param("count", "2"))
                .andExpect(status().isOk()).andReturn();

        String output=result.getResponse().getContentAsString();
        System.out.println(output);

    }
}
