package unitTests;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pace.logic.*;
import pace.util.*;
import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.IPrimitiveDatatype;
import ca.uhn.fhir.model.dev.resource.Patient;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.resource.*;
import ca.uhn.fhir.model.dstu.valueset.AdministrativeGenderCodesEnum;
import ca.uhn.fhir.model.primitive.BoundCodeableConceptDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
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

	
	/*
	
	
	@Test
	public void testDBPatients() {
		try
		{
			Pace pace = new Pace();
			pace.init();
			PersistenceService ps = new PersistenceService();
			EntityManagerFactory emf = new EntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			//ps.getPatientData();
			//ps.getPatientConditions();
			//ps.getPatientObservations();
			
			Thread.sleep(1200);
			
			
			   Class.forName("org.h2.Driver");
			    Connection conn = DriverManager.getConnection("jdbc:h2:./db/PACE", "sa", "");
			    
			    if(conn.isValid(1000))
			    {
			    	System.out.println("CONNECTED" + '\n');
			    	TypedQuery<PacePatient> query = em.createNamedQuery(
							"PacePatient.findByLastName", PacePatient.class);
			    	query.setParameter("name", "Love");
			    	List<PacePatient> patients = query.getResultList();
			    	for(PacePatient p : patients)
			    	{
			    		System.out.println("id " +  p.getPatientID() + " , Name : " + p.getFirstName() + ", " + p.getLastName());
			    	
			    	}
			   
			    }
			    
			    Thread.sleep(1200);
			    
			    pace.stopServer();

			    
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
			List<ca.uhn.fhir.model.dstu.resource.Patient> pts = dp.getAllPatients();
			System.out.println("Total patients : "+ pts.size());
			Iterator<ca.uhn.fhir.model.dstu.resource.Patient> itr = pts.iterator();
			while(itr.hasNext())
			{
				ca.uhn.fhir.model.dstu.resource.Patient p = itr.next();
				System.out.println("Patient name: " + p.getName().get(0).getFamilyFirstRep() + ", " +  p.getName().get(0).getGivenFirstRep());
				System.out.println("Patient id : " + p.getIdentifierFirstRep().getValue());
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
			ca.uhn.fhir.model.dstu.resource.Patient p = dp.getPatientById("Patient/1.1");
			System.out.println("Get patient by ID : "+ p.getName().toString());
			
			System.out.println("Patient birth date: " + p.getBirthDate().getValueAsString());

			System.out.println("Patient gender : " + p.getGenderElement().getText().getValue());
			
			
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
		
	}

	
	*/
	
	@Test
	public void testgetPatientMedication() {
		
		
		try {
			 
			FHIRDataParser dp = new FHIRDataParser();
			List<MedicationPrescription> med_pres = dp.getMedicationsForPatient("Patient/"+resid);
			System.out.println("Total Observations : "+ med_pres.size());
			Iterator<MedicationPrescription> itr = med_pres.iterator();
			while(itr.hasNext())
			{
				MedicationPrescription m = itr.next();
				System.out.println("MedicationPrescription Resource name: " + m.getResourceName());
				System.out.println("MedicationPrescription Date Written : " + m.getDateWrittenElement().getValueAsString());		
			}
			
			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		

}
