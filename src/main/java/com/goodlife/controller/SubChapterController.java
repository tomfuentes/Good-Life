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

import com.goodlife.dao.SubChapterDAO;
import com.goodlife.model.SubChapter;

@Controller
@RequestMapping(value = "/subchapterlookup")
@Transactional
public class SubChapterController {
	
	static final Logger logger = LogManager.getLogger(SubChapterController.class.getName());
	
	@Autowired
	private SubChapterDAO subChapterDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addsubchapter", method = RequestMethod.POST)
	public String addSubChapter(@RequestParam(value="chapId") Integer chapId,
											 @RequestParam(value="subChapTitle") String subChapTitle,
											 @RequestParam(value="subChapDescr") String subChapDescr,
											 @RequestParam(value="orderId") Integer orderId){
		
		SubChapter subChapter = new SubChapter();
		Date subChapTS = new Date();
		subChapter.setChapId(chapId);
		subChapter.setSubChapTitle(subChapTitle);
		subChapter.setSubChapDescr(subChapDescr);
		subChapter.setOrderId(orderId);
		subChapter.setSubChapTS(subChapTS);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.addSubChapter(subChapter));
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
	@RequestMapping(value = "deletesubchapter", method = RequestMethod.POST)
	public String deleteSubChapter(@RequestParam(value="subchapId") Integer subChapId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.deleteSubChapter(subChapId));
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
	@RequestMapping(value = "listallsubchaptersbychapter", method = RequestMethod.GET)
	public String listAllSubChaptersByChapter(@RequestParam(value="chapId") Integer chapId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.getSubChapListByChapter(chapId));
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
	@RequestMapping(value = "/updatesubchapterorder", method = RequestMethod.GET)
	public String updateSubChapterOrder(@RequestParam(value="newSubChapterOrderList") List<Integer> newSubChapterOrderList){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.updateOrder(newSubChapterOrderList));
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
	@RequestMapping(value = "updatesubchapter", method = RequestMethod.POST)
	public String updateSubChapter(@RequestParam("subChapId") Integer subChapId,
									@RequestParam("chapId") Integer chapId,
									@RequestParam("subChapDescr") String subChapDescr,
									@RequestParam("subChapTitle") String subChapTitle,
									@RequestParam("orderId") Integer orderId,
									@RequestParam("published") Boolean published){
		
		Date subChapTS = new Date();
		SubChapter updatedSubChapter = new SubChapter(subChapId, chapId, subChapDescr, subChapTitle, orderId, published, subChapTS);
		
		Boolean response = subChapterDAO.updateSubChapter(updatedSubChapter);
		
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
	@RequestMapping(value = "updatesubchaptertitle", method = RequestMethod.GET)
	public String updateSubChapterTitle(@RequestParam(value="subChapId") Integer subChapId,
													   @RequestParam(value="subChapTitle") String subChapTitle) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.updateTitle(subChapId,subChapTitle));
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
	@RequestMapping(value = "updatesubchapterdescr", method = RequestMethod.GET)
	public String updateSubChapterDescr(@RequestParam(value="subChapId") Integer subChapId,
													   @RequestParam(value="subChapDescr") String subChapDescr){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.updateDescription(subChapId,subChapDescr));
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
	@RequestMapping(value = "/setsubchapterpublished", method = RequestMethod.GET)
	public String setSubChapterPublished(@RequestParam(value = "subChapId") Integer subChapId,
										 @RequestParam(value = "published") Boolean published){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapterDAO.setPublishSubChapter(subChapId, published));
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
