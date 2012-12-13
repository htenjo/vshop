package com.zero.vshop.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zero.vshop.model.Client;
import com.zero.vshop.service.VShopServiceFacade;


/** 
 * Clase que determina si un usuario se ha autenticado de forma correcta
 * @author Hernan Tenjo
 */
public class VShopAuthenticationProvider implements AuthenticationProvider{
	//Fachada de servicios de negocio
	private VShopServiceFacade service;
	
	public VShopAuthenticationProvider(){
		super();
		service = ServiceLocator.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.providers.AuthenticationProvider#authenticate(org.springframework.security.Authentication)
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = StringUtils.trim((String)authentication.getPrincipal());
		String password = StringUtils.trim((String)authentication.getCredentials());
		Client client = service.authenticateUser(username, password);
		
		if(client != null){
			UserDetails userDetails = new VShopUserDetails(client);
			return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
		}else{
			throw new UsernameNotFoundException(null);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.providers.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authenticationTypeClazz) {
		if(UsernamePasswordAuthenticationToken.class == authenticationTypeClazz){
			return true;
		}else{
			return false;
		}
	}
}