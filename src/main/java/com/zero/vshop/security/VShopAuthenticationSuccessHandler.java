//=======================================================================
// ARCHIVO VShopAuthenticationSuccessHandler.java
// FECHA CREACIÓN: 2012/10/28
//=======================================================================
package com.zero.vshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.model.Client;
import com.zero.vshop.web.manager.VShopWebManager;


/**
 * Clase encargada de manejar las acciones que se deben realizar cuando el 
 * usuario se autentique satisfactoriamente en el sistema
 * @author Hernán Tenjo
 * @version 1.0
 */
public class VShopAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	//Fachada de servicios web	
	private VShopWebManager manager;
	
	public VShopAuthenticationSuccessHandler(){
		super();
		manager = new VShopWebManager();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#
	 * onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
		super.handle(request, response, authentication);
		Client client = manager.getCurrentClient();
		request.getSession().setAttribute(Constants.CURRENT_CLIENT_PARAMETER_NAME, client);
    }
}