/**
 * 
 */
package com.zero.vshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.zero.vshop.model.Client;
import com.zero.vshop.model.Order;
import com.zero.vshop.model.OrderItem;
import com.zero.vshop.model.Product;

/**
 * @author hernan
 *
 */
@Stateless
public class OrderService extends GenericService<Order>{
	/**
	 * 
	 * @param order
	 */
	public Order buy(Order order){
		for(OrderItem item : order.getItems()){
			Product product = manager.merge(item.getProduct());
			//TODO: Acá se deberá agregar una validación que impida consumir mas productos de los existentes
			product.setAmount(product.getAmount() - item.getAmount());
			item.setId(0);
		}
		
		order.setOrderDate(new Date());
		order.setClient(manager.find(Client.class, order.getClient().getId()));
		return save(order);
	}
	
	public List<OrderItem> listOrderItemsByClient(long clientId, Date fromDate){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clientId", clientId);
		parameters.put("fromDate", fromDate);
		List<Order> orders = list(Order.LIST_BY_CLIENT_IN_PERIOD, Order.class, null, null, parameters);
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		for(Order order : orders){
			items.addAll(order.getItems());
		}
		
		return items;
	}
}