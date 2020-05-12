package org.sid.billingservice;

import org.sid.billingservice.model.Bill;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.model.ProductItem;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.sid.billingservice.service.CustomerService;
import org.sid.billingservice.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerService customerService,
							ProductService productService){
		return args -> {
			Customer c1 = customerService.findCustomerById(1L);
			System.out.println("+++++++++++++++");
			System.out.println("ID = "+ c1.getId());
			System.out.println("Name : " + c1.getName());
			System.out.println("Email : "+ c1.getEmail());
			System.out.println("+++++++++++++++");
			Bill bill1 = billRepository.save(new Bill(null, new Date(),c1.getId(),null,null));
			PagedModel<Product> products = productService.findAllProducts();
			products.getContent().forEach(p->{
				productItemRepository.save(new ProductItem(null,p.getId(),null,p.getPrice(),30,bill1));
			});
		};
	}

}
