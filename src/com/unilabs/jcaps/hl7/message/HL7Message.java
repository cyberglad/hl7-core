package com.unilabs.jcaps.hl7.message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.unilabs.jcaps.hl7.message.Segment;

/*
 ------------------------------------------------------------------------
 Revisions:
 ==========
 - CE, 1.0, ???, Created
 */

/**
 * 
 * @author engec
 * 
 */
public class HL7Message {

	private static final long serialVersionUID = 1L;

	private String path = "";
	private String name = "";

	private ArrayList<Segment> alHL7Message = null;

	/**
	 * 
	 */
	public HL7Message() {
		alHL7Message = new ArrayList<Segment>();
	}

	/**
	 * Constructor from String Array
	 * 
	 * @param lines
	 */
	private HL7Message(String[] lines) {
		this();
		for (String l : lines) {
			if (l.length() > 0) {
				alHL7Message.add(new Segment(l));
			}
		}

	}

	/**
	 * 
	 */
	public HL7Message(String message) {
		this(message.split("[\\r\\n]+"));
	}

	/**
	 * Return segment message type (MSH, PID, ...)
	 * 
	 * @return Message type
	 */
	public String getMessageType() {
		return alHL7Message.get(0).get(8).toString();
	}

	/**
	 * Return MSH segment
	 * 
	 * @return MSH segment
	 */
	public Segment getMsh() {
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).getValue().equalsIgnoreCase("MSH")) {
				return s;
			}
		}

		return null;
	}

	/**
	 * Return PID segment
	 * 
	 * @return PID segment
	 */
	public Segment getPid() {
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).getValue().equalsIgnoreCase("PID")) {
				return s;
			}
		}

		return null;
	}

	/**
	 * Return PV1 segment
	 * 
	 * @return PV1 segment
	 */
	public Segment getPv1() {
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).getValue().equalsIgnoreCase("PV1")) {
				return s;
			}
		}

		return null;
	}

	/**
	 * 
	 */
	public Segment getOrc() {
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).getValue().equalsIgnoreCase("ORC")) {
				return s;
			}
		}

		return null;
	}

	/**
	 * Return OBR's segments
	 * 
	 * @return OBR's segments
	 */
	public ArrayList<Segment> getObrs() {
		ArrayList<Segment> obrs = new ArrayList<Segment>();
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).getValue().equalsIgnoreCase("OBR")) {
				obrs.add(s);
			}
		}
		return obrs;
	}

	/**
	 * Return OBX's segments
	 * 
	 * @return OBX's segements
	 */
	public ArrayList<Segment> getObxs() {
		ArrayList<Segment> obxs = new ArrayList<Segment>();
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).getValue().equalsIgnoreCase("OBX")) {
				obxs.add(s);
			}
		}
		return obxs;
	}

	/**
	 * Returns the value of a certain field
	 * 
	 * @param segmentName
	 *            , for ex. "OBR"
	 * @param fieldIndex
	 *            , for ex. 3
	 * @return
	 */
	public String getField(String segmentName, int fieldIndex) {
		String elementOBR = "";
		for (Segment s : alHL7Message) {
			if (s.get(0).get(0).toString().equalsIgnoreCase(segmentName)) {
				return s.get(fieldIndex).toString();
			}
		}
		return null;
	}

	/**
	 * Sets the field to a certain value
	 * 
	 * @param segmentName
	 *            , for ex. "OBR"
	 * @param fieldIndex
	 *            , for ex. 3
	 * @param value
	 *            Value to set
	 */
	public void setField(String segmentName, int fieldIndex, String value) {
		String elementOBR = "";
		for (Segment s : alHL7Message) {
			if (s.get(0).toString().equalsIgnoreCase(segmentName)) {
				s.set(fieldIndex, new Field(value));
			}
		}
	}

	/**
	 * 
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		// sb.append("\n*********************");
		// sb.append("\n*** ");
		// sb.append(this.path);
		// sb.append("\n*** ");
		// sb.append(this.name + " - " + getMessageType() );
		//
		// sb.append("\n-----------------------------------\n");

		for (Segment s : alHL7Message) {
			sb.append(s);
		}

		// sb.append("\n=========================================\n\n");

		return sb.toString();
	}

	private void log(String text) {
		System.out.println(text);
	}

}
