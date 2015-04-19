package pace.servlets;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

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
			
			String[] obs_array =  new String[] {"Body Weight", "Body Height", "Systolic BP", "Diastolic BP",
												"Body Temperature", "Heart Beat"};
			
			JSONObject data_obs_json = new JSONObject();
			
			JSONArray weight = new JSONArray();
			JSONArray height = new JSONArray();
			JSONArray systolic_bp = new JSONArray();
			JSONArray diastolic_bp = new JSONArray();
			JSONArray body_temperature = new JSONArray();
			JSONArray heart_beat = new JSONArray();
			
			JSONObject value;
			
			String last_weight = null;
			
			while(itr.hasNext())
			{
				Observation o = itr.next();
				String obs_name = o.getName().getCodingFirstRep().getDisplay().getValue();
				System.out.println("Observation name : " + o.getName().getCodingFirstRep().getDisplay().getValue());
				
				if (Arrays.asList(obs_array).contains(obs_name)){
				
					QuantityDt q = (QuantityDt) o.getValue();
	
					if(q!=null)
					{
						value = new JSONObject();
						value.put("value", q.getValue().getValueAsString());
						System.out.println("Observation value : " + (q.getValue()).getValueAsString());
		
						
						if (obs_name.equals(obs_array[0])){
							last_weight = q.getValue().getValueAsString();
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
							System.out.println("last weight : " + last_weight);
							weight.add(value);
						}
						if (obs_name.equals(obs_array[1])){
							height.add(value);
						}
						if (obs_name.equals(obs_array[2])){
							systolic_bp.add(value);
						}
						if (obs_name.equals(obs_array[3])){
							diastolic_bp.add(value);
						}
						if (obs_name.equals(obs_array[4])){
							body_temperature.add(value);
						}
						if (obs_name.equals(obs_array[5])){
							heart_beat.add(value);
						}
						
					}
				}
			}
			
			data_obs_json.put("weight", weight);
			data_obs_json.put("height", height);
			data_obs_json.put("systolic_bp", systolic_bp);
			data_obs_json.put("diastolic_bp", diastolic_bp);
			data_obs_json.put("body_temperature", body_temperature);
			data_obs_json.put("heart_beat", heart_beat);
			
			request.setAttribute("data_obs_json", data_obs_json);
			
			
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
