package com.lld.practise.ChatApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/newuser")
    public ResponseEntity<?> createUser(@RequestBody String createUser) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        User newUser=objectMapper.readValue(createUser,User.class);
        System.out.println(newUser);
        userRepository.insertUser(newUser);

        return ResponseEntity.status(200).body("User added");

    }

    @PostMapping("/message")
    public ResponseEntity<?>sendMessage(@RequestParam("sender") String sender,@RequestParam("reciever") String reciever,@RequestParam("message") String message)
    {
        String senderFileName=userRepository.getFileName(sender);
        String receverFileName= userRepository.getFileName(reciever);
        System.out.println(senderFileName+" "+receverFileName);
        
        return ResponseEntity.status(200).body("Message send");
    }
}
