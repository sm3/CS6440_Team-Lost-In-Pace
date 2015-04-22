package pace.servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
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

import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.resource.Condition;
import ca.uhn.fhir.model.dstu.resource.Observation;
import pace.logic.FHIRContext;
import pace.logic.FHIRDataParser;
import pace.logic.Pace;
import pace.logic.PacePatient;
import pace.logic.PersistenceService;
import pace.util.ColorScheme;
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
			
			JSONObject json_tests = new JSONObject();
			JSONArray test_arr = new JSONArray();
			JSONObject test;
			
			JSONObject json_colors = new JSONObject();
			JSONArray color_arr = new JSONArray();
			JSONObject color;
			
			
			JSONObject pat_ids = new JSONObject();
			
			
			String[] obs_array =  new String[] {"BNP", "MRI", "CKMB", "ECG", "Chest X-Ray",
					"CT Chest", "LDL", "HDL", "HbA1c", "Protein Urine", "Sodium Urine",
					"FDG PET CT Scan", "TSH" };
			
			ColorScheme color_value = new ColorScheme();
			
			ArrayList duplicate_test = new ArrayList();
			
			
			int i = 0;
			
			while(itr.hasNext())
			{
				if (i >= 20){
					break;
				}
				i+=1;
				
				ca.uhn.fhir.model.dstu.resource.Patient p = itr.next();
				
				//pat = new JSONObject();
	    		String pat_name = p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep();
	    		//pat.put("patientname", pat_name);
	    		//pats.add(pat);
	    			    		
	    		System.out.println("++++++++++++++++++Printing++++++++++++++++++");				
				System.out.println("Patient name: " + p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep());
				System.out.println("Patient id : " + p.getIdentifierFirstRep().getValue());
				
				String p_id = p.getIdentifierFirstRep().getValue().toString();
				
				//pat_ids.put(pat_name, p_id);
				
				try {				 
					List<Observation> obs = dp.getAllObservationsForPatient("Patient/" + p_id);
					Iterator<Observation> obs_itr = obs.iterator();
					while(obs_itr.hasNext())
					{
						Observation o = obs_itr.next();
						String obs_name = o.getName().getCodingFirstRep().getDisplay().getValue();
						
						if (Arrays.asList(obs_array).contains(obs_name)){
							
							//test = new JSONObject();
							//test.put("value", obs_name);
							//test_arr.add(test);
							System.out.println("Observation name : " + o.getName().getCodingFirstRep().getDisplay().getValue());
				    		
							QuantityDt q = (QuantityDt) o.getValue();
	
							if(q!=null)
							{
								if (!duplicate_test.contains(obs_name + " - " + pat_name)) {
									System.out.println("Observation value : " + (q.getValue().getValueAsString()));
									int my_color = color_value.getColorValue(obs_name, q.getValue().getValueAsString());
									System.out.println("Color: " + my_color);
									
									pat = new JSONObject();
									pat.put("patientname", obs_name + " - " + pat_name);
						    		pats.add(pat);
						    		
						    		pat_ids.put(obs_name + " - " + pat_name, p_id);
									
									test = new JSONObject();
									test.put("value", obs_name);
									test_arr.add(test);
									
									color = new JSONObject();
									color.put("value", Integer.toString(my_color));
									color_arr.add(color);
									
									duplicate_test.add(obs_name + " - " + pat_name);
								}
							}
							//break;
						}
					}	
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			json.put("Patients", pats);
			request.setAttribute("json", json);
			
			request.setAttribute("pat_ids", pat_ids);
			
			json_tests.put("Tests", test_arr);
			request.setAttribute("json_tests", json_tests);
			
			json_colors.put("Colors", color_arr);
			request.setAttribute("json_colors", json_colors);
						
			
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
