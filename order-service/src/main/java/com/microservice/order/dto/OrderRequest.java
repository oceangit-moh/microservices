package com.microservice.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sagar.Mohanty
 * Feb 27, 2023
 * 7:48:43 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
	private List<OrderLineItemsDto> orderLineItemsDtoList;
}
