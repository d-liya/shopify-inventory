package com.shopify.inventory.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.FluentPropertyBeanIntrospector;
import org.apache.commons.beanutils.PropertyUtils;

import com.shopify.inventory.dto.ProductDTO;
import com.shopify.inventory.model.Product;

public class ProductMapper {
	
	public static Product DtoToEntity(ProductDTO prdto) {
		Product pr = new Product();
		try {
			PropertyUtils.addBeanIntrospector(new FluentPropertyBeanIntrospector());
			BeanUtils.copyProperties(pr, prdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pr;
	}
	
	public static Product DtoToEntity(ProductDTO prdto, Product pr) {
		try {
			PropertyUtils.addBeanIntrospector(new FluentPropertyBeanIntrospector());
			BeanUtils.copyProperties(pr, prdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pr;
	}
	
	public static ProductDTO EntityToDto(Product pr) {
		ProductDTO prdto = new ProductDTO();
		try {
			PropertyUtils.addBeanIntrospector(new FluentPropertyBeanIntrospector());
			BeanUtils.copyProperties(prdto, pr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prdto;
	}
}
