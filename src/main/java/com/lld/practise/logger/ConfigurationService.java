package com.lld.practise.logger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConfigurationService {
    private final LogLevel logLevel;
    private  List<IAppender> appenderList;

    public ConfigurationService(LogLevel logLevel){
        this.logLevel=logLevel;
        appenderList =new ArrayList<>();
    }
    public void addApender(IAppender appender){
        this.appenderList.add(appender);
    }

    public boolean filterLog(ILog log)
    {
        return logLevel.ordinal()>log.getLogType().ordinal();
    }

}
