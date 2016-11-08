package com.goodlife.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.goodlife.model.AjaxResponse;

@Named
public class AjaxResponseBuilder {
	
	@Inject
	private MessageSource messageSource;
	
	public MessageSource getMessageSource(){
		return messageSource;
	}
	
	public void setMessageSource(final MessageSource messageSource){
		this.messageSource = messageSource;
	}
	
	public <T> AjaxResponse<T> createSuccessResponse(final T content){
		final AjaxResponse<T> response = new AjaxResponse<T>();
		response.setContent(content);
		return response;
	}
	
	public <T> AjaxResponse<T> createErrorResponse(final BindingResult result) {
		final AjaxResponse<T> response = new AjaxResponse<T>();
		if(result.hasErrors()){
			for(final ObjectError error : result.getGlobalErrors()){
				response.getGlobalErrors().add(
						getMessageSource().getMessage(error.getCode(), error.getArguments(), null));
			}
			for(final FieldError fieldError : result.getFieldErrors()){
				response.getFieldErrors().put(fieldError.getField(),
						getMessageSource().getMessage(fieldError.getCode(), fieldError.getArguments(), null));
			}
			
		}
		return response;
	}

}
