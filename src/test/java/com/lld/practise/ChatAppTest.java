package com.lld.practise;

import com.lld.practise.ChatApp.User;
import com.lld.practise.ChatApp.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ChatAppTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository; // or any other beans needed

    @BeforeEach // Use BeforeEach instead of BeforeAll for transactional tests
    public void initializeTest() {
        // Insert test data in DB, like test users or messages
        User user = new User("john","file.txt");

        userRepository.insertUser(user); // using your repository method
    }

    @Test
    public void testCreateUser() throws Exception {
        // Build and send a POST /api/chat/newuser with JSON body
        MvcResult mvcResult = mockMvc.perform(
                        post("/api/chat/newuser")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"userName\":\"alice\",\"fileName\":\"file1.txt\"}")
                )
                .andExpect(status().isOk())   // assert that status is 200 OK
                .andReturn();                 // return the MvcResult to inspect response

        // Now you can grab and print the response body:
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println("Response payload: " + responseBody);

    }




}
