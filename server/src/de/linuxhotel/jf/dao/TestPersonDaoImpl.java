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

import de.linuxhotel.jf.persistence.Person;

@Alternative
public class TestPersonDaoImpl implements PersonDao {

	private Map <Long, Person> pseudodb = new HashMap<Long, Person>();
	private long counter;
	
	@SuppressWarnings("unchecked")
	public TestPersonDaoImpl() {
		
		if (new File("/var/tmp/data").exists())
		
		try (ObjectInputStream oout = new ObjectInputStream(new FileInputStream("/var/tmp/data"));) {
			pseudodb = (Map <Long, Person>) oout.readObject();
			counter = pseudodb.size();
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Person> getAll() {
		ArrayList <Person> r = new ArrayList<Person>();
		r.addAll(pseudodb.values());
		return r;
	}

	@Override
	public Person getById(Long id) {
		return pseudodb.get(id);
	}

	@Override
	public void saveOrUpdate(Person person) {
		if (person.getId() == null)
			person.setId(++counter);
		pseudodb.put(person.getId(), person);

		if (new File("/var/tmp/data").exists())
		// ARM = Automatic Resource Management since 1.7
		try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("/var/tmp/data"));) {
			oout.writeObject(pseudodb);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person deleteById(Long id) {
		return pseudodb.remove(id);
	}

	
}
