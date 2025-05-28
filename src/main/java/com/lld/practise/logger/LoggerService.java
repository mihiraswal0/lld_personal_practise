package com.lld.practise.logger;

import lombok.AllArgsConstructor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@AllArgsConstructor

public class LoggerService {
    private final ConfigurationService configurationService;
    private final Queue<String> queue;
    private final ExecutorService executorService;
    public LoggerService(ConfigurationService configurationService){
        this.configurationService=configurationService;
        queue=new ArrayDeque<>();
        executorService=Executors.newSingleThreadExecutor();
        executorService.submit(this::logProcess);

    }
    public void info(String message){
        try {
            ILog log = LogFactory.createLog(LogLevel.INFO, message);
            String logMessage = LogFormat.getLog(log);
            queue.add(logMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void error(String message){
        ILog log=LogFactory.createLog(LogLevel.ERROR,message);

    }

    public void warn(String message){
        ILog log=LogFactory.createLog(LogLevel.WARN,message);

    }

    public void logProcess ()  {
        while (true) {
            try {
                if (queue.isEmpty())
                    wait();
                String log = queue.poll();
                List<IAppender> appenderList = configurationService.getAppenderList();
                appenderList.forEach(appender -> appender.append(log));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
