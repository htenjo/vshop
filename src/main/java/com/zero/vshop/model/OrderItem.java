/**
 * 
 */
package com.zero.vshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zero.vshop.constant.Constants;

/**
 * @author hernan
 */
@Entity
@Table(name=OrderItem.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = OrderItem.SEQUENCE_NAME, sequenceName=OrderItem.SEQUENCE_NAME, allocationSize=1)
public class OrderItem extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia de un orderItem
	public static final String TABLE_NAME = Constants.TABLE_ORDER_ITEM;
	public static final String SEQUENCE_NAME = Constants.SEQ_ORDER_ITEM;
	public static final String ENTITY_NAME = "com.zero.vshop.model.OrderItem";
	//Atributos de la instancia
	private Order order;
	private Product product;
	private int amount;
	private double unitPrice;
	
	/**
	 * @return the product
	 */
	@ManyToOne(optional=false)
	public Product getProduct() {
		return product;
	}
	
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the order
	 */
	@ManyToOne(optional=false)
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * Método que obtiene el valor unitario del producto, en el momento de la compra
	 * @return El valor del producto en el momento de la compra
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Método que modificar el valor unitario del producto comprado en el momento de la compra
	 * @param unitPrice Nuevo valor unitario del producto
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Metodo que define como se debe generar el id de la entidad y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=OrderItem.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
