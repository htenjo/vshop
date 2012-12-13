/**
 * 
 */
package com.zero.vshop.service;

import java.util.List;

import javax.ejb.Stateless;


import com.zero.vshop.model.Product;

/**
 * @author hernan
 *
 */
@Stateless
public class ProductService extends GenericService<Product>{
	public List<Product> listAll(){
		return listAll(Product.LIST_ALL, Product.class);
	}
}
