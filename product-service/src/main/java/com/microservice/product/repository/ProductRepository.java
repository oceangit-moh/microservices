package com.microservice.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.product.model.Product;

/**
 * @author Sagar.Mohanty
 * Feb 27, 2023
 * 5:17:31 PM
 */
public interface ProductRepository extends MongoRepository<Product, String>{

}
