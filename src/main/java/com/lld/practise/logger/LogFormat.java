package com.lld.practise.logger;

import java.time.LocalDateTime;

public class LogFormat {

    public static String getLog(ILog log){
        return  String.format("[%s] [%s] [%s] [%s]", LocalDateTime.now(),log.getLogType(),Thread.currentThread().getName(),log.getMessage());
    }
}
