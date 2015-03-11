package unitTests;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;

import pace.logic.*;
import pace.util.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class contextTest {

	static FHIRContext ctx = new FHIRContext();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		String[] input = new String[]{"Patient"};
		
		ctx.setInput(ctx.constructInputString(input,  "/"));
		
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

	/*@Test
	public void test1() {
		
		ctx.getContext();
			
	}
	@Test
	public void test2() {
		try
		{
		
			HttpURLConnection conn = ctx.sendGetRequest();
			System.out.println(ctx.readRespone());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
			
			
		
			
	}
	@Test
	public void test3() {
		try
		{
		
			HttpURLConnection conn = ctx.sendGetRequest();
			FHIRDataParser dp = new FHIRDataParser();
			String input = ctx.readRespone();
			HashMap<String, Object> map = dp.parseJSON(input);
			printMap(map);
			
			System.out.println("Getting submap ");
			HashMap<Integer, String> patients = dp.getPatients(input);
			System.out.println(patients.toString());
			//printMap(patients);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
			
			
		
			
	}
	@Test
	public void test4() {
		try
		{
			//Pace pace = new Pace();
			//pace.init();
			HttpURLConnection conn = ctx.sendGetRequest();
			FHIRDataParser dp = new FHIRDataParser();
			String input = ctx.readRespone();
			HashMap<String, Object> map = dp.parseJSON(input);
			printMap(map);
			
			System.out.println("Getting submap ");
			HashMap<Integer, String> patients = dp.getPatients(input);
			System.out.println(patients.toString());
			//printMap(patients);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
			
			
		
			
	}*/
	
	@Test
	public void test5() {
		try
		{
			Pace pace = new Pace();
			pace.init();
			
			Thread.sleep(600);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void printMap(HashMap map)
	{
		Iterator<String> itr = map.keySet().iterator();
		while(itr.hasNext())
		{
			String s = itr.next();
		
		System.out.println(s + " : " + map.get(s));
		}
	}
	
	

}
