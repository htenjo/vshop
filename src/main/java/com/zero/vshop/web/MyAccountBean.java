package com.zero.vshop.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.zero.vshop.model.Client;
import com.zero.vshop.model.OrderItem;
import com.zero.vshop.web.manager.VShopWebManager;

@ManagedBean
@ViewScoped
public class MyAccountBean {
	@Inject private VShopWebManager manager;
	private List<OrderItem> items;
	
	/**
	 * Método que inicia todas las variables requeridas para la página inicial
	 */
	@PostConstruct
	public void init(){
		Client client = manager.getCurrentClient();
		items = manager.listOrderItemsByClient(client.getId());
	}

	/**
	 * Método que obtiene las ordenes que han sido generadas durante el último periodo
	 * @return Lista con las compras realizadas durante el último periodo
	 */
	public List<OrderItem> getItems() {
		return items;
	}
}
