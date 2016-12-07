/*	
 * file 		 : SwsController.java
 * created by    : kmyu
 * creation-date : 2016. 11. 14.
 */

package net.smartworks.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.smartworks.factory.ManagerFactory;
import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.util.PropertiesUtil;

@Controller
public class HtsController {

	
	 @RequestMapping("/HcTimeSheet")
	 public String excelDownload(Model model, HttpServletRequest request){
		
		String resultDate = null;
		String resultColumnData = null;
		String resultSummaryData = null;
		String resultSummaryColumnData = null;
		try {
			resultDate = new String(request.getParameter("resultData").getBytes("8859_1"), "utf-8");
			resultColumnData = new String(request.getParameter("resultColumnData").getBytes("8859_1"), "utf-8");
			
			resultSummaryData = new String(request.getParameter("resultSummaryData").getBytes("8859_1"), "utf-8");
			resultSummaryColumnData = new String(request.getParameter("resultSummaryColumnData").getBytes("8859_1"), "utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("resultDate", resultDate);
		model.addAttribute("resultColumnData", resultColumnData);
		model.addAttribute("resultSummaryData", resultSummaryData);
		model.addAttribute("resultSummaryColumnData", resultSummaryColumnData);
		
		return "excelView";
	 }
	
	
	@RequestMapping(value="/hcTimeSheet", method=RequestMethod.GET)
	public ModelAndView testExcel(HttpServletRequest request) {

		
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("excelView");
		
		Map modelMap = new HashMap();
		modelMap.put("jhihi", "hello");
		
		mav.addAllObjects(modelMap);
		
		
	    return new ModelAndView("excelView");
	}
	
	@RequestMapping(value="/getDefaultSelectOptionForJqgrid", produces = "application/json", method=RequestMethod.POST)
	public @ResponseBody Map getDefaultSelectOptionForJqgrid(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userId = "";
		
		Map result = ManagerFactory.getInstance().getManager().getDefaultSelectOptionForJqgrid(userId);
		
		return result;
	}
	
	
	
	@RequestMapping(value="/getHcTimeSheetForJqgrid", produces = "application/json", method=RequestMethod.POST)
	public @ResponseBody Map getHcTimeSheetForJqgrid(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		String userId = "";
		
		String fromDate = (String)requestBody.get("fromDate");
		String toDate = (String)requestBody.get("toDate");
		
		//ST, OT, INPUT 
		String gridType = (String)requestBody.get("gridType");
		
		//부서 
		String dept = (String)requestBody.get("dept");
		
		//프로젝트코드 
		String projectCode = (String)requestBody.get("projectCode");
		
		
		HcTimeSheetCond cond = new HcTimeSheetCond();
		
		SimpleDateFormat sdf = new SimpleDateFormat(PropertiesUtil.getInstance().getDate_Pattern());
		
		Date startDate = null;
		if (fromDate != null) {
			startDate = sdf.parse(fromDate);
		}
		Date endDate = null;
		if (toDate != null) {
			endDate = sdf.parse(toDate);
		}
		
		cond.setWorkDateFrom(startDate);
		cond.setWorkDateTo(endDate);
		
		
		if (gridType != null) {
			cond.setGridType(gridType);
		}
		if (dept != null) {
			cond.setDept(dept);
		}
		if (projectCode != null) {
			cond.setPrjCode(projectCode);
		}
		
		Map result = ManagerFactory.getInstance().getManager().getHcTimeSheetJsonDatasForJqgrid(userId, cond);
		
		return result;
		
	}
	@RequestMapping(value="/getUserInfoOfInputHcTimeSheetForJqgrid", produces = "application/json", method=RequestMethod.POST)
	public @ResponseBody Map getUserInfoOfInputHcTimeSheetForJqgrid(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		String userId = "";
		
		String fromDate = (String)requestBody.get("fromDate");
		String toDate = (String)requestBody.get("toDate");
		
		//ST, OT, INPUT 
		String gridType = (String)requestBody.get("gridType");
		
		//부서 
		String dept = (String)requestBody.get("dept");
		
		//프로젝트코드 
		String projectCode = (String)requestBody.get("projectCod");
		
		String notInputOnly = (String)requestBody.get("notInputOnly");
		
		
		HcTimeSheetCond cond = new HcTimeSheetCond();
		
		SimpleDateFormat sdf = new SimpleDateFormat(PropertiesUtil.getInstance().getDate_Pattern());
		
		Date startDate = null;
		if (fromDate != null) {
			startDate = sdf.parse(fromDate);
		}
		Date endDate = null;
		if (toDate != null) {
			endDate = sdf.parse(toDate);
		}
		
		cond.setWorkDateFrom(startDate);
		cond.setWorkDateTo(endDate);
		
		
		if (gridType != null) {
			cond.setGridType(gridType);
		}
		if (dept != null) {
			cond.setDept(dept);
		}
		if (projectCode != null) {
			cond.setPrjCode(projectCode);
		}
		if (notInputOnly != null) {
			cond.setNotInputOnly(notInputOnly);
		}
		
		Map result = ManagerFactory.getInstance().getManager().getUserInfoOfInputHcTimeSheetForJqgrid(userId, cond);
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////// 테스트 코드 ///////////////////////
	
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public @ResponseBody Map getCurrentUser(HttpSession session) {
         
		Map resultMap = new HashMap();
		resultMap.put("id", "kimworks@smartworks.net");
		resultMap.put("picture", "pic");
		resultMap.put("username", "김웍스");
		return resultMap;
    }

	@RequestMapping(value = "/getJsonMap", method = RequestMethod.GET)
    public @ResponseBody Map getJsonMap(HttpSession session) {
         
		Map resultMap = new HashMap();
		
		List resultList = new ArrayList();
		
		Map rowMap = new HashMap();
		rowMap.put("CategoryName", "Beverages");
		rowMap.put("Quantity", "10");
		
		resultList.add(rowMap);
		
		resultMap.put("rows", resultList);
		
		return resultMap;
    }
	@RequestMapping(value = "/getString", method = RequestMethod.GET)
    public @ResponseBody String getJsonString(HttpSession session) {
         
		StringBuffer strBuff = new StringBuffer();
		
		strBuff.append("<span id='connect_SBPPrj' class='sbpPrjNames' style='cursor: pointer;' sbpId='1710'>바바라 백화점 매장 서비스 - 전과정 상세 TO-BE 룩스,앨범만</span><br/>");
		strBuff.append("<span id='connect_SBPPrj' class='sbpPrjNames' style='cursor: pointer;' sbpId='1725'>바바라 백화점 매장 서비스 - 전과정 상세 TO-BE_동영상용</span><br/>");
		strBuff.append("<span id='connect_SBPPrj' class='sbpPrjNames' style='cursor: pointer;' sbpId='1701'>바바라 백화점 매장 서비스 - 서비스화 프로세스</span><br/>");
		strBuff.append("<span id='connect_SBPPrj' class='sbpPrjNames' style='cursor: pointer;' sbpId='1665'>바바라 백화점 매장 서비스 - 전과정 상세 AS-IS</span><br/>");
		
		
		String encodingStr = URLEncoder.encode(strBuff.toString());
		return strBuff.toString();
		
    }
	
	
	@RequestMapping(value="/getList", method=RequestMethod.POST)
	public @ResponseBody Map getList(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		List<Map<String, String>> filtersList = (List<Map<String, String>>)requestBody.get("filters");

		//String objId = (String)requestBody.get("objId");
		
		Map result = new HashMap();
		result.put("hello","world");
		result.put("hi","world");
		
		return result;
		
	}

}

