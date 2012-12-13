/**
 * 
 */
package com.zero.vshop.constant;

import org.apache.commons.lang.StringUtils;

/**
 * @author hernan
 *
 */
public class Constants {
	public static final String PERSISTENT_UNIT_NAME = "vshopPU";
	public static final String SCHEMA_STORE = "model";
	
	public static final String TABLE_USER = "user";
	public static final String SEQ_USER = SCHEMA_STORE + ".seq_" + TABLE_USER;
	
	public static final String TABLE_ROLE = "role";
	public static final String SEQ_ROLE = SCHEMA_STORE + ".seq_" + TABLE_ROLE;
	
	public static final String TABLE_PRODUCT = "product";
	public static final String SEQ_PRODUCT = SCHEMA_STORE + ".seq_" + TABLE_PRODUCT;
	
	public static final String TABLE_ORDER = "order";
	public static final String SEQ_ORDER = SCHEMA_STORE + ".seq_" + TABLE_ORDER;
	
	public static final String TABLE_ORDER_ITEM = "order_item";
	public static final String SEQ_ORDER_ITEM = SCHEMA_STORE + ".seq_" + TABLE_ORDER_ITEM;
	
	public static final String TABLE_CLIENT = "client";
	public static final String SEQ_CLIENT = SCHEMA_STORE + ".seq_" + TABLE_CLIENT;
	
	public static final String TABLE_PURCHASE = "purchase";
	public static final String SEQ_PURCHASE = SCHEMA_STORE + ".seq_" + TABLE_PURCHASE;
	
	public static final int DB_STRING_DEFAULT_SIZE = 256;
	public static final int DB_STRING_SHORT_SIZE = 1000;
	
	
	public static final String WARNING_UNCHECKED = "unchecked";
	public static final String MSG_WILDCARD = "#";
	public static final String EMPTY_STRING = StringUtils.EMPTY;
	public static final String ROLE_ADMIN_NAME = "ADMIN";
	public static final String ROLE_CLIENT_NAME = "CLIENT";
	public static final String CURRENT_CLIENT_PARAMETER_NAME = "currentClient";
}
