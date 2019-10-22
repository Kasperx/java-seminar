package de.linuxhotel.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

	@Inject
	private EntityManagerFactory emf;
	
	@Produces
	public EntityManager produce() {
		return emf.createEntityManager();
	}
}
