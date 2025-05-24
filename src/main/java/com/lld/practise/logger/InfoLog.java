package com.lld.practise.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class InfoLog implements ILog {
    private final LogLevel logType;
    private final String message;

    public InfoLog(LogLevel logType,String message){
        this.logType=logType;
        this.message=message;

    }

}
