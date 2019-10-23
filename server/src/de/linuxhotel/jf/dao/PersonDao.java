package de.linuxhotel.jf.dao;

import java.util.List;

import de.linuxhotel.jf.persistence.Person;

public interface PersonDao {
	
	public List <Person> getAll();
	public Person getById(Long id);
	public void saveOrUpdate(Person person);
	public Person deleteById(Long id);
}
