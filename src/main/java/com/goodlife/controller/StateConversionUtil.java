package com.goodlife.controller;

import java.util.HashMap;
import java.util.Map;

import com.goodlife.exceptions.StateNotFoundException;

public class StateConversionUtil {

	private Map<String, String> states;

	public StateConversionUtil() {
		super();
		setStates();
	}

	/*
	 * Given a state (e.g. "Illinois", "IL", "il"), returns the correct state code "IL".
	 */
	public String lookUp(String state) throws StateNotFoundException {
		String stateCode = "";
		
		if (state.length() > 2) {
			state = state.toLowerCase();
			stateCode = states.get(state);
			
		} else if (state.length() == 2) {
			state = state.toUpperCase();
			if (states.containsValue(state)) {
				stateCode = state;
			}
		}
		
		if (stateCode == null || stateCode.length() == 0) {
			throw new StateNotFoundException(state + " not found.");
		}
		return stateCode;
	}


	public Map<String, String> getStates() {
		return states;
	}
	
	public void setStates() {
		Map<String, String> states = new HashMap<String, String>();
		states.put("alabama","AL");
		states.put("alaska","AK");
		states.put("alberta","AB");
		states.put("american samoa","AS");
		states.put("arizona","AZ");
		states.put("arkansas","AR");
		states.put("armed forces","AE");
		states.put("armed forces americas","AA");
		states.put("armed forces pacific","AP");
		states.put("british columbia","BC");
		states.put("california","CA");
		states.put("colorado","CO");
		states.put("connecticut","CT");
		states.put("delaware","DE");
		states.put("district Of columbia","DC");
		states.put("florida","FL");
		states.put("georgia","GA");
		states.put("guam","GU");
		states.put("hawaii","HI");
		states.put("idaho","ID");
		states.put("illinois","IL");
		states.put("indiana","IN");
		states.put("iowa","IA");
		states.put("kansas","KS");
		states.put("kentucky","KY");
		states.put("louisiana","LA");
		states.put("maine","ME");
		states.put("manitoba","MB");
		states.put("maryland","MD");
		states.put("massachusetts","MA");
		states.put("michigan","MI");
		states.put("minnesota","MN");
		states.put("mississippi","MS");
		states.put("missouri","MO");
		states.put("montana","MT");
		states.put("nebraska","NE");
		states.put("nevada","NV");
		states.put("new brunswick","NB");
		states.put("new hampshire","NH");
		states.put("new jersey","NJ");
		states.put("new mexico","NM");
		states.put("new york","NY");
		states.put("newfoundland","NF");
		states.put("north carolina","NC");
		states.put("north dakota","ND");
		states.put("northwest territories","NT");
		states.put("nova scotia","NS");
		states.put("nunavut","NU");
		states.put("ohio","OH");
		states.put("oklahoma","OK");
		states.put("ontario","ON");
		states.put("oregon","OR");
		states.put("pennsylvania","PA");
		states.put("prince edward island","PE");
		states.put("puerto rico","PR");
		states.put("quebec","PQ");
		states.put("rhode island","RI");
		states.put("saskatchewan","SK");
		states.put("south carolina","SC");
		states.put("south dakota","SD");
		states.put("tennessee","TN");
		states.put("texas","TX");
		states.put("utah","UT");
		states.put("vermont","VT");
		states.put("virgin islands","VI");
		states.put("virginia","VA");
		states.put("washington","WA");
		states.put("west virginia","WV");
		states.put("wisconsin","WI");
		states.put("wyoming","WY");
		states.put("yukon territory","YT");
		
		this.states = states;
	}

}
