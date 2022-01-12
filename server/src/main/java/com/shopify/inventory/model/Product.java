package com.shopify.inventory.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCTID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "PRODUCTNAME", nullable = true)
	private String name;

//	@Column(name = "PRODUCTIMAGE", nullable = true)
//	private String image;
	
	@Column(name = "INVENTORYRECEIVED", nullable = true)
	private int inventoryReceived;
	 
	@Column(name = "INVENTORYSHIPPED", nullable = true)
	private int inventoryShipped;
	 
	@Column(name = "INVENTORYONHAND", nullable = true)
	private int inventoryOnHand;
	 
}

