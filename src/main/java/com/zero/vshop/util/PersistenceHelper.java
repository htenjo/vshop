/**
 * 
 */
package com.zero.vshop.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.zero.vshop.constant.Constants;

/**
 * @author hernan
 *
 */
public class PersistenceHelper {
	private static PersistenceHelper instance = new PersistenceHelper();
	
	private PersistenceHelper(){
	}
	
	public static PersistenceHelper getInstance(){
		return instance;
	}
	
	@SuppressWarnings(Constants.WARNING_UNCHECKED)
	public <T> T findFromNamedQuery(Class<T> clazz, String queryName, String paramName, Object paramValue, EntityManager manager){
		try{
			Query query = manager.createNamedQuery(queryName, clazz);
			query.setParameter(paramName, paramValue);
			return (T)query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings(Constants.WARNING_UNCHECKED)
	public <T> T findFromNamedQuery(Class<T> clazz, String queryName, Map<String, Object> parameters, EntityManager manager){
		try{
			Query query = manager.createNamedQuery(queryName, clazz);
			
			for(Entry<String, Object> entry : parameters.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());			
			}
			
			return (T)query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Metodo que cuenta la cantidad de entidades que se encuentran
	 * almacenadas en el sistema y que cumplen una serie de condiciones
	 * @param queryName Nombre de la consulta estática que se desea ejecutar
	 * @param parameterName Nombre del parámetro por el que se desea filtrar
	 * @param parameterValue Valor por el que se desea filtrar
	 * @return La cantidad de entidades almacenadas en el sistema que cumplen
	 * con las condiciones dadas por los parametros. 
	 */
	public int countFromNamedQuery(String queryName, String parameterName, Object parameterValue, EntityManager manager){
		Query query = manager.createNamedQuery(queryName);
		query.setParameter(parameterName, parameterValue);
		return (Integer)query.getSingleResult();
	}
	
	/**
	 * Metodo que cuenta la cantidad de entidades que se encuentran
	 * almacenadas en el sistema y que cumplen una serie de condiciones
	 * @param queryName Nombre de la consulta estática que se desea ejecutar
	 * @param parameters Mapa que contiene los parametros requeridos por la consulta
	 * @return La cantidad de entidades almacenadas en el sistema que cumplen
	 * con las condiciones dadas por los parametros. 
	 */
	public int countFromNamedQuery(String queryName, Map<String, Object> parameters, EntityManager manager){
		Query query = manager.createNamedQuery(queryName);
		
		for(Entry<String, Object> entry : parameters.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());			
		}
		
		return (Integer)query.getSingleResult();
	}
}
