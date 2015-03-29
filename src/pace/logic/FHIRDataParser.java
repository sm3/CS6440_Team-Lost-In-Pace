package pace.logic;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import javax.lang.model.element.Element;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class FHIRDataParser {

    public Pace  myPace;

  public HashMap parseJSON(String inputJson) throws JsonParseException, JsonMappingException, IOException {
	  
	  
	  HashMap<String, Object> map = new HashMap<>();

	    ObjectMapper mapper = new ObjectMapper();
	    map = mapper.readValue(inputJson,
	            new TypeReference<HashMap<String, Object>>() {});

	

	    return map;
  }
  
  public HashMap getPatients(String inputJson) 
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
		
		
		System.out.println("Element "+nl.item(1).getTextContent());
		res = nl.item(1).getTextContent();
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  return res;

  }
 
  

}