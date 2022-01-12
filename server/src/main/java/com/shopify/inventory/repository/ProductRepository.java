package com.shopify.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopify.inventory.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
