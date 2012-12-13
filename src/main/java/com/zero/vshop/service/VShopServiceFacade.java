/**
 * 
 */
package com.zero.vshop.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.model.Client;
import com.zero.vshop.model.Order;
import com.zero.vshop.model.OrderItem;
import com.zero.vshop.model.Product;
import com.zero.vshop.model.Role;

/**
 * @author hernan
 *
 */
@Stateless
public class VShopServiceFacade {
	//Unidad de persistencia requerida por el servicio
	@PersistenceContext(unitName = Constants.PERSISTENT_UNIT_NAME)
	private EntityManager manager;
	@EJB private UserService userService;
	@EJB private ClientService clientService;
	@EJB private RoleService roleService;
	@EJB private ProductService productService;
	@EJB private OrderService orderService;
	
	public Role saveRole(Role role){
		return roleService.saveRole(role);
	}
	
	public Role findRole(long roleId){
		return roleService.findRole(roleId);
	}
	
	public Role findRoleByName(String roleName){
		return roleService.findRoleByName(roleName);
	}
	
	public Client authenticateUser(String username, String password){
		return userService.authenticate(username, password);
	}
	
	public Client saveClient(Client entity){
		return clientService.saveClient(entity);
	}
	
	public Client findClient(long clientId){
		return clientService.findClient(clientId);
	}
	
	public void deleteClient(long clientId){
		clientService.deleteClient(clientId);
	}
	
	public List<Product> listProducts(){
		return productService.listAll();
	}
	
	public Order buy(Order order){
		Order persistedOrder = orderService.buy(order);
		Client client = persistedOrder.getClient();
		client.setCredit(client.getCredit() - persistedOrder.getTotalPrice());
		return order;
	}
	
	public List<OrderItem> listOrderItemsByClient(long clientId){
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return orderService.listOrderItemsByClient(clientId, calendar.getTime());
	}
}
