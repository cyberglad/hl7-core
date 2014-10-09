package com.unilabs.jcaps.hl7.message;

import java.util.ArrayList;

/**
 * 
 * @author EngeC
 *
 */
public class Field extends ArrayList<Subfield> {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Field() {
		
	}
	
	/**
	 * 
	 * @param field
	 */
	public Field(String field) {
		Subfield sub = null;
		String[] subfields = field.split("\\^",-1);
		for (String f : subfields) {
			sub = new Subfield(f);
			add(sub);			
		}

	}
	
	/**
	 * 
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
			
		for (int i = 0; i < this.size(); i++) {
			if (this.size() > 1 && i < this.size()- 1) {
				sb.append(this.get(i) + "^");
			} else {
				sb.append(this.get(i));
			}			
		}
		
		return sb.toString();
	}
}
