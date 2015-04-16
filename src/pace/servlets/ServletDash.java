package pace.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pace.logic.FHIRDataParser;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.resource.Observation;

/**
 * Servlet implementation class ServletDash
 */
@WebServlet("/ServletDash")
public class ServletDash extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String doctor = request.getParameter("doctor");
		String name = request.getParameter("name");
		String pat_id = request.getParameter("pat_id");
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			List<Observation> obs = dp.getAllObservationsForPatient("Patient/" + pat_id);
			System.out.println("Total Observations : "+ obs.size());
			Iterator<Observation> itr = obs.iterator();
			while(itr.hasNext())
			{
				Observation o = itr.next();
				System.out.println("Observation code: " + o.getName().getCodingFirstRep().getCode().getValue());
				System.out.println("Observation name : " + o.getName().getCodingFirstRep().getDisplay().getValue());
				
				QuantityDt q = (QuantityDt) o.getValue();

				if(q!=null)

				{

				System.out.println("Observation value : " + (q.getValue()).getValueAsString());

				System.out.println("Observation units : " + q.getUnits());

				}				
			}									
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("doctors", doctor);
		request.setAttribute("name", name);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
		
	}

}
