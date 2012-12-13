/**
 * 
 */
package com.zero.vshop.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.zero.vshop.constant.Constants;
import com.zero.vshop.util.PersistenceHelper;

/**
 * @author hernan
 *
 */
public class GenericService<T> {
	//Unidad de persistencia requerida por el servicio
	@PersistenceContext(unitName = Constants.PERSISTENT_UNIT_NAME)
	protected EntityManager manager;
	protected PersistenceHelper helper = PersistenceHelper.getInstance();
	
	protected T save(T entity){
		manager.persist(entity);
		return entity;
	}
	
	protected T find(long entityId, Class<T> clazz){
		return manager.find(clazz, entityId);
	}
	
	protected void delete(T entity){
		manager.remove(entity);
	}
	
	protected void delete(Class<T> entityClazz, long entityId){
		T entity = manager.getReference(entityClazz, entityId);
		delete(entity);
	}
	
	protected List<T> listAll(String queryName, Class<T> clazz){
		TypedQuery<T> query = manager.createNamedQuery(queryName, clazz);
		return query.getResultList();
	}
	
	protected List<T> list(String queryName, Class<T> clazz, Integer startIndex, Integer maxElements, Map<String, Object> parameters){
		TypedQuery<T> query = manager.createNamedQuery(queryName, clazz);
		
		if(startIndex != null && maxElements != null){
			query.setFirstResult(startIndex);
			query.setMaxResults(maxElements);
		}
		
		for(String parameterName : parameters.keySet()){
			query.setParameter(parameterName, parameters.get(parameterName));
		}
		
		return query.getResultList();
	}
}
