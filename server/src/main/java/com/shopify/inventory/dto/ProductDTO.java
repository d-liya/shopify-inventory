package com.shopify.inventory.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ProductDTO {
	private Integer id;

	private String name;
	
//	private String image;
	
	private int inventoryReceived;
	 
	private int inventoryShipped;
	 
	private int inventoryOnHand;
}
