package de.linuxhotel.jf.cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
	
	  @Produces
	  @PersistenceContext(unitName = "myPu")
	  public EntityManager entityManager; 

}
