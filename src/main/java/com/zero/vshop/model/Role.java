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
 *
 */
@Entity
@Table(name=Role.TABLE_NAME, schema=Constants.SCHEMA_STORE)
@SequenceGenerator(name = Role.SEQUENCE_NAME, sequenceName=Role.SEQUENCE_NAME, allocationSize=1)
@NamedQueries(
		@NamedQuery(name=Role.FIND_BY_NAME, query=PersistenceConstant.ROLE_FIND_BY_NAME)
)
public class Role extends GenericEntity{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Definicion de los atributos de persistencia del empleado
	public static final String TABLE_NAME = Constants.TABLE_ROLE;
	public static final String SEQUENCE_NAME = Constants.SEQ_ROLE;
	public static final String ENTITY_NAME = "com.zero.vshop.model.Role";
	public static final String FIND_BY_NAME = ENTITY_NAME + ".findByName";
	//Atributos de la instancia
	private String name;

	/**
	 * @return the name
	 */
	@Column(nullable=false, unique=true, length=Constants.DB_STRING_DEFAULT_SIZE)
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
	 * Metodo que define como se debe generar el id de la entidad
	 * y su obtención
	 * @return el identificador de la entidad
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Role.SEQUENCE_NAME)
	public long getId() {
		return this.id;
	}
}
