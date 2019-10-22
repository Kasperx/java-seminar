package de.linuxhotel.jf.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.linuxhotel.jf.persistence.Person;

@Alternative
public class JPAPersonDaoImpl implements PersonDao {
	
	@Inject
	private EntityManager em;
	
	@Override
	public List<Person> getAll() {
		return em.createQuery("select p from Person p", Person.class).getResultList();
	}

	@Override
	public Person getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Person person) {
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
	}

	@Override
	public Person deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
