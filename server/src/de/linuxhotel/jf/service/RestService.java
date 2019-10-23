package de.linuxhotel.jf.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

import de.linuxhotel.jf.dao.CrudDao;
import de.linuxhotel.jf.dao.JPACrudCompanyDaoImpl;
import de.linuxhotel.jf.persistence.Company;
import de.linuxhotel.jf.persistence.Person;

@XmlRootElement
@Path("rest")
public class RestService {

	@Inject
	private PersonService personService;
	
	@Inject
	//private JPACrudCompanyDaoImpl companyDao;
	private CrudDao<Company, Long> companyDao;
	
	@GET
	@Path("persons")
	public List <Person> abc(){
		return personService.searchList(null);
	}
	
	@GET
	@Path("companies")
	public List <Company> abc2(){
		return companyDao.getAll();
	}
}
