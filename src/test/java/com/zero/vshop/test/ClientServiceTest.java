/**
 * 
 */
package com.zero.vshop.test;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zero.vshop.model.Client;
import com.zero.vshop.model.User;

/**
 * @author hernan
 *
 */
public class ClientServiceTest extends GenericTest{
	/**
	 * Metodo que permite realizar la configuración inicial para la correcta ejecucion de las pruebas
	 * @throws AcsendoException 
	 */
	@BeforeClass
	public static void setup(){
	}
	
	@Test
	public void testSave(){
		String username = "zerovirus23", password = "VShop840123";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.addRole(helper.buildRoleAdmin());
		user.addRole(helper.buildRoleClient());
		Client client = new Client();
		client.setFirstName("Hernán");
		client.setLastName("Tenjo");
		client.setUser(user);
		client = service.saveClient(client);
		Assert.assertNotNull(client);
		Assert.assertEquals(client.getUser().getUsername(), username);
		Assert.assertEquals(client.getUser().getPassword(), password);
		Assert.assertEquals(client.getUser().getRoles().size(), 2);
	}
}
