package com.shopify.inventory.service;

import java.util.List;
import java.util.Optional;

import com.shopify.inventory.model.Product;

public interface IProduct {
	List<Product> getAllProducts();
	
	Optional<Product> findById(int id);
	
	Product save(Product prd);
	
	void delete(int id);
}
