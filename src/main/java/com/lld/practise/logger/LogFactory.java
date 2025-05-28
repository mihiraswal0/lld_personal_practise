package com.lld.practise.logger;

public class LogFactory {
    public static ILog createLog(LogLevel logLevel,String message){
       return switch (logLevel){
            case INFO -> new InfoLog(logLevel,message);
            case DEBUG -> new DebugLog(logLevel,message);
            case ERROR -> new ErrorLog(logLevel,message);
            default -> throw new RuntimeException("Log Level did not matched");
        };
    }
}
