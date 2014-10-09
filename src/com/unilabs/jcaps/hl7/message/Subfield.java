package com.unilabs.jcaps.hl7.message;

import java.io.Serializable;

/**
 * 
 * @author EngeC
 *
 */
public class Subfield implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String value = "";

	public Subfield() {
		
	}
	
	public Subfield(String subfield) {
		this.value = subfield;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		return this.value;
	}
 	
}
