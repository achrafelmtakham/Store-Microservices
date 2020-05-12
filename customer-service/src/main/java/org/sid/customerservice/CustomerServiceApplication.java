package org.sid.customerservice;

import org.sid.customerservice.model.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.save(new Customer(null,"ENSET","enset@gmail"));
			customerRepository.save(new Customer(null,"EMI","emi@gmail"));
			customerRepository.save(new Customer(null,"EHTP","ehtp@gmail"));
			customerRepository.findAll().forEach(System.out::println);

		};
	}
}
