/**
 * 
 */
package com.zero.vshop.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.constant.PersistenceConstant;

/**
 * @author hernan
 *
 */
@Entity
@Table(name=Order.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = Order.SEQUENCE_NAME, sequenceName=Order.SEQUENCE_NAME, allocationSize=1)
@NamedQueries(
		@NamedQuery(name=Order.LIST_BY_CLIENT_IN_PERIOD, query=PersistenceConstant.ORDER_LIST_BY_CLIENT_IN_PERIOD)
	)
public class Order extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia de una orden 
	public static final String TABLE_NAME = Constants.TABLE_ORDER;
	public static final String SEQUENCE_NAME = Constants.SEQ_ORDER;
	public static final String ENTITY_NAME = "com.zero.vshop.model.Order";
	public static final String LIST_BY_CLIENT_IN_PERIOD = ENTITY_NAME + ".listByClientInPeriod";
	//Atributos de la instancia
	private Set<OrderItem> items = new LinkedHashSet<OrderItem>();
	private Date orderDate;
	private Client client;
	
	/**
	 * Método conveniente que permite agregar un nuevo item (producto) a la orden
	 * @param item Registro que hace referencia a un nuevo producto en la orden
	 */
	@Transient
	public void addItem(OrderItem item){
		if(items == null){
			items = new LinkedHashSet<OrderItem>();
		}
		
		item.setOrder(this);
		items.add(item);
	}
	
	/**
	 * Método que calcula el precio actual de la compra 
	 * @return Valor total de la compra
	 */
	@Transient
	public double getTotalPrice(){
		double price = 0;
		
		for(OrderItem item : items){
			price += item.getUnitPrice() * item.getAmount();
		}
		
		return price;
	}
	
	/**
	 * @return the items
	 */
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="order")
	public Set<OrderItem> getItems() {
		return items;
	}
	
	/**
	 * @param items the items to set
	 */
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	
	/**
	 * @return the orderDate
	 */
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOrderDate() {
		return orderDate;
	}
	
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * @return the client
	 */
	@ManyToOne(optional=false)
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Metodo que define como se debe generar el id de la entidad y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Order.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
