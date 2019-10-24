package de.linuxhotel.jf.service;

import java.util.List;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.linuxhotel.jf.dao.JDBCPersonDaoImpl;
import de.linuxhotel.jf.dao.PersonDao;
import de.linuxhotel.jf.persistence.Company;
import de.linuxhotel.jf.persistence.Gender;
import de.linuxhotel.jf.persistence.Person;

public class PersonService {

	@Inject
	private PersonDao dao;
	
	@Inject
	private Logger logger;
	
	void run() {
		// Dao = Data Access Object
		// PersonDao dao = new TestPersonDaoImpl();
		//PersonDao dao = new JDBCPersonDaoImpl();
		// dao.saveOrUpdate(person);
		
		Person person = new Person("Christian", "Glass", 195, Gender.M);
		
		Company company = new Company();
		company.setName("netzcaktor");
		person.setCompany(company);
		
		dao.saveOrUpdate(person);
		dao.saveOrUpdate(new Person("Kevin", "Straus", 203, Gender.M));

		for (Person temp : dao.getAll()) {
			System.out.println(temp.toString());
		}
	}

	public List <Person> searchList(String query) {
		
		Person person = new Person("Christian", "Glass", 195, Gender.M);
		Company company = new Company();
		company.setName("netzcaktor");
		person.setCompany(company);
		dao.saveOrUpdate(person);
		
		dao.saveOrUpdate(new Person("Kevin", "Straus", 203, Gender.M));
		
		//Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Klasse: "+dao.getClass());
	
		return dao.getAll();
	}
}
