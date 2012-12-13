/**
 * 
 */
package com.zero.vshop.security;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.zero.vshop.service.VShopServiceFacade;

/**
 * Clase que permite tener acceso a los servicios de negocio en los contextos
 * donde la injección de dependencias no funciona (Beans no manejados)
 * p.e el contexto de SpringSecutity
 * @author hernan
 */
public class ServiceLocator {
	private static VShopServiceFacade service;
	
	private ServiceLocator(){
	}
	
	public static VShopServiceFacade getInstance(){
		if(service == null){
			try{
				Context context = new InitialContext();
				service = (VShopServiceFacade)context.lookup("java:global/TiendaVirtual/VShopServiceFacade");
			}catch(NamingException e){
				//TODO: Aca debe agregarse la gestión de logs respectiva
				e.printStackTrace();
			}
		}
		
		return service;
	}
}
