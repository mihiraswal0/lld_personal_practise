package com.lld.practise.logger;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@AllArgsConstructor

public class LoggerServiceV2 {
    private final ConfigurationService configurationService;
    private final Queue<String> queue;
    private final ExecutorService executorService;
    @SneakyThrows
    public LoggerServiceV2(ConfigurationService configurationService){
        this.configurationService=configurationService;
        queue=new ArrayDeque<>();
        executorService=Executors.newSingleThreadExecutor();
        executorService.submit(this::logProcess);

    }
    public void info(String message){
        try {
            ILog log = LogFactory.createLog(LogLevel.INFO, message);
            String logMessage = LogFormat.getLog(log);
            synchronized (queue) {
                queue.add(logMessage);
                queue.notifyAll();
            }
        }catch (Exception e){
            System.out.println("exception"+e.getMessage());
        }
    }
    public void error(String message){
        ILog log=LogFactory.createLog(LogLevel.ERROR,message);

    }

    public void warn(String message){
        ILog log=LogFactory.createLog(LogLevel.WARN,message);

    }

    public void logProcess () {
        while (true) {

            try {
                String log;
                synchronized (queue) {
                    if (queue.isEmpty())
                        queue.wait();
                    log = queue.poll();
                }
                System.out.println("logging" + log);
                List<IAppender> appenderList = configurationService.getAppenderList();
                appenderList.forEach(appender -> appender.append(log));
            } catch (Exception e) {
                System.out.println("Exception wile process"+e.getMessage());
            }
        }
    }

}
