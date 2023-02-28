package com.microservice.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sagar.Mohanty
 * Feb 27, 2023
 * 4:41:40 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {
	private String name;
	private String description;
	private BigDecimal price;
}
