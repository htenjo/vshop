/**
 * 
 */
package com.zero.vshop.web.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.model.Client;
import com.zero.vshop.model.Order;
import com.zero.vshop.model.OrderItem;
import com.zero.vshop.model.Product;
import com.zero.vshop.security.VShopUserDetails;
import com.zero.vshop.service.VShopServiceFacade;

/**
 * @author hernan
 *
 */
@Named
public class VShopWebManager {
	@EJB private VShopServiceFacade service;
	
	/**
	 * Método que obtiene todos los productos disponibles para la venta
	 * @return Lista con los productos disponibles para la venta
	 */
	public List<Product> listProducts(){
		return service.listProducts();
	}
	
	public List<OrderItem> listOrderItemsByClient(long clientId){
		return service.listOrderItemsByClient(clientId);
	}
	
	public void buy(Order order){
		Order persistedOrder = service.buy(order);
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		updateClientInfo(persistedOrder.getClient(), request);
	}
	
	//==========================================================================
	//							MÉTODOS AUXILIARES
	//==========================================================================
	public void updateClientInfo(Client updatedClient, HttpServletRequest request){
		Client loggedClient = getCurrentClient();
		Client sessionClient = (Client)request.getSession().getAttribute(Constants.CURRENT_CLIENT_PARAMETER_NAME);
		
		if(loggedClient != null && sessionClient != null){
			loggedClient.setCredit(updatedClient.getCredit());
			sessionClient.setCredit(updatedClient.getCredit());
		}
	}
	
	/**
	 * Metodo que permite obtener el usuario autenticado actualmente
	 * @return El {@link UserDTO} generado en el proceso de autenticacion
	 */
	public Client getCurrentClient(){
		VShopUserDetails userDetails = getLoggedUser(VShopUserDetails.class);
		Client currentClient = null;
		
		if(userDetails != null){
			currentClient = userDetails.getClient();
		}
		
		return currentClient;
	}
	
	/**
	 * Metodo que permite obtener el principal registrado en el 
	 * contexto de seguridad. Generalmente el username
	 * @param <T> Tipo del objeto que se espera como principal
	 * @param clazz Clase del objeto que ha sido registrado como Principal
	 * @return El objeto que ha sido registrado como principal
	 */
	@SuppressWarnings(Constants.WARNING_UNCHECKED)
	public <T extends UserDetails> T getLoggedUser(Class<T> clazz){
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
			return (T)authentication.getPrincipal();
		}else{
			return null;
		} 
	}
	
	/**
	 * Metodo predeterminado para obtener el principal registrado en 
	 * el contexto de seguridad. El principal en este metodo corresponde
	 * directamente con el username del usuario logueado
	 * @return Username del usuario logueado
	 */
	public String getDefaultLoggedUser(){		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return (String)authentication.getPrincipal();
	}
	
	/**
	 * Metodo que permite obtener los roles del usuario logueado
	 * como una lista de cadenas
	 * @return Lista de roles en forma de cadena
	 */
	public List<String> getUserRolesAsString(){
		List<String> roles = new ArrayList<String>();
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Collection<GrantedAuthority> authorities = authentication.getAuthorities();
		
		for(GrantedAuthority authority : authorities){
			roles.add(authority.getAuthority());	
		}
		
		return roles;
	}
}