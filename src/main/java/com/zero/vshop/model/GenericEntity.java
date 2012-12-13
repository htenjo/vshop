/**
 * 
 */
package com.zero.vshop.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author hernan
 *
 */
@MappedSuperclass
public abstract class GenericEntity implements Serializable{
	//Variable de serialización por defecto
	private static final long serialVersionUID = 1L;
	//Identificador único de la entidad
	protected long id;

	/**
	 * Metodo que define como se debe generar el id de la entidad
	 * y su obtención. Se debe definir en cada entidad concreta
	 * para que se manejen de manera independiente en la BD
	 * @return el identificador de la entidad
	 */	
	@Transient
	public abstract long getId();
	
	/**
	 * Metodo que debe ser implementado para definir la 
	 * manera en la que se asignara un nuevo id a la entidad
	 * @param id Nuevo identificador que será asignado a la entidad
	 */
	public void setId(long id){
		this.id = id;
	}
	
	/**
	 * Método que determina si la entidad es una nueva entidad o una entidad persistida con base en su ID
	 * @return True si el ID de la entidad es 0, false de lo contrario
	 */
	@Transient
	public boolean isNew(){
		return getId() == 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//Se garantiza que no se hagan comparaciones nulas
		if(obj == null){
			return false;
		}
		//Se garantiza la relacion reflexiva
		if(this == obj){
			 return true;
		}
		//Se garantiza que los dos objetos si sean de la misma clase
		//el instance of solo garantiza que pertenezcan a la misma familia
		if(this.getClass() != obj.getClass()){
			return false;
		}
		
		//Si lo anterior no se cumple se puede pasar a comparar las entidades
		//que contienen el id, atributo diferenciador entre entidades
		return this.getId() == this.getClass().cast(obj).getId();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int)this.getId()*17*this.getClass().getName().length();
	}
}
