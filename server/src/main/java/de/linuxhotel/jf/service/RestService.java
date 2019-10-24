package de.linuxhotel.jf.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.linuxhotel.jf.dao.CrudDao;
import de.linuxhotel.jf.dao.JPACrudCompanyDaoImpl;
import de.linuxhotel.jf.dao.PersonDao;
import de.linuxhotel.jf.persistence.Company;
import de.linuxhotel.jf.persistence.Person;
import de.linuxhotel.jf.report.ExcelExport;

@XmlRootElement
@Path("rest")
public class RestService {

	@Inject
	private PersonService personService;
	
	@Inject
	//private JPACrudCompanyDaoImpl companyDao;
	private CrudDao<Company, Long> companyDao;
	
	@Inject
	ExcelExport excelExport;
	
	@Inject
	private PersonDao persondao;
	
	@GET
	@Path("persons")
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes(value = MediaType.APPLICATION_JSON)
	public List <Person> abc(){
		return personService.searchList(null);
	}
	
	@GET
	@Path("personxls")
	@Produces(value = "application/vnd.ms-excel")
	public Response abc3(@Context HttpHeaders h){
		
		Locale l = h.getAcceptableLanguages().get(0);
		ResourceBundle rb = ResourceBundle.getBundle("MessageResources", l);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		excelExport.generateReport(personService.searchList(null), bout);
		ResponseBuilder b = Response.ok(bout.toByteArray());
		b.header("Content-Disposition", "attachment; filename=\"filename.jpg\"");
		return b.build();
	}
	
	@GET
	@Path("companies")
	public List <Company> abc2(){
		return companyDao.getAll();
	}
	
	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response person(Person person) {
		persondao.saveOrUpdate(person);
		return Response.ok(person).build();
	}
}
