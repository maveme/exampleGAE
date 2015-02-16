/*
 * Esta parte se ha tomado de: http://javanotepad.blogspot.com/2007/05/jpa-entitymanagerfactory-in-web.html
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.simbancario;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

/**
 *
 * @author Mauricio
 */
public class PersistenceManager {

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	public static final boolean DEBUG = true;

	private static final PersistenceManager singleton = new PersistenceManager();

	protected EntityManagerFactory emf;

	public static PersistenceManager getInstance() {

		return singleton;
	}


	private PersistenceManager() {
	}

	public EntityManagerFactory getEntityManagerFactory() {

		if (emf == null) {
			createEntityManagerFactory();
		}
		return emf;
	}

	public void closeEntityManagerFactory() {

		if (emf != null) {
			emf.close();
			emf = null;
			if (DEBUG) {
				System.out.println("n*** Persistence finished at " + new java.util.Date());
			}
		}
	}

	protected void createEntityManagerFactory() {

		try{
			Map<String, String> properties = new HashMap();
			properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.GoogleDriver");
			properties.put("javax.persistence.jdbc.url","url");
			// Create a EntityManager which will perform operations on the database.
			this.emf = Persistence.createEntityManagerFactory("testsql", properties);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		if (DEBUG) {
			System.out.println("n*** Persistence started at " + new java.util.Date());
		}
	}

}
