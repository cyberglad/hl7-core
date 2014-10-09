package com.unilabs.jcaps.hl7.message;

import java.util.ArrayList;

/**
 * 
 * @author EngeC
 * 
 */
public class Segment extends ArrayList<Field> {
	
	private static final long serialVersionUID = 1L;

	public Segment() {

	}

	public Segment(String line) {

		Field field = null;
		String[] fields = line.split("\\|",-1);
		for (String f : fields) {
			field = new Field(f);
			add(field);			
		}

	}
	
	public String getSegmentType() {	
		return this.get(0).get(0).toString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
/*		sb.append("\n********** ");
		sb.append(getSegmentType() + "\n");*/

		int i = 0;
		for (Field f : this) {
			if (i > 0) {
				sb.append("|");
			}
			sb.append(f);
			i++;
		}
		sb.append("\n");
		return sb.toString();

	}
}
