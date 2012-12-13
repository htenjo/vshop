/**
 * 
 */
package com.zero.vshop.test;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.model.Role;
import com.zero.vshop.service.VShopServiceFacade;


/**
 * Clase que contiene funcionalidades utilitarias para la ejecución de las pruebas
 * @author hernan
 */
public class TestHelper {
	//Fachada de servicios
	private VShopServiceFacade service;
	
	/**
	 * Método constructor del helper
	 * @param service Fachada de servicios que será utilizada
	 */
	public TestHelper(VShopServiceFacade service) {
		this.service = service;
	}
	
	/**
	 * Método que obtiene/construye el role Administrador del sistema
	 * @return Role que corresponde al administrador del sistema
	 */
	public Role buildRoleAdmin(){
		Role role = service.findRoleByName(Constants.ROLE_ADMIN_NAME);
		
		if(role == null){
			role = new Role();
			role.setName(Constants.ROLE_ADMIN_NAME);
			role = service.saveRole(role);
		}
		
		return role;
	}
	
	/**
	 * Método que obtiene/construye el role Cliente del sistema
	 * @return Role que corresponde al Cliente del sistema
	 */
	public Role buildRoleClient(){
		Role role = service.findRoleByName(Constants.ROLE_CLIENT_NAME);
		
		if(role == null){
			role = new Role();
			role.setName(Constants.ROLE_CLIENT_NAME);
			role = service.saveRole(role);
		}
		
		return role;
	}
}
