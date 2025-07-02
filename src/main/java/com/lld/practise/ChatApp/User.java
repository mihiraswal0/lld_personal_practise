package com.lld.practise.ChatApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "file_name")
    private String fileName;
}
