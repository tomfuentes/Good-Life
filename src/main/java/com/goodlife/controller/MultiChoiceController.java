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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.MultiChoiceListDAO;
import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceList;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;

@Controller
@RequestMapping(value = "/multichoice")
public class MultiChoiceController {
	
	static final Logger logger = LogManager.getLogger(MultiChoiceController.class.getName());
	
	@Autowired
	private MultiChoiceQDAO mcQdao;
	
	@Autowired
	private MultiChoiceListDAO mcLdao;
	
	@Autowired
	private MultiChoiceOptionDAO mcOptdao;
	
	@Autowired
	private MultiChoiceListDAO mcListdao;
	
	@ResponseBody
	@RequestMapping(value = "/addmultichoicelist", method = RequestMethod.GET)
	public String addMultiChoiceList(@RequestParam(value="description") String description,
											 @RequestParam(value="graded") Boolean graded,
											 @RequestParam(value="orderId") Integer orderId,
											 @RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="title") String title) {
		
		Date multiChoiceListTS = new Date();
		MultiChoiceList mcL = new MultiChoiceList();
		mcL.setDescription(description);
		mcL.setGraded(graded);
		mcL.setOrderId(orderId);
		mcL.setPublished(Boolean.FALSE);
		mcL.setSubChapId(subChapId);
		mcL.setTitle(title);
		mcL.setMultiChoiceListTS(multiChoiceListTS);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcLdao.addMultiChoiceList(mcL));
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
	@RequestMapping(value = "/addmultichoicequestion", method = RequestMethod.GET)
	public String addMultiChoiceQuestion(@RequestParam(value="questionText") String questionText,
											 @RequestParam(value="multiChoiceListId") Integer multiChoiceListId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="corrAns", required = false) Integer corrAns,
											 @RequestParam(value="orderId") Integer orderId) {
		
		Date multiChoiceQTS = new Date();
		MultiChoiceQ mcQ = new MultiChoiceQ();
		mcQ.setQuesText(questionText);
		mcQ.setMultiChoiceListId(multiChoiceListId);
		mcQ.setHelpText(helpTxt);
		if(corrAns != null) mcQ.setCorrectAnswer(corrAns);
		mcQ.setOrderId(orderId);
		mcQ.setMultiChoiceQTS(multiChoiceQTS);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.addMultiChoice(mcQ));
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
	@RequestMapping(value = "/addmultichoiceoption", method = RequestMethod.GET)
	public String addMultiChoiceOption(@RequestParam(value="mcQId") Integer mcQId,
											 @RequestParam(value="choiceText") String choiceText) {
		
		Date multiChoiceOptionTS = new Date();
		MultiChoiceOption mcOpt = new MultiChoiceOption();
		mcOpt.setMultiQuesId(mcQId);
		mcOpt.setChoiceText(choiceText);
		mcOpt.setMultiChoiceOptionTS(multiChoiceOptionTS);

		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.addMultiChoiceOption(mcOpt));
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
	@RequestMapping(value = "/deletemultichoiceoption", method = RequestMethod.GET)
	public String deleteMultiChoiceOption(@RequestParam(value="Id") Integer mcOptId) {
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.deleteMultiChoiceOption(mcOptId));
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
	@RequestMapping(value = "/deletemultichoiceques", method = RequestMethod.GET)
	public String deleteMultiChoiceQuestion(@RequestParam(value="Id") Integer mcQId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.deleteMultiChoice(mcQId));
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
	@RequestMapping(value = "/listalloptionsbyquestion", method = RequestMethod.GET)
	public String listAllOptionsByQuestion(@RequestParam(value="quesId") Integer quesId) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.getMultiChoiceOptions(quesId));
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
	@RequestMapping(value = "/updateoptiontext", method = RequestMethod.GET)
	public String updateOptionText(@RequestParam(value="optionId") Integer optionId, 
			@RequestParam(value="optionText") String optionText) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.updateChoiceText(optionId, optionText));
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
	@RequestMapping(value = "/listallquestionbylistid", method = RequestMethod.GET)
	public String listAllQuestionBySubchapter(
			@RequestParam(value="multiChoiceListId") Integer multiChoiceListId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.getAllMultiChoice(multiChoiceListId));
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
	@RequestMapping(value = "/updatemultichoicelist", method = RequestMethod.GET)
	public String updateMultiChoiceList(@RequestParam(value = "multiChoiceListId") Integer multiChoiceListId,
										@RequestParam(value = "description") String description,
										@RequestParam(value = "graded") Boolean graded,
										@RequestParam(value = "orderId") Integer orderId,
										@RequestParam(value = "published") Boolean published,
										@RequestParam(value = "subChapId") Integer subChapId,
										@RequestParam(value = "title") String title){
		
		Boolean response = Boolean.TRUE;
		MultiChoiceList mcList;
		Date multiChoiceListTS = new Date();
		try {
			mcList = mcListdao.getMultiChoiceListById(multiChoiceListId);
			mcList.setDescription(description);
			mcList.setGraded(graded);
			mcList.setOrderId(orderId);
			mcList.setPublished(published);
			mcList.setSubChapId(subChapId);
			mcList.setTitle(title);
			mcList.setMultiChoiceListTS(multiChoiceListTS);
			
			if(mcListdao.addMultiChoiceList(mcList) == null)
				response = Boolean.FALSE;
			
		} catch (MultipleChoiceNotFoundException e1) {
			response = Boolean.FALSE;
			e1.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
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
	@RequestMapping(value = "/updatequestionorder", method = RequestMethod.GET)
	public String updateQuestionOrder(
			@RequestParam(value="multiChoiceIdList") List<Integer> multiChoiceIdList){

		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateOrder(multiChoiceIdList));
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
	public String updateQuestionText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") String quesText){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateQuestionText(multiChoiceId, quesText));
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
	public String updateHelpText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="helpText") String helpText){
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateHelpText(multiChoiceId, helpText));
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
	@RequestMapping(value = "/updatecorrectanswer", method = RequestMethod.GET)
	public String updateCorrectAnswer(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") Integer correctAnswer){
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateCorrectAnswer(multiChoiceId, correctAnswer));
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
	@RequestMapping(value = "/setmultichoiceqpublished", method = RequestMethod.GET)
	public String setMultiChoiceQPublished(@RequestParam(value = "multiChoiceId") Integer multiChoiceId,
										   @RequestParam(value = "published") Boolean published){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.setPublishMultiChoiceQ(multiChoiceId, published));
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
	@RequestMapping(value = "/setmultichoiceoptpublished", method = RequestMethod.GET)
	public String setMultiChoiceOptPublished(@RequestParam(value = "optionId") Integer optionId,
										   @RequestParam(value = "published") Boolean published){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.setPublishMulitChoiceOption(optionId, published));
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
