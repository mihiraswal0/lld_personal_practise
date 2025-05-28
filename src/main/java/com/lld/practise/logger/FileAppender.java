package com.lld.practise.logger;

import lombok.*;

import java.io.FileWriter;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class FileAppender implements IAppender {
    private String path;

    @Override
    public void append(String message) {
        try (FileWriter fileWriter = new FileWriter(path, true)) { // true => append mode
            fileWriter.write(message + "\n"); // add newline after each message
            System.out.println("Message written to file.");
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
