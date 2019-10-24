package server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.Test;

import de.linuxhotel.jf.dao.PersonDao;
import de.linuxhotel.jf.persistence.Person;
import de.linuxhotel.jf.service.PersonService;

public class DispatcherServletTest {

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		DispatcherServlet s = new DispatcherServlet();
		HttpServletRequest request = EasyMock.niceMock(HttpServletRequest.class);
		RequestDispatcher d = EasyMock.createMock(RequestDispatcher.class);
		List <Person> list = new ArrayList();
		list.add(new Person());
		
		s.dao = new PersonDao() {
			
			@Override
			public void saveOrUpdate(Person person) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Person getById(Long id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Person> getAll() {
				return list;
			}
			
			@Override
			public Person deleteById(Long id) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		try {
			EasyMock.expect(request.getRequestDispatcher("/WEB-INF/personlist.jsp")).andReturn(d);
			d.forward(request, null);
			request.setAttribute("personlist", list);
			EasyMock.replay(request);
			s.doPost(request, null);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EasyMock.verify(request, d);
		fail("Not yet implemented");
	}

}
