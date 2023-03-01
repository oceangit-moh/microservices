package com.microservice.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sagar.Mohanty
 * Feb 27, 2023
 * 5:17:52 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
