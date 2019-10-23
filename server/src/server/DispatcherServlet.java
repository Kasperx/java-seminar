package server;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.linuxhotel.jf.service.PersonService;

@WebServlet (urlPatterns = "*.html")
public class DispatcherServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PersonService personservice;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("personlist", personservice.searchList(request.getParameter("q")));
		request.getRequestDispatcher("/WEB-INF/personlist.jsp").forward(request, response);
	}
	
	

}
