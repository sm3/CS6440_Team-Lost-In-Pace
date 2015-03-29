package pace.servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

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
		ctx.setInput(ctx.constructInputString(input,  "/"));
		try
		{
			HttpURLConnection conn = ctx.sendGetRequest();
			FHIRDataParser dp = new FHIRDataParser();
			String result = ctx.readRespone();
			HashMap<String, Object> map = dp.parseJSON(result);
			
			System.out.println("Getting submap ");
			HashMap<Integer, String> patients = dp.getPatients(result);
			System.out.println("++++++++++++++++++Printing++++++++++++++++++");
			
			JSONObject json = new JSONObject();
			JSONArray pats = new JSONArray();
			JSONObject pat;
			
			for (Integer key : patients.keySet()) {
				pat = new JSONObject();
				pat.put("patientname", patients.get(key));
				pats.add(pat);
				System.out.println(key);
				System.out.println(patients.get(key));
			}
			
			json.put("Patients", pats);
			request.setAttribute("json", json);
						
			
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
