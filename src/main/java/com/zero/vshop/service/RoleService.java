/**
 * 
 */
package com.zero.vshop.service;

import javax.ejb.Stateless;

import com.zero.vshop.model.Role;

/**
 * @author hernan
 *
 */
@Stateless
public class RoleService extends GenericService<Role>{
	public Role saveRole(Role role){
		return super.save(role);
	}
	
	public Role findRole(long roleId){
		return super.find(roleId, Role.class);
	}
	
	public Role findRoleByName(String roleName){
		return helper.findFromNamedQuery(Role.class, Role.FIND_BY_NAME, "name", roleName, manager);
	}
}
