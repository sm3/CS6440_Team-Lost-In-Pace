package pace.servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pace.logic.FHIRContext;
import pace.logic.FHIRDataParser;
import pace.logic.Pace;
import pace.logic.PacePatient;
import pace.logic.PersistenceService;
import pace.util.EntityManagerFactory;

/**
 * Servlet implementation class ServletOne
 */
@WebServlet("/ServletOne")
public class ServletOne extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String doctor = request.getParameter("doctor");
		
		FHIRContext ctx = new FHIRContext();
		String[] input = new String[]{"Patient"};
		ctx.setInput(ctx.constructInputString(input,  "/?&_count=100"));
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			List<ca.uhn.fhir.model.dstu.resource.Patient> pts = dp.getAllPatients();
			System.out.println("Total patients : "+ pts.size());
			Iterator<ca.uhn.fhir.model.dstu.resource.Patient> itr = pts.iterator();
			
			JSONObject json = new JSONObject();
			JSONArray pats = new JSONArray();
			JSONObject pat;
			
			JSONObject pat_ids = new JSONObject();
			
			
			int i = 0;
			
			while(itr.hasNext())
			{
				ca.uhn.fhir.model.dstu.resource.Patient p = itr.next();
				
				pat = new JSONObject();
	    		String pat_name = p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep();
	    		pat.put("patientname", pat_name);
	    		//pat.put("patient_id", p.getIdentifierFirstRep().getValue().toString());
	    		pats.add(pat);
	    		
	    		System.out.println("++++++++++++++++++Printing++++++++++++++++++");				
				System.out.println("Patient name: " + p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep());
				System.out.println("Patient id : " + p.getIdentifierFirstRep().getValue());
				
				
				
				pat_ids.put(pat_name, p.getIdentifierFirstRep().getValue().toString());
				
				
				if (i >= 130){
					break;
				}
				i+=1;
				
			}
			
			json.put("Patients", pats);
			request.setAttribute("json", json);
			
			request.setAttribute("pat_ids", pat_ids);
			
						
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		request.setAttribute("doctors", doctor);
		RequestDispatcher rd = request.getRequestDispatcher("treemap.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
