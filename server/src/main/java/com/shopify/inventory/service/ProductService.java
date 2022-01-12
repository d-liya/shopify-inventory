package com.shopify.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopify.inventory.model.Product;
import com.shopify.inventory.repository.ProductRepository;

@Service
public class ProductService implements IProduct {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product prd) {
		return productRepository.save(prd);
	}

	@Override
	public void delete(int id) {
		productRepository.deleteById(id);
	}

}
