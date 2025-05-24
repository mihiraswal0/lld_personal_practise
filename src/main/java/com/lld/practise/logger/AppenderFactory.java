package com.lld.practise.logger;

public class AppenderFactory {
    public static IAppender createAppender(AppenderType appenderType){
        return switch (appenderType){
            case FILE -> new FileAppender();
            case CONSOLE -> new ConsoleAppender();
        };
    }
}
