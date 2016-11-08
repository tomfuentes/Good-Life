package com.goodlife.controller;

import java.io.IOException;
import java.util.Date;

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

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.model.UploadFileQ;

@Controller
@Transactional
@RequestMapping(value = "/uploadQuestion")
public class UploadQuestionController {
	
	static final Logger logger = LogManager.getLogger(UploadQuestionController.class.getName());
	
	@Autowired
	private UploadFileQDAO uploadDAO;
	
	@ResponseBody
	@RequestMapping(value = "/adduploadfilequestion", method = RequestMethod.GET)
	public String addUploadFileQuestion(@RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="descr") String descr){
											 //@RequestParam(value="orderId") Integer orderId) {
		
		UploadFileQ uploadFileQ = new UploadFileQ();
		Date uploadFileQTS = new Date();
		uploadFileQ.setDescription(descr);
		uploadFileQ.setSubChapId(subChapId);
		uploadFileQ.setHelpText(helpTxt);
		//uploadFileQ.setOrderId(orderId);
		uploadFileQ.setUploadFileQTS(uploadFileQTS);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.addUploadFileQuestion(uploadFileQ));
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
	@RequestMapping(value = "/deleteuploadfilequestion", method = RequestMethod.GET)
	public String deleteUploadFileQuestion(@RequestParam(value="uploadQuesId") Integer uploadQuesId){
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.deleteUploadFileQuestion(uploadQuesId));
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
	@RequestMapping(value = "/updatedescription", method = RequestMethod.GET)
	public String updateDescription(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="descr") String descr){
						
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.updateDescription(uploadQuesId, descr));
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
	public String updateHelpText(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="helpTxt") String helpText){
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.updateHelpText(uploadQuesId, helpText));
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
	@RequestMapping(value = "/getuploadquestionbysubchapter") 
	public String getUploadQuestionBySubchapId(@RequestParam(value="subChapId") Integer subChapId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.getUploadFileQuestion(subChapId));
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
	@RequestMapping(value = "/setuploadquestionpublished", method = RequestMethod.GET)
	public String setUploadQuestionPublished(@RequestParam(value="uploadQuesId") Integer uploadQuesId,
											  @RequestParam(value="published") Boolean published){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.setPublishedUploadFileQ(uploadQuesId, published));
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
