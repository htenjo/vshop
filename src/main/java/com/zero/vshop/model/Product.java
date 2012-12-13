/**
 * 
 */
package com.zero.vshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.constant.PersistenceConstant;

/**
 * @author hernan
 */
@Entity
@Table(name=Product.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = Product.SEQUENCE_NAME, sequenceName=Product.SEQUENCE_NAME, allocationSize=1)
@NamedQueries(
	@NamedQuery(name=Product.LIST_ALL, query=PersistenceConstant.PRODUCT_LIST_ALL)
)
public class Product extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia del Producto 
	public static final String TABLE_NAME = Constants.TABLE_PRODUCT;
	public static final String SEQUENCE_NAME = Constants.SEQ_PRODUCT;
	public static final String ENTITY_NAME = "com.zero.vshop.model.Product";
	public static final String LIST_ALL = ENTITY_NAME + ".listAll";
	
	//Atributos de la instancia
	private String name;
	private double price;
	private String photoUrl;
	private String provider;
	private int amount;
	private int priority;
	
	/**
	 * @return the name
	 */
	@Column(length=Constants.DB_STRING_DEFAULT_SIZE, nullable=false)
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the photoUrl
	 */
	@Column(length=Constants.DB_STRING_SHORT_SIZE)
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	/**
	 * @return the provider
	 */
	@Column(length=Constants.DB_STRING_DEFAULT_SIZE)
	public String getProvider() {
		return provider;
	}
	
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
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
	 * Método que obtiene la prioridad del producto
	 * @return Número entero que determina la prioridad del producto
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Método que modifica la prioridad del producto
	 * @param priority Nueva prioridad que tendrá el producto
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Metodo que define como se debe generar el id de la entidad
	 * y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Product.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
