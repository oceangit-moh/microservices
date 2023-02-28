package com.microservice.product.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sagar.Mohanty
 * Feb 27, 2023
 * 4:35:03 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(value = "product")
public class Product {
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;

}
