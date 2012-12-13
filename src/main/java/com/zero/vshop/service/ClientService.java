/**
 * 
 */
package com.zero.vshop.service;

import javax.ejb.Stateless;

import com.zero.vshop.model.Client;

/**
 * @author hernan
 *
 */
@Stateless
public class ClientService extends GenericService<Client>{
	public Client saveClient(Client entity){
		return this.save(entity);
	}
	
	public Client findClient(long clientId){
		return super.find(clientId, Client.class);
	}
	
	public void deleteClient(long clientId){
		super.delete(Client.class, clientId);
	}
}
