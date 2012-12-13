//=======================================================================
// ARCHIVO VShopException.java
// FECHA CREACIÓN: 2012/10/28
//=======================================================================
package com.zero.vshop.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.ApplicationException;

import org.apache.commons.lang.StringUtils;

import com.zero.vshop.constant.Constants;

/**
 * Clase que representa una excepcion generada en un proceso de negocio en el modulo Hcm
 * @author Hernán Tenjo
 * @version 1.0
 */
@ApplicationException(rollback = true)
public class VShopException extends Exception{
	//Identificador requerido para el proceso de serialización
	private static final long serialVersionUID = 1L;
	//Codigo de error generico	
	public static final int UNKNOWN_EXCEPTION = 1;
	//
	public static final int FIRST_EXCEPTION = 2;
	//Codigo del error que se generó
	protected int errorCode;
	//Lista con los parametros del error
	private List<String> errorParameters = new ArrayList<String>();
	
	/**
	 * Constructor por defecto de la excepcion
	 */
	protected VShopException(){		
	}
	
	/**
	 * Metodo constructor de la excepción
	 * @param errorCode Codigo que identifica a la excepción
	 */
	protected VShopException(int errorCode){
		this.errorCode = errorCode;
	}
		
	/**
	 * Metodo constructor de la excepcion cuando no se desea especificar un codigo de error específico
	 * @param e Excepción original
	 */
	protected VShopException(Throwable e){
		super(e);
	}
	
	/**
	 * Metodo constructor de la excepcion cuando no se desea especificar un codigo de error específico
	 * @param e Excepción original
	 * @param parameters Parametros a reemplazar en el mensaje original
	 */
	protected VShopException(Throwable e, String... parameters){		
		super(e);
		this.errorParameters.addAll(Arrays.asList(parameters));
	}
	
	/**
	 * Metodo constructor de la excepción
	 * @param errorCode Codigo que identifica a la excepción
	 * @param e Traza de la excepción original
	 */
	protected VShopException(int errorCode, Throwable e){
		super(e);
		this.errorCode = errorCode;
	}
	
	/**
	 * Constructor auxiliar cuando solo se requiere de un parámetro 
	 * @param errorCode Codigo que identifica a la excepción
	 * @param parameters Parametro a reemplazar en el mensaje original
	 */
	protected VShopException(int errorCode, String... parameters){
		this(errorCode);
		this.errorParameters.addAll(Arrays.asList(parameters));
	}
	
	/**
	 * Constructor auxiliar cuando solo se requiere de un parámetro 
	 * @param errorCode Codigo que identifica a la excepción
	 * @param e Traza de la excepción original
	 * @param parameters Parametro a reemplazar en el mensaje original
	 */
	protected VShopException(int errorCode, Throwable e, String... parameters){
		this(errorCode, e);
		this.errorParameters.addAll(Arrays.asList(parameters));
	}
	
	/**
	 * Método que permite obtener el código de error de la excepcion
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Metodo que permite transformar el mensaje con comodines original de la excepción con los parametros dados
	 * @return Cadena con el mensaje de la excepcion mas informativa
	 */
	protected String buildMessage() {			
		switch (errorCode) {
		case FIRST_EXCEPTION:
			return getMessage(VShopErrorMessages.UNKNOWN_EXCEPTION);
		default:
			return getMessage(VShopErrorMessages.UNKNOWN_EXCEPTION);
		}
	}

    /**
     * Método que permite asignar parámetros a la excepción, luego que esta ha sido creada
     * @param errorParameters Parámetros del mensaje que será mostrado por la excepción
     */
    public void setErrorParameters(List<String> errorParameters) {
        this.errorParameters = errorParameters;
    }
	
    /**
	 * Metodo que permite reemplzar los comodines de los mensajes por
	 * los textos dados como parámetros
	 * @param message Mensaje que se desea transformar
	 * @return El mensaje con las transformaciones requeridas
	 */
	protected String getMessage(String message){
		if(errorParameters.isEmpty()){
			message = StringUtils.replace(message, Constants.MSG_WILDCARD, Constants.EMPTY_STRING);
		}else{
			for(String param : errorParameters){
				//Si ya se han realizado todos los reemplazos se termina el ciclo
				if(!message.contains(Constants.MSG_WILDCARD)){
					break;
				}
				
				message = StringUtils.replaceOnce(message, Constants.MSG_WILDCARD, param);
			}
			
			//Si aún quedan parámetros para reemplazar, se reemplazan por vacio
			if(!message.contains(Constants.MSG_WILDCARD)){
				message = StringUtils.replace(message, Constants.MSG_WILDCARD, Constants.EMPTY_STRING);
			}
		}
		
		return message;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder();
		message.append("\n::: Se ha presentado una situación inesperada");
		message.append("\n::: Código de error ["+ this.errorCode+"] \n");
		
		if(!StringUtils.isBlank(super.getMessage())){
			message.append(super.getMessage());
		}
		
		return message.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return buildMessage();
	}	
}