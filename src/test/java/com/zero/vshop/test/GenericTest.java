//=======================================================================
// ARCHIVO GenericTest.java
// FECHA CREACIÓN: Sep 30, 2009
//=======================================================================
package com.zero.vshop.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.zero.vshop.service.VShopServiceFacade;

/**
 * Clase base para todas las pruebas que impliquen levantar
 * el servidor de aplicaciones ejb embebido
 * @author Hernán Tenjo
 * @version 1.0
 */
public abstract class GenericTest {
	private static final String CONTEXT_INITIAL_FACTORY_NAME = "java.naming.factory.initial";
	private static final String CONTEXT_INITIAL_FACTORY = "org.apache.openejb.client.LocalInitialContextFactory";
	private static final String OPEN_EJB_CONFIGURATION_NAME = "openejb.configuration";
	private static final String OPEN_EJB_CONFIGURATION = "target/test-classes/openejb.xml";
	private static final String OPEN_EJB_DEPLOY_AS_EAR_NAME = "openejb.deployments.VShop.ear";
	private static final String DATA_SOURCE_PARAM_NAME = "vshopPU.jta-data-source";
	private static final String HIBERNATE_EXPORT_PARAM_NAME = "vshopPU.hibernate.hbm2ddl.auto";
	private static final String DATA_SOURCE_TEST_PARAM_VALUE = "vshop_ds_test";
	private static final String HIBERNATE_EXPORT_TEST_PARAM_VALUE = "create-drop";
	private static final String DATA_SOURCE_PROD_PARAM_VALUE = "vshop_ds";
	private static final String HIBERNATE_EXPORT_PROD_PARAM_VALUE = "validate";
	protected static final String OPEN_EJB_JNDI_REMOTE_SUFIX = "Remote";
	protected static final String OPEN_EJB_JNDI_LOCAL_SUFIX = "LocalBean";
	protected static Context context;
	private static boolean USE_PRODUCTION_DB_FOR_TESTING = false;
	//Objeto que facilita la ejecucion de las pruebas
	protected static TestHelper helper;
	//Rutas para la gestion dinamica de los persistence.xml para pruebas
	protected static VShopServiceFacade service;
		
	/**	
	 * Metodo que permite iniciar al cargar la clase, las variables
	 * requeridas para probar la funcionalidad del servicio
	 * {@link IPersistentEMService}
	 * @throws Exception Si se produce un error al crear el contexto JNDI
	 */
	@BeforeClass
	public static void initContainer() throws Exception{
		context = new InitialContext(getConfiguration(USE_PRODUCTION_DB_FOR_TESTING));
	    service = (VShopServiceFacade)context.lookup(VShopServiceFacade.class.getSimpleName() + OPEN_EJB_JNDI_LOCAL_SUFIX);
	    helper = new TestHelper(service);
	}
	
	/**
	 * Metodo que permite finalizar adecuadamente todos los recursos
	 * utilizados en las pruebas. 
	 * @throws Exception Si se produce un error al cerrar el contexto JNDI
	 */
	@AfterClass
	public static void closeContainer() throws Exception{
		context.close();
	}
	
	public static Properties getConfiguration(boolean useProductionConfiguration){
		Properties properties = new Properties();
		//Determina la fabrica a utilizar para el contenedor embebido
		properties.put(CONTEXT_INITIAL_FACTORY_NAME, CONTEXT_INITIAL_FACTORY);
		//Archivo de configuración de OpenEJB
		properties.put(OPEN_EJB_CONFIGURATION_NAME, OPEN_EJB_CONFIGURATION);
		//Determina si se va a tomar el classpath como un EAR para el despliegue
		properties.put(OPEN_EJB_DEPLOY_AS_EAR_NAME, Boolean.TRUE.toString());
				
		if(useProductionConfiguration){
			buildProductionConfiguration(properties);
		}else{
			buildTestConfiguration(properties);
		}
		
		return properties;
	}
	
	/**
	 * Método que construye la configuración requerida para realizar las pruebas en una
	 * base de datos de producción (Base de datos poblada con información real).
	 * NOTA: Esta configuración sólo deberá utilizarce para pruebas temporales
	 * @param properties Objeto donde serán guardadas las propiedades de configuración
	 */
	private static void buildProductionConfiguration(Properties properties){
		//Permite la creación de un datasource transaccional para acceso a BD
		properties.put("vshop_ds", "new://Resource?type=DataSource");
		//Determina el driver de conexion a la BD, en nuestro caso PostgreSQL
		properties.put("vshop_ds.JdbcDriver", "org.postgresql.Driver");
		//Cadena de conexion a la BD
		properties.put("vshop_ds.JdbcUrl", "jdbc:postgresql://localhost:5432/vshop");
		//Usuario con el que se va a loguear en la BD
		properties.put("vshop_ds.UserName", "postgres");
		//Clave del usuario con el que se intenta conectar a la BD
		properties.put("vshop_ds.Password", "postgres");
		properties.put(DATA_SOURCE_PARAM_NAME, DATA_SOURCE_PROD_PARAM_VALUE);
		properties.put(HIBERNATE_EXPORT_PARAM_NAME, HIBERNATE_EXPORT_PROD_PARAM_VALUE);
	}
	
	/**
	 * Método que construye la configuración requerida para realizar las pruebas en 
	 * una base de datos de pruebas (en blanco)
	 * @param properties Objeto donde serán guardadas las propiedades de configuración
	 */
	private static void buildTestConfiguration(Properties properties){
		//Permite la creación de un datasource transaccional para acceso a BD
		properties.put("vshop_ds_test", "new://Resource?type=DataSource");
		//Determina el driver de conexion a la BD, en nuestro caso PostgreSQL
		properties.put("vshop_ds_test.JdbcDriver", "org.postgresql.Driver");
		//Cadena de conexion a la BD
		properties.put("vshop_ds_test.JdbcUrl", "jdbc:postgresql://localhost:5432/vshop_test");
		//Usuario con el que se va a loguear en la BD
		properties.put("vshop_ds_test.UserName", "postgres");
		//Clave del usuario con el que se intenta conectar a la BD
		properties.put("vshop_ds_test.Password", "postgres");
		properties.put(DATA_SOURCE_PARAM_NAME, DATA_SOURCE_TEST_PARAM_VALUE);
		properties.put(HIBERNATE_EXPORT_PARAM_NAME, HIBERNATE_EXPORT_TEST_PARAM_VALUE);
	}
}