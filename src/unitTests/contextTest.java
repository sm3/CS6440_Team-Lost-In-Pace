package unitTests;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pace.logic.*;
import pace.util.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu.resource.*;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.rest.client.BaseClient;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.server.EncodingEnum;
import ca.uhn.fhir.*;

public class contextTest {

	static FHIRContext ctx = new FHIRContext();
	String resid = "3.568001602-01";
	String condid = "250.01";
	String obsid = "17856-6";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		String[] input = new String[]{"Patient"};
		
		ctx.setInput(ctx.constructInputString(input,  "/?&_count=100"));
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	
	
	@Test
	public void testDBPatients() {
		try
		{
			Pace pace = new Pace();
			pace.init();
			PersistenceService ps = new PersistenceService();
			EntityManagerFactory emf = new EntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			ps.getPatientData();
			ps.getPatientConditions();
			//ps.getPatientObservations();
			
			Thread.sleep(1200);
			
			
			   Class.forName("org.h2.Driver");
			    Connection conn = DriverManager.getConnection("jdbc:h2:./db/PACE", "sa", "");
			    
			    if(conn.isValid(1000))
			    {
			    	System.out.println("CONNECTED" + '\n');
			    	TypedQuery<PacePatient> query = em.createNamedQuery(
							"PacePatient.findAll", PacePatient.class);
			    	List<PacePatient> patients = query.getResultList();
			    	for(PacePatient p : patients)
			    	{
			    		System.out.println("id " +  p.getPatientID() + " , Name : " + p.getFirstName() + ", " + p.getLastName());
			    	
			    	}
			   
			    }
			    
			    Thread.sleep(1200);

			    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	
	@Test
	public void testgetAllPatients()
	{
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			List<Patient> pts = dp.getAllPatients();
			System.out.println("Total patients : "+ pts.size());
			Iterator<Patient> itr = pts.iterator();
			while(itr.hasNext())
			{
				Patient p = itr.next();
				System.out.println("Patient name: " + p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep());
				System.out.println("Patient id : " + p.getIdentifierFirstRep().getValue().getValue());
			}
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testgetPatientCondition()
	{
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			List<Condition> cond = dp.getAllConditionsForPatient(resid);
			System.out.println("Total conditions : "+ cond.size());
			Iterator<Condition> itr = cond.iterator();
			while(itr.hasNext())
			{
				Condition c = itr.next();
				System.out.println(c.toString());
				System.out.println("Condition code: " + c.getCode().getCodingFirstRep().getCode().getValue());
				System.out.println("Condition name : " + c.getCode().getCodingFirstRep().getDisplay().getValue());
			}
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testGetPatientByID()
	{
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			Patient p = dp.getPatientById("Patient/3.568001602-01");
			System.out.println("Get patient by ID : "+ p.getName().toString());
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Test
	public void testgetPatientObservation()
	{
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			List<Observation> obs = dp.getAllObservationsForPatient("Patient/"+resid);
			System.out.println("Total Observations : "+ obs.size());
			Iterator<Observation> itr = obs.iterator();
			while(itr.hasNext())
			{
				Observation o = itr.next();
				System.out.println("Observation code: " + o.getName().getCodingFirstRep().getCode().getValue());
				System.out.println("Observation name : " + o.getName().getCodingFirstRep().getDisplay().getValue());
			}
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	

}
