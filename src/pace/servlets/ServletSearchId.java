package pace.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pace.logic.FHIRContext;
import pace.logic.FHIRDataParser;

/**
 * Servlet implementation class ServletSearchId
 */
@WebServlet("/ServletSearchId")
public class ServletSearchId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String doctor = request.getParameter("doctor");
		String search_id = request.getParameter("search_id");
		String name = null;
		
		FHIRContext ctx = new FHIRContext();
		String[] input = new String[]{"Patient"};
		ctx.setInput(ctx.constructInputString(input,  "/?&_count=100"));
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			ca.uhn.fhir.model.dstu.resource.Patient p = dp.getPatientById(search_id);
			System.out.println("Get patient by ID : "+ p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep());
			name = p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep();
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (name == null){
			String error = "Patient ID: " + search_id + " is not valid";
			request.setAttribute("doctors", doctor);
			request.setAttribute("search_id", search_id);
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("doctors", doctor);
			request.setAttribute("search_id", search_id);
			request.setAttribute("name", name);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}
	}

}
