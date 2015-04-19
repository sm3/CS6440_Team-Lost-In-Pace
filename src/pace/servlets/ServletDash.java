package pace.servlets;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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
import ca.uhn.fhir.model.dstu.resource.MedicationPrescription;
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
			
			int i = 0;
						
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
						
						if (i >= 75){
							break;
						}
						i+=1;
						
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
			
			
			JSONObject data_meds_json = new JSONObject();
			
			JSONArray medication = new JSONArray();
			JSONArray date_written = new JSONArray();
			JSONArray prescriber = new JSONArray();
			JSONArray status = new JSONArray();
			
			JSONObject med_value;
			JSONObject date_value;
			JSONObject pres_value;
			JSONObject stat_value;
			
			//JSONObject total = new JSONObject();
			
			List<MedicationPrescription> p = dp.getAllPrescriptionsForPatient(pat_id);
			
			//total.put("value", p.size());
			System.out.println("Total Medications : "+ p.size());
			
			Iterator<MedicationPrescription> m_itr = p.iterator();
			
			int j = 0;
			//List<String> med_names = new ArrayList<String>();
			
			while(m_itr.hasNext())
			{
				MedicationPrescription o = m_itr.next();
				
				String med = o.getMedication().getDisplay().getValue();
				
				//if (!Arrays.asList(med_names).contains(med)){
			
					med_value = new JSONObject();
					med_value.put("value", med);
					medication.add(med_value);
					System.out.println("Medication name : " + o.getMedication().getDisplay().getValue());
					
					date_value = new JSONObject();
					date_value.put("value", o.getDateWrittenElement().getValueAsString());
					date_written.add(date_value);
					System.out.println("Date Written : " + o.getDateWrittenElement().getValueAsString());
					
					pres_value = new JSONObject();
					pres_value.put("value", o.getPrescriber().getDisplay().getValue());
					prescriber.add(pres_value);
					System.out.println("Prescriber : " + o.getPrescriber().getDisplay().getValue());
					
					stat_value = new JSONObject();
					stat_value.put("value", o.getStatus().getValue());
					status.add(stat_value);
					System.out.println("Status : " + o.getStatus().getValue());
					
					if (j > 3){
						break;
					}
					j+=1;
					
					//med_names.add(med);
				
				//}
				
			}
			
			data_meds_json.put("medication", medication);
			data_meds_json.put("date_written", date_written);
			data_meds_json.put("prescriber", prescriber);
			data_meds_json.put("status", status);
			
			//data_meds_json.put("total", total);
			
			request.setAttribute("data_meds_json", data_meds_json);
			
			
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
