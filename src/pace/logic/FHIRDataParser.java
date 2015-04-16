package pace.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu.resource.*;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.rest.client.BaseClient;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.rest.server.EncodingEnum;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import javax.lang.model.element.Element;
import javax.xml.parsers.*;

//import org.hl7.fhir.instance.formats.JsonComposer;
//import org.hl7.fhir.instance.formats.JsonParser;
//import org.hl7.fhir.instance.formats.Parser;
//import org.hl7.fhir.instance.formats.XmlComposer;
//import org.hl7.fhir.instance.formats.XmlParser;
//import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
//import org.hl7.fhir.instance.model.Patient;
//import org.hl7.fhir.instance.model.Property;
//import org.hl7.fhir.instance.model.Resource;
//import org.hl7.fhir.instance.*;
//import org.json.JSONObject;
//import org.json;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class FHIRDataParser {

    public Pace  myPace;
    FHIRContext pacectx;
    
    private static final String serverBase = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/";
  	private FhirContext ctx;
  	private IGenericClient client;
    /**
     * Initialize the data parser.
     */
    public FHIRDataParser() {
    	
    	pacectx = new FHIRContext();
    	ctx = new FhirContext();
    	client = ctx.newRestfulGenericClient(serverBase);
    	((BaseClient)client).setEncoding(EncodingEnum.JSON); //prefer JSON encoding
    }
  	
    public FHIRContext getPaceContext()
    {
    	return pacectx;
    }

  public HashMap<String, Object> parseJSON(String inputJson) throws JsonParseException, JsonMappingException, IOException {
	  
	  
	  HashMap<String, Object> map = new HashMap<>();

	    ObjectMapper mapper = new ObjectMapper();
	    map = mapper.readValue(inputJson,
	            new TypeReference<HashMap<String, Object>>() {});

	

	    return map;
  }
  /**
   * Returns a Hashmap of patient data
   * @param inputJson
   * @return
   */
  public HashMap<Integer, String> getPatients(String inputJson) 
  {
	  HashMap<Integer, String> patients = new HashMap<Integer, String>();
	  System.out.println("input to getPatients "+ inputJson);
	  ObjectMapper mapper = new ObjectMapper();
	  //read JSON like DOM Parser
	  JsonNode rootNode;
	  try 
	  {
		  rootNode = mapper.readTree(inputJson);
		

		  JsonNode idNode = rootNode.path("id");
		  System.out.println("id = "+ idNode.asText());
 
		  List<JsonNode> patientNode = rootNode.findValues("title");
		  List<JsonNode> nameNode = rootNode.findValues("div");
		  
		  System.out.println("patients = "+ patientNode.size());
		  Integer count = 1;
		  //div entry has patient names in a table format. Call getPatientName with the xml string
		  for(JsonNode jn : patientNode)
		  {
			  
			
				  if(jn.isTextual())
					  System.out.println("patient = "+ jn.asText());
				  else if(jn.isInt())
					  System.out.println("patient = "+ jn.asInt());
				  
				  patients.put(count, getPatientName(nameNode.get(count-1).asText()));
			  
			  count++;
		  }
		  System.out.println("Done with patient data");
	  } catch (Exception e) 
	  {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  return patients;
  }
  /**
   * Gets patient conditions by parsing input string
   * @param patientID
   * @param inputJson
   * @return returns a hashmap of  condition code and name.
   */
  public HashMap<String, String> getPatientConditions(String patientID, String inputJson)
  {
		
		
		HashMap<String, String> conditions = new HashMap<>();
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		  //read JSON like DOM Parser
		  JsonNode rootNode;
		  try 
		  {
			  rootNode = mapper.readTree(inputJson);
			 
			

			  JsonNode idNode = rootNode.path("id");
			  System.out.println("id = "+ idNode.asText());
	 
			  List<JsonNode> conditionNode = rootNode.findValues("title");
			  List<JsonNode> nameNode = rootNode.findValues("code");
			  
			  System.out.println("conditions = "+ conditionNode.size());
			  Integer count = 1;
			  //div entry has patient names in a table format. Call getPatientName with the xml string
			  for(JsonNode jn : nameNode)
			  {
				  
				  System.out.println(jn.toString());
				      JsonNode codingNode = jn.findValue("coding");
				      System.out.println(codingNode.toString());
				      JsonNode codeNode = codingNode.findValue("code");
				      JsonNode displayNode = codingNode.findValue("display");
				      //JsonNode statusNode = conditionNode.get(0).findValue("status");
				      //JsonNode onsetNode = conditionNode.get(0).findValue("onsetDate");
				
					  
					 conditions.put("code", codeNode.asText());
					 conditions.put("name", displayNode.asText());
					 
				  count++;
			  }
			  System.out.println("Done with condition data");
			  System.out.println(conditions.toString());
		  } catch (Exception e) 
		  {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  
		  return conditions;
		
  }
  
  /**
   * Parses input string for elements and constructs Observation list. 
   * @param patientID
   * @param inputJson
   * @returns ArrayList of HashMaps containing observation information
   */
  
  public ArrayList<HashMap<String, String>> getPatientObservations(String patientID, String inputJson)
  {
	 	
	
		
		ArrayList<HashMap<String, String>> patientObservations = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		  //read JSON like DOM Parser
		  JsonNode rootNode;
		  try 
		  {
			  rootNode = mapper.readTree(inputJson);
			 
			

			  JsonNode idNode = rootNode.path("id");
			  System.out.println("id = "+ idNode.asText());
	 
			 // List<JsonNode> conditionNode = rootNode.findValues("content");
			  List<JsonNode> nameNode = rootNode.findValues("content");
			  
			  System.out.println("conditions = "+ nameNode.size());
			  System.out.println("name node = "+ nameNode.toString());
			  Integer count = 1;
			  //div entry has patient names in a table format. Call getPatientName with the xml string
			  for(JsonNode jn : nameNode)
			  {
				  HashMap<String, String> observations = new HashMap<>();
				  	  System.out.println(jn.toString());
				      JsonNode codingNode = jn.findValue("coding");
				      System.out.println(codingNode.toString());
				      JsonNode codeNode = codingNode.findValue("code");
				      JsonNode displayNode = codingNode.findValue("display");
				      JsonNode valueQNode = jn.findValue("valueQuantity");
			 
				      
				      List<JsonNode> statusNodes = jn.findValues("status");
				      JsonNode statusNode = statusNodes.get(1);
				      System.out.println("statusNode" + statusNode.asText());
					  if(statusNode !=null && statusNode.asText().equals("final"))
					  {
						 observations.put("code", codeNode.asText());
						 observations.put("name", displayNode.asText());
						 if(valueQNode !=null)
						 {
							 JsonNode valueNode = valueQNode.findValue("value");
							 JsonNode unitNode = valueQNode.findValue("units");
							 observations.put("value", valueNode.asText());
							 if (unitNode!=null) 
								 observations.put("units", unitNode.asText());
						 }
						 patientObservations.add(observations);
					 }
					 
					 
					 
				  count++;
			  }
			  System.out.println("Done with observations data");
			  for(HashMap<String, String> hmap : patientObservations)
			  {
				  System.out.println(hmap.toString());
			  }
			 
		  } catch (Exception e) 
		  {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  
		  return patientObservations;
		
  }
  
  /**
   * JSON string response for getPatients has a xml snippet that contains name. This 
   * function extracts the name.
   * @param xml
   * @return
   */
  
  public String getPatientName(String xml)
  {
	  String res = null;
	  try {
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc;
		InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
       


		doc = dBuilder.parse(is);
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nl = doc.getElementsByTagName("td");
		
		
		System.out.println("Element "+((Object) nl.item(1)));
		res = ((Object) nl.item(1)).toString();
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  return res;

  }
  
/**
 * getPatient -uses HAPI
 * @param id
 * @return
 */

  
	public Patient getPatientById(String id) {
		IdDt ptid = new IdDt(id);
      return (Patient) client.read((Class<? extends IResource>) Patient.class, ptid);
	}
	
/**
 * gets all patients - users HAPI
 * @return
 */
	public List<Patient> getAllPatients() {
		
		
      Bundle response = client
              .search()
              .forResource((Class<? extends IResource>) Patient.class)
              .execute();
      
      System.out.println("Bundle :" + response.toString());
      List<Patient> patients = (List<Patient>) response.getResources((Class<? extends IResource>) Patient.class);
      while (!response.getLinkNext().isEmpty()) {
     	   // load next page
     	   response = client.loadPage().next(response).execute();
     	   patients.addAll((Collection<? extends Patient>) response.getResources((Class<? extends IResource>) Patient.class));
     	}
      return patients;
	}
	/**
	 * gets a condition using id - users HAPI
	 * @return
	 */
	public Condition getConditionById(String id) {
		IdDt condid = new IdDt(id);
      return client.read(Condition.class, condid);
	}

	/**
	 * gets all conditions for a patient - users HAPI
	 * @return
	 */
	public List<Condition> getAllConditionsForPatient(String id) {
		
	    Bundle response = client
	    		.search()
	    		.forResource(Condition.class)
	    		.where(Condition.SUBJECT.hasId(id))
	    		.execute();
		
		
	    List<Condition> conditions = response.getResources(Condition.class);
	    while (!response.getLinkNext().isEmpty()) {
  	   // load next page
  	   response = client.loadPage().next(response).execute();
  	   conditions.addAll(response.getResources(Condition.class));
  	}
	    return conditions;
	}
	/**
	 * gets observation by id - users HAPI
	 * @return
	 */
	public Observation getObservationById(String id) {
		IdDt obsid = new IdDt(id);
      return client.read(Observation.class, obsid);
	}
	/**
	 * gets all observations for a patient - users HAPI
	 * @return
	 */
	public List<Observation> getAllObservationsForPatient(String id) {
	    Bundle response = client
	    		.search()
	    		.forResource(Observation.class)
	    		.where(Observation.SUBJECT.hasId(id))
	    		.execute();
	   
	  
	    List<Observation> observations = response.getResources(Observation.class);
  	while (!response.getLinkNext().isEmpty()) {
  	   // load next page
  	   response = client.loadPage().next(response).execute();
  	   observations.addAll(response.getResources(Observation.class));
  	}
  	return observations;
	}

	 
}