package com.lld.practise;

import ch.qos.logback.core.Appender;
import com.lld.practise.designPattern.CompositePattern;
import com.lld.practise.designPattern.FactoryMethod;
import com.lld.practise.logger.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PractiseApplication.class, args);
		ConfigurationService configurationService=new ConfigurationService(LogLevel.WARN);
		configurationService.addApender(AppenderFactory.createAppender(AppenderType.CONSOLE));
		IAppender file=AppenderFactory.createAppender(AppenderType.FILE);
			((FileAppender) file).setPath("log.txt");
		configurationService.addApender(file);
		LoggerService loggerService=new LoggerService(configurationService);
//		loggerService.info("This is an info log");
		for(int i=0;i<100;i++){
			final int temp=i;
			Thread thread= new Thread(()->loggerService.info("This is info logs "+temp));
			thread.start();
		}
	}

}
