/**
 * 
 */
package com.zero.vshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.constant.PersistenceConstant;

/**
 * @author hernan
 *
 */
@Entity
@Table(name=User.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = User.SEQUENCE_NAME, sequenceName=User.SEQUENCE_NAME, allocationSize=1)
@NamedQueries(
	@NamedQuery(name=User.FIND_BY_USERNAME, query=PersistenceConstant.USER_FIND_BY_USERNAME)
)
public class User extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia del usuario 
	public static final String TABLE_NAME = Constants.TABLE_USER;
	public static final String SEQUENCE_NAME = Constants.SEQ_USER;
	public static final String ENTITY_NAME = "com.zero.vshop.model.User";
	public static final String FIND_BY_USERNAME = ENTITY_NAME + ".findByUsername";
	
	//Atributos de la instancia
	private String username;
	private String password;
	private Set<Role> roles = new HashSet<Role>();
	
	/**
	 * @return the username
	 */
	@Column(nullable=false, unique=true, length=Constants.DB_STRING_DEFAULT_SIZE)
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Método que permite agregar un role al usuario
	 * @param role El rol que se agregará al usuario
	 */
	@Transient
	public void addRole(Role role){
		this.roles.add(role);
	}
	
	/**
	 * @return the role
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<Role> getRoles() {
		return roles;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * Metodo que define como se debe generar el id de la entidad y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=User.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
