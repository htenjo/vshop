/**
 * 
 */
package com.zero.vshop.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.zero.vshop.model.Client;
import com.zero.vshop.model.User;

/**
 * @author hernan
 *
 */
@Stateless
public class UserService extends GenericService<User>{
	public User save(User user){
		return super.save(user);
	}
	
	public void delete(User user){
		super.delete(user);
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Client authenticate(String username, String password){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", username);
		parameters.put("password", password);
		return helper.findFromNamedQuery(Client.class, Client.FIND_BY_USERNAME, parameters, manager);
	}
}
