package com.example.ejercicio1S456;

import com.example.ejercicio1S456.entities.Laptop;
import com.example.ejercicio1S456.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context =   SpringApplication.run(Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null,"i7", 800.00, false);
		Laptop laptop2 = new Laptop(null,"i5", 700.00, true);
		Laptop laptop3 = new Laptop(null,"i3", 400.00, false);

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);


	}

}
