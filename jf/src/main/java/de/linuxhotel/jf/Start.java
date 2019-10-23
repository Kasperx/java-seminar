package de.linuxhotel.jf;

import java.util.List;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import de.linuxhotel.jf.dao.JDBCPersonDaoImpl;
import de.linuxhotel.jf.dao.PersonDao;
import de.linuxhotel.jf.persistence.Gender;
import de.linuxhotel.jf.persistence.Person;

public class Start {

	public static void main(String[] args) {
		new Start().run();
	}
	
	void run() {
//		PersonService ps = new PersonService();
//		ps.run();
		
		// cdi
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		PersonService application = container.instance().select(PersonService.class).get();
		application.run();
		weld.shutdown();
	}
}
