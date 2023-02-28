package com.microservice.order.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sagar.Mohanty
 * Feb 27, 2023
 * 7:50:09 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {

	private Long id;
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
