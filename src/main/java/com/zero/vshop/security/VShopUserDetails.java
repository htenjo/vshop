//=======================================================================
// ARCHIVO AcsendoUserDetails.java
// FECHA CREACIÓN: 24/03/2010
//=======================================================================
package com.zero.vshop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.zero.vshop.model.Client;
import com.zero.vshop.model.Role;


/**
 * Clase que implementa las comportamientos mínimos requeridos
 * por un UserDetails para Acsendo
 * @author Hernán Tenjo
 * @version 1.0
 */
public class VShopUserDetails implements UserDetails {
	//Identificador requerido para la serializacion
	private static final long serialVersionUID = 1L;
	//Objeto con la informacion requerida para el manejo de la seguridad
	private Client client;
	
	/**
	 * Metodo constructor del UserDetails
	 * @param client Objeto que guarda la información básica requerida
	 * para el manejo de la seguridad en Acsendo 
	 */
	public VShopUserDetails(Client client){
		this.client = client;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : client.getUser().getRoles()){
			GrantedAuthorityImpl auth = new GrantedAuthorityImpl(role.getName());
			authorities.add(auth);
		}
		
		return authorities;
	}

	/**
	 * Permite obtener la compañía del usuario logueado
	 * @return La compañía si el usuario tiene asociada una, null de lo contrario
	 */
	public Client getClient() {
		return client;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return client.getUser().getPassword();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return client.getUser().getUsername();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//Se garantiza que no se hagan comparaciones nulas
		if(obj == null){
			return false;
		}
		//Se garantiza la relacion reflexiva
		if(this == obj){
			 return true;
		}
		//Se garantiza que los dos objetos si sean de la misma clase
		//el instance of solo garantiza que pertenezcan a la misma familia
		if(this.getClass() != obj.getClass()){
			return false;
		}
		
		//Si lo anterior no se cumple se puede pasar a comparar los usuarios
		//que contiene el id que es el atributo diferenciador entre entidades
		return this.client.getUser().getId() == this.getClass().cast(obj).client.getUser().getId();		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int)this.client.getUser().getId()*17*this.getClass().getName().length();
	}
}