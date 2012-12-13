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
 *
 */
@Entity
@Table(name=Purchase.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = Purchase.SEQUENCE_NAME, sequenceName=Purchase.SEQUENCE_NAME, allocationSize=1)
public class Purchase extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia de la compra 
	public static final String TABLE_NAME = Constants.TABLE_PURCHASE;
	public static final String SEQUENCE_NAME = Constants.SEQ_PURCHASE;
	public static final String ENTITY_NAME = "com.zero.vshop.model.Purchase";
	//Definición de los atributos de la entidad
	private Product product;
	private int amount;
	private double totalPrice;
	private String provider;
	
	/**
	 * Método que obtiene el producto que se ha comprado
	 * @return El producto que ha sido comprado
	 */
	@ManyToOne(optional=false)
	public Product getProduct() {
		return product;
	}

	/**
	 * Método que modifica el producto que se ha comprado
	 * @param product El nuevo producto que ha sido comprado
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Método que obtiene la cantidad total de unidades compradas
	 * @return La cantidad de unidades compradas del producto
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Método que modifica la cantidad total de unidades compradas
	 * @param amount Nueva cantidad de unidades compradas
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Método que obtiene el valor total de la compra
	 * @return Valor del conjunto de unidades compradas
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Método que modifica el valor total de la compra
	 * @param totalPrice NUevo valor del conjunto de unidades compradas
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Método que obtiene el nombre del proveedor (Almacen donde se compro el producto)
	 * @return Nombre del proveedor del producto
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * Método que modifica el nombre del proveedor del producto
	 * @param provider El nuevo nombre del proveedor del producto
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * Metodo que define como se debe generar el id de la entidad
	 * y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Purchase.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
