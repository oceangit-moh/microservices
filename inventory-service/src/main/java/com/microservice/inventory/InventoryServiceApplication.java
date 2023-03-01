package com.microservice.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.microservice.inventory.model.Inventory;
import com.microservice.inventory.repository.InventoryRepository;


@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
		@Bean
		CommandLineRunner loadData(InventoryRepository inventoryRepository) {
			return args -> {
				Inventory inventory = new Inventory();
				inventory.setSkuCode("Mi4");
				inventory.setQuantity(10);
				
				Inventory inventory1 = new Inventory();
				inventory1.setSkuCode("Mi5");
				inventory1.setQuantity(0);
				
				inventoryRepository.save(inventory);
				inventoryRepository.save(inventory1);
			};
		}
}
