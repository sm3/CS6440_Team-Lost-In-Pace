package pace.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseConfig {
	
	private String url = null;
	private String format = null;
	private String trustStore =null;
	private String trustStorePasswd =null;
	
	

	private static final String path = "./config.txt";
	
	public ParseConfig()
	{
		File file = new File(path);
		
		try
		{
			Scanner scan = new Scanner(file);
			   
	    
			while(scan.hasNext())
			{
				String ln = scan.next();
				System.out.println("Line : " + ln);
				Scanner line = new Scanner(ln);
				line.useDelimiter("=");
				System.out.println("String in the line is " + line);
				String nextLine = line.next();
				if(ln.startsWith("//"))
				{
					System.out.println("ignoring line : " + ln);
					continue;
				}
				else if(nextLine.equals("URL"))
				{
					url=line.next();
					System.out.println("URL from config "+ url);
				}
				else if(nextLine.equals("format"))
				{
					format=line.next();
					System.out.println("format from config "+ format);
				}
				else if(nextLine.equals("trustStore"))
				{
					trustStore=line.next();
					System.out.println("trustStore from config "+ trustStore);
				}
				else if(nextLine.equals("trustStorePassword"))
				{
					trustStorePasswd=line.next();
					System.out.println("trustStorePassword from config "+ trustStorePasswd);
				}


				
				
			}
	    
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public static String getPath() {
		return path;
	}
		
	public String getTrustStore() {
		return trustStore;
	}

	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}

	public String getTrustStorePasswd() {
		return trustStorePasswd;
	}

	public void setTrustStorePasswd(String trustStorePasswd) {
		this.trustStorePasswd = trustStorePasswd;
	}
}
