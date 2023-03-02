package com.microservice.order.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.microservice.order.dto.InventoryResponse;
import com.microservice.order.event.OrderPlacedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.order.dto.OrderLineItemsDto;
import com.microservice.order.dto.OrderRequest;
import com.microservice.order.model.Order;
import com.microservice.order.model.OrderLineItems;
import com.microservice.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;

	private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::mapToDto)
		.toList();
		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode)
				.toList();
		// Call inventory service, and place order if product is in
		InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse :: isInStock);
		if(inventoryResponseArray.length>0)
		{
			orderRepository.save(order);
			kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
			return "Order Placed Successfully";
		}
		else
		{
			throw new IllegalArgumentException("Product is not in stock, please try again later");
		}
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
