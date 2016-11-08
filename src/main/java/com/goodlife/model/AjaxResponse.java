package com.goodlife.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjaxResponse<T> {
	
	private T content;
	
	private final List<String> globalErrors = new ArrayList<String>();
	private final Map<String, String> fieldErrors = new HashMap<String, String>();
	
	public void setContent(final T content){
		this.content = content;
	}
	
	public T getcontent(){
		return content;
	}
	
	public boolean isHasErrors(){
		return !globalErrors.isEmpty() || !fieldErrors.isEmpty();
	}
	
	public List<String> getGlobalErrors(){
		return globalErrors;
	}
	
	public Map<String, String> getFieldErrors(){
		return fieldErrors;
	}

}
