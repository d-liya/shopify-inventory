package com.shopify.inventory.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.inventory.dto.ProductDTO;
import com.shopify.inventory.mapper.ProductMapper;
import com.shopify.inventory.model.Product;
import com.shopify.inventory.service.ProductService;
import com.shopify.inventory.utils.ProductCSVUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin
@RestController
@RequestMapping("/api/productservice")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@CrossOrigin
	@GetMapping(value="/products")
	ResponseEntity<List<ProductDTO>> getAll(){
	
		try {
			List<Product> productList = productService.getAllProducts();
			List<ProductDTO> prDto = new ArrayList<ProductDTO>();
			
			for(Product p: productList) {
				prDto.add(ProductMapper.EntityToDto(p));
			}
			return ResponseEntity.ok(prDto);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	

	@GetMapping(value = "/products/{id}")
	ResponseEntity<ProductDTO> getById(@PathVariable("id") @Min(1) int id){
		try {
			Product product = productService.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException(null,"No product with the given id: "+ id));
			ProductDTO productDto = ProductMapper.EntityToDto(product);
			return ResponseEntity.ok().body(productDto);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PostMapping(value="/products")
	ResponseEntity<?> createProduct (@Valid @RequestBody ProductDTO prd){
		Product product = ProductMapper.DtoToEntity(prd);
		Product addedProduct =  productService.save(product);
		if(addedProduct != null) {
			return ResponseEntity.created(null).build();
		}
		return ResponseEntity.internalServerError().build();

	}
	
	@PutMapping(value="/products/{id}")
	ResponseEntity<?>  updateProduct (@PathVariable("id") @Min(1) int id, @Valid @RequestBody ProductDTO prd){
		try {
			Product product = productService.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException(null,"No product with the given id: "+ id));
			product = ProductMapper.DtoToEntity(prd, product);
			Product addedProduct =  productService.save(product);
			if(addedProduct != null) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	@DeleteMapping(value="/products/{id}")
	ResponseEntity<String> deleteProduct (@PathVariable("id") @Min(1) int id){
		try {
			Product product = productService.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException(null,"No product with the given id: "+ id));
			productService.delete(id);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	

	@GetMapping(value = "/download", produces = "text/csv")
    void findCities(HttpServletResponse response) throws IOException {

		List<Product> productList = productService.getAllProducts();
		if(productList.size() > 0) {
			ProductCSVUtil.writeProducts(response.getWriter(), productList);
			return;
		}
        response.setStatus(204);
       
    }
	
}
