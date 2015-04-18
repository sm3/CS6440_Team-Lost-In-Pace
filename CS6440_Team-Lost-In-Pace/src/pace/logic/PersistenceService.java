package pace.logic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.resource.Condition;
import ca.uhn.fhir.model.dstu.resource.Observation;
import ca.uhn.fhir.model.dstu.resource.Patient;
import pace.util.EntityManagerFactory;
public class PersistenceService {

    
    public void getPatientData()
    {
    	try 
    	{
    		
    		FHIRDataParser dp = new FHIRDataParser();
    		
    	
    		// Get all Patients
    		List<Patient> patients = dp.getAllPatients();
    		EntityManager em  = EntityManagerFactory.createEntityManager();

    
    		// Read the existing entries and write to console
    		em.getTransaction().begin();
    
    		for (Patient p : patients) {
    			PacePatient pp = new PacePatient();
    			pp.setPatientID(p.getIdentifierFirstRep().getValue().getValue());
    			pp.setFirstName(p.getName().get(0).getGivenFirstRep().getValue());
    			pp.setLastName(p.getName().get(0).getFamilyFirstRep().getValue());   
    			em.persist(pp);
    		}
    		System.out.println("Size: " + patients.size());

    
    		em.getTransaction().commit();

    		em.close();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    	
    public void getPatientConditions()
    {
    	try
    	{
    		FHIRDataParser dp = new FHIRDataParser();
    		FHIRContext pacectx = dp.getPaceContext();
		
		
    		List<Patient> patients = dp.getAllPatients();
    		EntityManager em  = EntityManagerFactory.createEntityManager();

        
    		// Read the existing entries and write to console
    		em.getTransaction().begin();
        
    		for (Patient p : patients) {
        	
    			//add conditions
    			String input ="/Condition?subject=Patient/"+p.getIdentifierFirstRep().getValue().getValue() +"&_format=json&_count=100";
    			pacectx.sendGetRequest(input);
    			HashMap<String, String> conditions = dp.getPatientConditions(p.getIdentifierFirstRep().getValue().getValue(), pacectx.readRespone());
    			if(conditions.size()>0)
    			{
    				for (String o : conditions.keySet())
    				{
    					ChronicDisease c = new ChronicDisease();
    					c.setDiseaseID(o.toString());
    					c.setName(conditions.get(o));
            		
    					System.out.println("inserting chronic condition "+ c.getDiseaseID() +","+ c.getName());
    					em.persist(c);
    				}
    			}
    			
    		}
    		System.out.println("Size: " + patients.size());
    		em.getTransaction().commit();

    		em.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
   }

    public void getPatientObservations() 
    {
    	try
    	{
    		FHIRDataParser dp = new FHIRDataParser();
    		FHIRContext pacectx = dp.getPaceContext();
    		
    		
    		List<Patient> patients = dp.getAllPatients();
            EntityManager em  = EntityManagerFactory.createEntityManager();

            
            // Read the existing entries and write to console
            em.getTransaction().begin();
            
            for (Patient p : patients) 
            {
    	
            //add observations //"Patient/"
        	String input ="/Observation?subject=Patient/"+p.getIdentifierFirstRep().getValue().getValue() +"&_format=json&_count=100";
        	pacectx.sendGetRequest(input);
        	ArrayList<HashMap<String, String>> observations = dp.getPatientObservations(p.getIdentifierFirstRep().getValue().getValue(), pacectx.readRespone());
    	
        	if(observations.size()>0)
        	{
    	
        		for (HashMap<String, String> o : observations)
        		{
    			
        			LabTest lt = new LabTest();
        			if(o.get("code")!=null)
        			{
        				lt.setTestID(o.get("code"));
        				lt.setName(o.get("name"));
        				if(o.get("value") !=null)
        				{
        					lt.setValue(Double.parseDouble(o.get("value")));
        					if(o.get("units")!=null)
        					{
        						lt.setUnit(o.get("units"));
        					}
        				}
    			
        			}
        			System.out.println("inseting lab test "+ lt.getId() +", " + lt.getName() +"," + lt.getUnit() + "," + lt.getValue());
        			em.persist(lt);
    			
        		}
        	}
			
            }
        em.getTransaction().commit();
        System.out.println("Size: " + patients.size());

        em.close();
    }
    catch (Exception e)
    {
    	e.printStackTrace();
    }
}
	
    
    public void getObservations() 
    {
    	try
    	{
    		FHIRDataParser dp = new FHIRDataParser();
   
    		List<Patient> patients = dp.getAllPatients();
    		EntityManager em  = EntityManagerFactory.createEntityManager();

    
    		// Read the existing entries and write to console
    		em.getTransaction().begin();
    
    		for (Patient p : patients) {
    	
    			//add observations //"Patient/"
    			List<Observation> observations = dp.getAllObservationsForPatient("Patient/"+p.getIdentifierFirstRep().getValue().getValue());
    	
    			if(observations.size()>0)
    			{
    	
    				for (Observation o : observations)
    				{
    					
    					LabTest lt = new LabTest();
    					lt.setId(o.getName().getCodingFirstRep().getCode().getValue());
    					lt.setName(o.getName().getCodingFirstRep().getDisplay().getValue());
    		 
    					QuantityDt quan = (QuantityDt) o.getValue();
    					if(quan.getUnits() !=null)
    						lt.setUnit(quan.getUnits().getValue());
    					lt.setValue(Double.parseDouble(quan.getValue().getValueAsString()));
    					System.out.println("inseting lab test "+ lt.getId() +", " + lt.getName() +"," + lt.getUnit() + "," + lt.getValue());
    					em.persist(lt);
    					
    				}
    			}
			
    		}
    		System.out.println("Size: " + patients.size());
		
    		em.getTransaction().commit();
    		em.close();
    	}
    catch (Exception e)
    {
    	e.printStackTrace();
    }
}
	
    

    public void getConditions()
    {
    	try
    	{
    	FHIRDataParser dp = new FHIRDataParser();
     
        
        
        List<Patient> patients = dp.getAllPatients();
        EntityManager em  = EntityManagerFactory.createEntityManager();

        
        // Read the existing entries and write to console
        em.getTransaction().begin();
        
        for (Patient p : patients) {
        	
        	//add conditions
        	List<Condition> conditions = dp.getAllConditionsForPatient(p.getIdentifierFirstRep().getValue().getValue());
            if(conditions.size()>0)
            {
            	for (Condition o : conditions)
            	{
            		ChronicDisease c = new ChronicDisease();
            		c.setDiseaseID(o.getCode().getCodingFirstRep().getCode().getValue());
            		c.setName(o.getCode().getCodingFirstRep().getDisplay().getValue());
            		//c.setName(o.getCode().getCodingFirstRep().getCode().getValue());
            		System.out.println("inserting chronic condition "+ c.getDiseaseID() +","+ c.getName());
            		em.persist(c);
            	}
            }
    			
        }
        System.out.println("Size: " + patients.size());

        
        em.getTransaction().commit();

        em.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    

    

    }

}