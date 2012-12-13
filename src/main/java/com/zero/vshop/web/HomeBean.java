package com.zero.vshop.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.zero.vshop.model.Order;
import com.zero.vshop.model.OrderItem;
import com.zero.vshop.model.Product;
import com.zero.vshop.web.manager.VShopWebManager;

@ManagedBean
@ViewScoped
public class HomeBean {
	@Inject private VShopWebManager manager;
	private List<Product> products;
	private Order order;
	private int currentIds = 0;
	
	/**
	 * Método que inicia todas las variables requeridas para la página inicial
	 */
	@PostConstruct
	public void init(){
		products = manager.listProducts();
		order = new Order();
		order.setClient(manager.getCurrentClient());
	}

	/**
	 * Método que agrega productos al carrito de compras
	 * @param product Producto que se desea agregar
	 */
	public void addProduct(Product product){
		Set<OrderItem> orderItems = order.getItems();
		OrderItem item = null;
		
		for(OrderItem itemAux : orderItems){
			if(itemAux.getProduct().equals(product)){
				item = itemAux;
				break;
			}
		}
		
		if(item == null){
			item = new OrderItem();
			item.setId(--currentIds);
			item.setProduct(product);
			item.setUnitPrice(product.getPrice());
		}
		
		item.setAmount(item.getAmount() + 1);
		order.addItem(item);
	}
	
	/**
	 * Método que elimina un producto del carrito de compras
	 * @param item Elemento del carrito que se desea eliminar
	 */
	public void deleteOrderItem(OrderItem item){
		order.getItems().remove(item);
	}
	
	/**
	 * Método que permite finalizar la venta
	 */
	public void buy(){
		if(order.getItems().isEmpty()){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debes agregar por lo menos un producto al carrito", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}else{
			manager.buy(order);
			init();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra realizada con exito!", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	/**
	 * Método que obtiene la lista de los productos disponibles
	 * @return Lista con los productos disponibles para ser comprados
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Método que obtiene la orden que se está trabajando
	 * @return
	 */
	public List<OrderItem> getOrderItems() {
		return new ArrayList<OrderItem>(order.getItems());
	}
	
	public double getCurrentPrice(){
		return order.getTotalPrice();
	}
}
