package com.goodlife.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.model.ShortAnswerQ;

@Controller
@RequestMapping(value = "/shortAnswerQuestion")
@Transactional
public class ShortAnswerQuestionController {
	
	static final Logger logger = LogManager.getLogger(ShortAnswerQuestionController.class.getName());
	
	@Autowired
	private ShortAnswerQDAO shortAnsDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addshortanswerquestion", method = RequestMethod.GET)
	public String addShortAnswerQuestion(@RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="descr") String descr,
											 @RequestParam(value="orderId") Integer orderId) {
		
		Date shortAnsQTS = new Date();
		ShortAnswerQ shortAnswerQ = new ShortAnswerQ();
		shortAnswerQ.setSubChapId(subChapId);
		shortAnswerQ.setHelpText(helpTxt);
		shortAnswerQ.setOrderId(orderId);
		shortAnswerQ.setShortAnsQTS(shortAnsQTS);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnsDAO.addShortAnswerQuestion(shortAnswerQ));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatequestiontext", method = RequestMethod.GET)
	public String updateQuestionText(@RequestParam(value="shortAnsQId") Integer shortAnsQId, 
			@RequestParam(value="quesText") String quesText)  {
		
		shortAnsDAO.updateQuestionText(shortAnsQId, quesText);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnsDAO.updateQuestionText(shortAnsQId, quesText));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public String updateHelpText(@RequestParam(value="shortAnsQId") Integer shortAnsQId, 
			@RequestParam(value="helpTxt") String helpText)  {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnsDAO.updateHelpText(shortAnsQId, helpText));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/allquestionsbysubchapter") 
	public String allShortAnswerQuestionsBySubchapId(
			@RequestParam(value="subChapId") Integer subChapId)  {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnsDAO.getShortAnswerBySubChapter(subChapId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateorder", method = RequestMethod.GET)
	public String updateOrder(@RequestParam(value="shortAnsQIdList") 
			List<Integer> shortAnsQIdList)  {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnsDAO.updateOrderId(shortAnsQIdList));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setshortanswerqpublished", method = RequestMethod.GET)
	public String setShortAnswerQPublished(@RequestParam(value = "saQId") Integer saQId,
										   @RequestParam(value = "published") Boolean published){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnsDAO.setPublishShortAnswer(saQId, published));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
}
