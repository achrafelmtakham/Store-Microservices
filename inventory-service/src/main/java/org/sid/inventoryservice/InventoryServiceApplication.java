package org.sid.inventoryservice;

import org.sid.inventoryservice.model.Product;
import org.sid.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		return args->{
			restConfiguration.exposeIdsFor(Product.class);
			productRepository.save(new Product(null,"Ord HP 877",3800));
			productRepository.save(new Product(null,"samsung galaxy s20",5000));
			productRepository.save(new Product(null,"Iphone  11 pro",10000));
		};
	}

}
