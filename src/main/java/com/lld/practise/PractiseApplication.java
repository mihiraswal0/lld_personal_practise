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
	}

}
