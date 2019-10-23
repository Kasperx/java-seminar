package de.linuxhotel.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {

	@Produces
	public EntityManagerFactory producer() {
		return Persistence.createEntityManagerFactory("myPu");
	}
	
}
