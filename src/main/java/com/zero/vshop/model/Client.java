/**
 * 
 */
package com.zero.vshop.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.constant.PersistenceConstant;

/**
 * @author hernan
 *
 */
@Entity
@Table(name=Client.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = Client.SEQUENCE_NAME, sequenceName=Client.SEQUENCE_NAME, allocationSize=1)
@NamedQueries(
	@NamedQuery(name=Client.FIND_BY_USERNAME, query=PersistenceConstant.CLIENT_FIND_BY_USERNAME)
)
public class Client extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia del Cliente 
	public static final String TABLE_NAME = Constants.TABLE_CLIENT;
	public static final String SEQUENCE_NAME = Constants.SEQ_CLIENT;
	public static final String ENTITY_NAME = "com.zero.vshop.model.Client";
	public static final String FIND_BY_USERNAME = ENTITY_NAME + ".findByUsername";
	//Atributos de la instancia
	private String firstName;
	private String lastName;
	private double credit;
	private User user;
	
	/**
	 * @return the firstName
	 */
	@Column(nullable=false, length=Constants.DB_STRING_DEFAULT_SIZE)
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	@Column(nullable=false, length=Constants.DB_STRING_DEFAULT_SIZE)
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the user
	 */
	@OneToOne(optional=false, orphanRemoval=true, cascade=CascadeType.ALL)
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Método que obtiene la cantidad de credito que tiene hasta el momento el cliente
	 * Una cantidad positiva indica que el cliente ha dado más dinero del que ha gastado
	 * Una cantidad negativa indica que el cliente aún no ha pagado todo lo que ha consumido
	 * @return Saldo del dinero que tiene a favor el cliente
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * Método que modifica la cantidad de crédito que tiene hasta el momento el cliente
	 * @param credit Nueva cantidad de crédito asociada al cliente 
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * Metodo que define como se debe generar el id de la entidad y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Client.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
