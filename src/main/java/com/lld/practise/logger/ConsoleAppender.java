package com.lld.practise.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ConsoleAppender implements IAppender  {

    public void append(String message){
        System.out.println(message);
    }
}
