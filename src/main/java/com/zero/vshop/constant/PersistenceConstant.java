/**
 * 
 */
package com.zero.vshop.constant;

import com.zero.vshop.model.Client;
import com.zero.vshop.model.Order;
import com.zero.vshop.model.Product;
import com.zero.vshop.model.Role;
import com.zero.vshop.model.User;

/**
 * @author hernan
 *
 */
public class PersistenceConstant {
	public static final String ROLE_FIND_BY_NAME = "SELECT r FROM " + Role.ENTITY_NAME + " r WHERE r.name = :name";
	public static final String USER_FIND_BY_USERNAME = "SELECT u FROM " + User.ENTITY_NAME + " u WHERE u.username = :username";
	public static final String CLIENT_FIND_BY_USERNAME = "SELECT c FROM " + Client.ENTITY_NAME + " c WHERE c.user.username = :username AND c.user.password = :password";
	public static final String PRODUCT_LIST_ALL = "FROM " + Product.ENTITY_NAME + " as p ORDER BY p.priority DESC";
	public static final String ORDER_LIST_BY_CLIENT_IN_PERIOD = "FROM " + Order.ENTITY_NAME + " as o WHERE o.client.id = :clientId AND o.orderDate >= :fromDate ORDER BY o.id DESC";
}
