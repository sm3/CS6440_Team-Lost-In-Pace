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
import pace.util.ColorScheme;
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
		String name = "";
		String pat_id = request.getParameter("pat_id");
		String test = request.getParameter("test");
		String color_number = request.getParameter("color");
		String color = "";
		
		String test1_value = "";
		String test2_value = "";
		String test3_value = "";
		String test4_value = "";
		
		String test1_color = "";
		String test2_color = "";
		String test3_color = "";
		String test4_color = "";
		
		String test1_units = "";
		String test2_units = "";
		String test3_units = "";
		String test4_units = "";
		
		String test1_name = "";
		String test2_name = "";
		String test3_name = "";
		String test4_name = "";
			
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			
			ca.uhn.fhir.model.dstu.resource.Patient p_by_id = dp.getPatientById("Patient/" + pat_id);
			name = p_by_id.getName().get(0).getFamilyFirstRep() + ", " +  p_by_id.getName().get(0).getGivenFirstRep();
			
			List<Observation> obs = dp.getAllObservationsForPatient("Patient/" + pat_id);
			System.out.println("Total Observations : "+ obs.size());
			Iterator<Observation> itr = obs.iterator();
			
			String[] obs_array =  new String[] {"Body Weight", "Body Height", "Systolic BP", "Diastolic BP",
												"Body Temperature", "Heart Beat"};
			
			String[] tests_array =  new String[] {"BNP", "MRI", "CKMB", "ECG", "Chest X-Ray",
					"CT Chest", "LDL", "HDL", "HbA1c", "Protein Urine", "Sodium Urine",
					"FDG PET CT Scan", "TSH" };
			
			ArrayList max_tests = new ArrayList();

			
			JSONObject data_obs_json = new JSONObject();
			
			JSONArray weight = new JSONArray();
			JSONArray height = new JSONArray();
			JSONArray systolic_bp = new JSONArray();
			JSONArray diastolic_bp = new JSONArray();
			JSONArray body_temperature = new JSONArray();
			JSONArray heart_beat = new JSONArray();
			
			JSONObject value;
			
			ColorScheme color_value = new ColorScheme();
			
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
						System.out.println("Observation value : " + (q.getValue().getValueAsString()));		
						
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
						
						if (i >= 50){
							break;
						}
						i+=1;
						
					}
				}
				
				if (Arrays.asList(tests_array).contains(obs_name)){
					
					QuantityDt q_t = (QuantityDt) o.getValue();
	
					if(q_t != null)
					{
						if (!max_tests.contains(obs_name)) {
							if (max_tests.size() == 0){
								test1_value = q_t.getValue().getValueAsString();
								test1_color = color_value.colorValueToName(color_value.getColorValue(obs_name, q_t.getValue().getValueAsString()));
								test1_units = q_t.getUnits().getValueAsString();
								test1_name = obs_name;
								max_tests.add(obs_name);
							}
							else if (max_tests.size() == 1){
								test2_value = q_t.getValue().getValueAsString();
								test2_color = color_value.colorValueToName(color_value.getColorValue(obs_name, q_t.getValue().getValueAsString()));
								test2_units = q_t.getUnits().getValueAsString();
								test2_name = obs_name;
								max_tests.add(obs_name);
							}
							else if (max_tests.size() == 2){
								test3_value = q_t.getValue().getValueAsString();
								test3_color = color_value.colorValueToName(color_value.getColorValue(obs_name, q_t.getValue().getValueAsString()));
								test3_units = q_t.getUnits().getValueAsString();
								test3_name = obs_name;
								max_tests.add(obs_name);
							}
							else if (max_tests.size() == 3){
								test4_value = q_t.getValue().getValueAsString();
								test4_color = color_value.colorValueToName(color_value.getColorValue(obs_name, q_t.getValue().getValueAsString()));
								test4_units = q_t.getUnits().getValueAsString();
								test4_name = obs_name;
								max_tests.add(obs_name);
							}
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
				if (j >= 3){
					break;
				}
				j+=1;
				
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
		
		
		request.setAttribute("test1_value", test1_value);
		request.setAttribute("test2_value", test2_value);
		request.setAttribute("test3_value", test3_value);
		request.setAttribute("test4_value", test4_value);
		
		request.setAttribute("test1_color", test1_color);
		request.setAttribute("test2_color", test2_color);
		request.setAttribute("test3_color", test3_color);
		request.setAttribute("test4_color", test4_color);
		
		request.setAttribute("test1_name", test1_name);
		request.setAttribute("test2_name", test2_name);
		request.setAttribute("test3_name", test3_name);
		request.setAttribute("test4_name", test4_name);
		
		request.setAttribute("test1_units", test1_units);
		request.setAttribute("test2_units", test2_units);
		request.setAttribute("test3_units", test3_units);
		request.setAttribute("test4_units", test4_units);
		
		request.setAttribute("doctors", doctor);
		request.setAttribute("name", name);
		//request.setAttribute("test", test);
		//request.setAttribute("color", color);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
		
	}

}
