package com.lld.practise;

import com.lld.practise.designPattern.CompositePattern;
import com.lld.practise.designPattern.FactoryMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PractiseApplication.class, args);

//		FactoryMethod factoryMethod=new FactoryMethod();
//		factoryMethod.implementFactoryMethod();
		CompositePattern compositePattern=new CompositePattern();
		compositePattern.implementComposite();
	}

}
