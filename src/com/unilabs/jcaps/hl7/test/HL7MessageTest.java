package com.unilabs.jcaps.hl7.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.unilabs.jcaps.hl7.message.Field;
import com.unilabs.jcaps.hl7.message.HL7Message;
import com.unilabs.jcaps.hl7.message.Segment;

/**
 * Unit Test for <class>HL7Message</class>
 * 
 * @author engec
 * 
 */
public class HL7MessageTest {
	static HL7Message hl7Message;
	// Linux
	// static String testFile =
	// "/home/christian/Documents/Unilabs/HL7/test2.hl7";
	// Window
	static String testFile = "C:\\Temp\\Jade\\temp\\ORU_0000258863_0003757_00001.hl7";

	int testOBRField = 3;
	String testOBRValue = "TEST";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hl7Message = new HL7Message(load(testFile));
		System.out.println(hl7Message.toString());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		hl7Message = null;
	}

	/*
	 * @Test public void customTest() { Field f = hl7Message.get(0).get(1);
	 * log(f.toString()); }
	 */

	@Test
	public void testMessage() throws Exception {
		log("*** testMessage");
		assertNotNull(hl7Message);
		log(hl7Message.toString());

	}

	@Test
	public void testMSH() throws Exception {
		log("*** testMSH");

		Segment s = hl7Message.getMsh();
		assertNotNull(s);
		log(" => " + s.toString());
		assertEquals("MSH", s.getSegmentType());

	}

	@Test
	public void testPID() throws Exception {
		log("*** testPID");

		Segment s = hl7Message.getPid();
		assertNotNull(s);
		log(" => " + s.toString());
		assertEquals("PID", s.getSegmentType());

	}

	public void testPV1() throws Exception {
		log("*** testPV1");

		Segment s = hl7Message.getPv1();
		assertNotNull("PV1 Missing", s);
		log(" => " + s.toString());
		assertEquals("PV1", s.getSegmentType());

	}

	@Test
	public void testOBR() throws Exception {
		log("*** testObr");

		ArrayList<Segment> obrs = hl7Message.getObrs();
		assertNotNull("OBR Missing", obrs.get(0));
		// log(obrs.get(0).toString());

		for (Segment s : obrs) {
			assertEquals("OBR", s.getSegmentType());
			log(" => " + s.toString());
		}

	}

	// @Test
	public void testOBX() throws Exception {
		log("*** testOBX");

		ArrayList<Segment> obxs = hl7Message.getObxs();
		assertNotNull("OBX Missing", obxs.get(0));

		for (Segment s : obxs) {
			assertEquals("OBX", s.getSegmentType());
			log(" => " + s.toString());
		}

	}

	@Test
	public void testReplaceOBR() throws Exception {
		log("*** testReplaceOBR");

		ArrayList<Segment> obrs = hl7Message.getObrs();
		assertNotNull("OBR Missing", obrs.get(0));

		log(hl7Message.getField("OBR", 3));
		hl7Message.setField("OBR", 3, "TEST");
		log(hl7Message.getField("OBR", 3));

	}

	/**
	 * Load Message from file
	 * 
	 * @param path
	 *            Path to file
	 * @throws Exception
	 */
	public static String load(String path) throws Exception {

		String hl7Message = "";
		File f = new File(path);
		if (!f.exists()) {
			throw new Exception("File not exist: " + path);
		}

		String name = new File(path).getName();

		String enc = "ISO-8859-1";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), enc));

		Segment segment = null;
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.length() > 0) {
				hl7Message += line;
				hl7Message += "\r";
			}
		}

		reader.close();
		return hl7Message;
	}

	private void log(String text) {
		System.out.println(text);
	}

}
