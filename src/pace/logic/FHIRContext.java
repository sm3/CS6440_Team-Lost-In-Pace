package pace.logic;

import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.*;

import pace.util.*;

public class FHIRContext {

  private static String url;

    private Pace myPace;
    private static String input;
    private static String format;
    static HttpURLConnection conn = null;
    
    public FHIRContext()
    {
    	ParseConfig pc = new ParseConfig();
    	
    	setUrl(pc.getUrl());
    	if(pc.getFormat().equals("JSON"))
    			setFormat("_format=json");
    	//Because we connect to GT FHIR server using 8443 we need to supply cert info
    	
    	System.setProperty("javax.net.ssl.trustStore",  pc.getTrustStore());
    	System.setProperty("javax.net.ssl.trustStorePassword", pc.getTrustStorePasswd());
    }

 

public static HttpURLConnection getContext() {
	  
	  try
	  {
		  if(getUrl() == null)
		  {
			  System.out.println("Incorrect URL :"+ getUrl());
		  }
		  else
		  {
			  System.out.println(" URL :"+ url);
			 
			  URL target = new URL(url);
			  conn = (HttpURLConnection) target.openConnection();
		  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.toString());
	  }
	  return conn;
  }

  public void setUrl(String url) {
	  this.url = url;
  }

  public static String getUrl() {
	  return url;
  }

public String getInput() {
	return input;
}

public void setInput(String input) {
	this.input = input;
}

public String constructInputString(String[] st, String delim)
{
	String res =null;
	for(String s : st)
	{
		if(res == null)
		{
			 res= "/" + s;
		}
		else
		{
			res = res +"/" +s;
		}
	}
	System.out.println("inputs "+ res);
	return res;
	
}

public String getFormat() {
	return format;
}

public void setFormat(String format) {
	this.format = format;
}

//public File getData(HttpURLConnection conn)
//{
	
	
	//return resFile;
//}

public static HttpURLConnection sendGetRequest()
        throws IOException {
    URL url = new URL(getUrl()+ input + "?" + format);
    conn = (HttpURLConnection) url.openConnection();
    conn.setUseCaches(false);

    conn.setDoInput(true); // set to true to read server's response
    conn.setDoOutput(false); // false for  GET request

    return conn;
}

public static HttpURLConnection sendPostRequest(
        Map<String, String> params) throws IOException {
    URL url = new URL(getUrl());
    conn = (HttpURLConnection) url.openConnection();
    conn.setUseCaches(false);

    conn.setDoInput(true); // true for server returns response

    StringBuffer requestParams = new StringBuffer();

    if (params != null && params.size() > 0) {

        conn.setDoOutput(true); // true for  POST request

        
        Iterator<String> paramIterator = params.keySet().iterator();
        while (paramIterator.hasNext()) {
            String key = paramIterator.next();
            String value = params.get(key);
            requestParams.append(URLEncoder.encode(key, "UTF-8"));
            requestParams.append("=").append(
                    URLEncoder.encode(value, "UTF-8"));
            requestParams.append("&");
        }

        // do POST 
        OutputStreamWriter writer = new OutputStreamWriter(
                conn.getOutputStream());
        writer.write(requestParams.toString());
        writer.flush();
    }

    return conn;
}

public static String readRespone() throws IOException {
    InputStream inputStream = null;
    if (conn != null) {
        inputStream = conn.getInputStream();
    } else {
        throw new IOException("Connection is not established.");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(
            inputStream));

    //read response and store it as a string
    String response ="";
    String line = "";
    while ((line = reader.readLine()) != null) {
    	System.out.println("output " + line);
        response = response + line;
    }
    reader.close();

    return response;
}
 


public static void disconnect() {
    if (conn != null) {
        conn.disconnect();
    }
}


}