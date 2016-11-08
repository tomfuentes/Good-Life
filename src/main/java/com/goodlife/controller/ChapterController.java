package com.goodlife.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.exceptions.UploadPathException;
import com.goodlife.model.Chapter;
import com.goodlife.model.ChapterPage;
import com.goodlife.model.Node;
import com.goodlife.model.ObjectPair;
import com.goodlife.model.SubChapter;
import com.goodlife.model.Tree;

@Controller
@RequestMapping(value = "/chapterlookup")
@Transactional
public class ChapterController {
	
	static final Logger logger = LogManager.getLogger(ChapterController.class.getName());
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Autowired
	private ChapterPageDAO chapterPageDAO;
	
	@Autowired
	private SubChapterDAO subChapterDAO;
	
	private static final String UPLOAD_DIR = "/resources/images/chapter_pages";
	@ResponseBody
	@RequestMapping(value = "/addchapter", method = RequestMethod.POST)
	public String addChapter(@RequestParam(value="chapTitle") String chapTitle,
											 @RequestParam(value="chapDescr") String chapDescr,
											 @RequestParam(value="orderId") String orderId){
		logger.debug("inside add chapter");
		
		Date chapTS = new Date();

		Chapter chapter = new Chapter();
		chapter.setChapTitle(chapTitle);
		chapter.setChapDescr(chapDescr);
		chapter.setOrderId(Integer.valueOf(orderId));
		chapter.setPublished(false);
		chapter.setChapTS(chapTS);
		
		Integer response = 0;
		response = chapterDAO.addChapter(chapter);
		
		logger.debug("response - add chapter: "+ response);

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
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		logger.error("hitting the chapter homepage");
		return "curriculum/chapterHome";
	}
	
	@ResponseBody
	@RequestMapping(value = "/publishchapter", method = RequestMethod.GET)
	public String publishChapter(@RequestParam(value="chapId") Integer chapId,
												@RequestParam(value="published") Boolean published){
		
		Boolean response = chapterDAO.updatePublished(chapId,published);
		
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
	@RequestMapping(value = "/deletechapter", method = RequestMethod.POST)
	public String deleteChapter(@RequestParam(value="chapId") Integer chapId){
		
		Boolean response = chapterDAO.deleteChapter(chapId);
		
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
	@RequestMapping(value = "/listpublishedchapters", method = RequestMethod.GET)
	public String listPublishedChapters() {
		
		List<Chapter> allPublishedChapterList = chapterDAO.listAllPublishedChapters();
				
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(allPublishedChapterList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	/*
	 * 
	 */
	@ResponseBody
	@RequestMapping(value ="listcurriculum", method = RequestMethod.GET)
	public String listCurriculum(){
		
		List<Tree<Object>> treeList = new ArrayList<Tree<Object>>();
		List<Chapter> chapterList = chapterDAO.listAllChapters();
		List<SubChapter> subChapList;
		Tree<Object> tree;
		Node<Object> node;
		Node<Object> child;
		
		for(int i = 0; i < chapterList.size(); i++){
			subChapList = subChapterDAO.getSubChapListByChapter(chapterList.get(i).getChapId());
			if(subChapList == null)
				subChapList = new ArrayList<SubChapter>();
			tree = new Tree<Object>();
			node = new Node<Object>(chapterList.get(i));
			tree.setRoot(node);
			
			for(int j = 0; j < subChapList.size(); j++){
				child = new Node<Object>(subChapList.get(j));
				node.addChild(child);
			}
			treeList.add(tree);
		}
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(treeList);
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
	@RequestMapping(value = "/listsavedchapterdrafts", method = RequestMethod.GET)
	public String listAllChapterDrafts() {
		
		List<Chapter> allSavedChapterDraftsList = chapterDAO.listAllChapterDrafts();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(allSavedChapterDraftsList);
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
	@RequestMapping(value = "/updatechapter", method = RequestMethod.POST)
	public String updateChapter(@RequestParam(value="chapId") Integer chapId,
								@RequestParam(value="chapDescr") String chapDescr,
								@RequestParam(value="chapTitle") String chapTitle,
								@RequestParam(value="orderId") Integer orderId,
								@RequestParam(value="published") Boolean published){
		Date chapTS = new Date();
		
		Chapter updatedChapter = new Chapter(chapId, chapDescr, chapTitle, orderId, chapTS, published);
		
		Boolean response = chapterDAO.updateChapter(updatedChapter);
		
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
	@RequestMapping(value = "/updatechapterorder", method = RequestMethod.GET)
	public String updateChapterOrder(@RequestParam(value="newChapterOrderList")List<Integer> newChapterOrderList){
				
		Boolean response = chapterDAO.updateOrder(newChapterOrderList);
		
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
	@RequestMapping(value = "updatechaptertitle", method = RequestMethod.GET)
	public String updateChapterTitle(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapTitle")String chapTitle){
		
		Boolean response = chapterDAO.updateTitle(chapId, chapTitle);
		
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
	@RequestMapping(value = "updatechapterdescr", method = RequestMethod.GET)
	public String updateChapterDescr(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapDescr")String chapDescr){
		
		Boolean response = chapterDAO.updateDescr(chapId, chapDescr);
		
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
	@RequestMapping(value = "addchapterpage", method = RequestMethod.GET)
	public String addChapterPage(@RequestParam(value="chapId") Integer chapId,
			 									@RequestParam(value="pageNum") Integer pageNum,
			 									@RequestParam(value="pageUrl") String pageUrl,
			 									@RequestParam(value="file") MultipartFile mpfile,
			 									HttpSession session) throws ChapterNotFoundException, UploadPathException{

		/*ChapterPage chapterPage = new ChapterPage();
		chapterPage.setChapId(chapId);
		chapterPage.setPageNum(pageNum);
		chapterPage.setPageUrl(pageUrl); */
		ChapterPage chapterPage = uploadChapterPage(chapId, pageNum, pageUrl, mpfile, session);
		
		Integer response = chapterPageDAO.addChapterPage(chapterPage);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
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
	// helper method for addChapterPage upload
	private ChapterPage uploadChapterPage(Integer chapId, Integer pageNum, String pageUrl, MultipartFile mpfile, HttpSession session) throws UploadPathException {
		ChapterPage chapterPage = null;
		Date chapPageTS = new Date();
		
		if (mpfile != null && mpfile.getSize() > 0) {
			Boolean uploadSuccess = false;
			String fileName = null;
			
			// Create upload directory 
			String uploadDirPath = session.getServletContext().getRealPath(UPLOAD_DIR);
			if (uploadDirPath == null) {
				uploadDirPath = "/resources/images/chapter_pages";
				//throw new ChapterPageNotFoundException("upload directory is null");
			}
			File uploadDir = new File(uploadDirPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			fileName = mpfile.getOriginalFilename();
				
			String uploadFilePath = session.getServletContext().getRealPath(UPLOAD_DIR + "/" + fileName);
			if (uploadFilePath == null) {
				uploadFilePath = "testFile.pdf";
				//throw new ChapterPageNotFoundException("upload file path is null");
			}
			File uploadFile = new File(uploadFilePath);
			
			try {
				byte[] bytes = mpfile.getBytes();
				BufferedOutputStream stream = 
					new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath)));
                stream.write(bytes);
                stream.close();
				
				uploadSuccess = true;
			} catch(IOException e) {
			}
		
			if (uploadSuccess) {
				chapterPage = new ChapterPage();
				chapterPage.setChapId(chapId);
				chapterPage.setPageNum(pageNum);
				chapterPage.setPageUrl("" + UPLOAD_DIR + "/" + fileName);
				chapterPage.setChapPageTS(chapPageTS);
			}
		}
		
		if (chapterPage == null) {
			return null;
		}
		return chapterPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapterpage", method = RequestMethod.GET)
	public String deleteChapterPage(@RequestParam(value="pageId") Integer pageId){
		
		Boolean response = chapterPageDAO.deleteChapterPage(pageId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
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
	@RequestMapping(value = "/listchapterpagesbychapid", method = RequestMethod.GET)
	public String listChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId){
		
		List<ChapterPage> chapterPagesList = new ArrayList<ChapterPage>();
		try {
			chapterPagesList = chapterPageDAO.findAllChapterPagesByChapterId(chapId);
		} catch (ChapterPageNotFoundException e1) {
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(chapterPagesList);
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
	@RequestMapping(value = "/updatechapterpageorder", method = RequestMethod.GET)
	public String updateChapterPageOrder(@RequestParam(value="newChapterPageOrderList")List<Integer> newChapterPageOrderList){
				
		Boolean response = chapterPageDAO.updateChapterPageOrder(newChapterPageOrderList);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
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
	@RequestMapping(value = "updatechapterpageurl", method = RequestMethod.GET)
	public String updateChapterPageUrl(@RequestParam(value="pageId")Integer pageId,
														@RequestParam(value="pageUrl")String pageUrl){
		
		Boolean response = chapterPageDAO.updatePageUrl(pageId, pageUrl);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
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
	@RequestMapping(value = "deletechapterpagesbychapid", method = RequestMethod.GET)
	public String deleteAllChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId){
		
		Boolean response = chapterPageDAO.deleteAllPagesByChapterId(chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
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
	
}
