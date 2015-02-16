package co.edu.uniandes.simbancario.services;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.models.CreditLine;
import com.google.appengine.api.utils.SystemProperty;
import com.mysql.jdbc.Connection;

import co.edu.uniandes.simbancario.PersistenceManager;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class Service {

	@PersistenceContext(unitName = "testsql")
	EntityManager entityManager;

	@PostConstruct
	public void init() {
		try {
			//PersistenceManager.getInstance().getEntityManagerFactory();
			entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	public CreditLine  getAllCreditLines() {		
		entityManager.getTransaction().begin();
		CreditLine f= new CreditLine();
		f.setInterestRate(0.2);
		
		entityManager.persist(f);
		entityManager.getTransaction().commit();
		
		CreditLine p = entityManager.find(CreditLine.class, new Long(1));

		return p;

	}




}
