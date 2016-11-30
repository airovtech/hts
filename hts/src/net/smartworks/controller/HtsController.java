/*	
 * file 		 : SwsController.java
 * created by    : kmyu
 * creation-date : 2016. 11. 14.
 */

package net.smartworks.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.smartworks.factory.ManagerFactory;
import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.util.PropertiesUtil;

@Controller
public class HtsController {


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
	@RequestMapping(value = "/getJsonString", method = RequestMethod.GET)
    public @ResponseBody String getJsonString(HttpSession session) {
         
		StringBuffer strBuff = new StringBuffer();
		
		strBuff.append(" { ");
		strBuff.append("    \"userdata\":{\"CategoryName\":\"\",\"ProductName\":\"\",\"Country\":\"Total\",\"Price\":\"19521.68\",\"Quantity\":\"\"}, ");
		strBuff.append("    \"rows\":[ ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Steeleye Stout\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1008.0000\", ");
		strBuff.append("          \"Quantity\":\"65\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Laughing Lumberjack Lager\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"140.0000\", ");
		strBuff.append("          \"Quantity\":\"10\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Lakkalik\u00f6\u00f6ri\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2160.0000\", ");
		strBuff.append("          \"Quantity\":\"120\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Guaran\u00e1 Fant\u00e1stica\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"436.5000\", ");
		strBuff.append("          \"Quantity\":\"97\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Ipoh Coffee\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1656.0000\", ");
		strBuff.append("          \"Quantity\":\"36\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Chang\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"342.0000\", ");
		strBuff.append("          \"Quantity\":\"20\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Chartreuse verte\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"648.0000\", ");
		strBuff.append("          \"Quantity\":\"42\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Ipoh Coffee\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1656.0000\", ");
		strBuff.append("          \"Quantity\":\"39\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Chai\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1314.0000\", ");
		strBuff.append("          \"Quantity\":\"73\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Chang\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"5168.0000\", ");
		strBuff.append("          \"Quantity\":\"294\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Steeleye Stout\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2772.0000\", ");
		strBuff.append("          \"Quantity\":\"174\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"C\u00f4te de Blaye\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"42160.0000\", ");
		strBuff.append("          \"Quantity\":\"170\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Outback Lager\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"567.0000\", ");
		strBuff.append("          \"Quantity\":\"42\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Sasquatch Ale\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1610.0000\", ");
		strBuff.append("          \"Quantity\":\"122\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Chai\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3103.2000\", ");
		strBuff.append("          \"Quantity\":\"180\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Chartreuse verte\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"288.0000\", ");
		strBuff.append("          \"Quantity\":\"20\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Guaran\u00e1 Fant\u00e1stica\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"288.0000\", ");
		strBuff.append("          \"Quantity\":\"80\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Sasquatch Ale\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"156.8000\", ");
		strBuff.append("          \"Quantity\":\"14\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Lakkalik\u00f6\u00f6ri\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"266.4000\", ");
		strBuff.append("          \"Quantity\":\"16\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Rh\u00f6nbr\u00e4u Klosterbier\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2292.4500\", ");
		strBuff.append("          \"Quantity\":\"297\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Beverages\", ");
		strBuff.append("          \"ProductName\":\"Outback Lager\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"900.0000\", ");
		strBuff.append("          \"Quantity\":\"66\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Sirop d'\u00e9rable\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3505.5000\", ");
		strBuff.append("          \"Quantity\":\"141\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Original Frankfurter gr\u00fcne So\u00dfe\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"988.0000\", ");
		strBuff.append("          \"Quantity\":\"82\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Louisiana Fiery Hot Pepper Sauce\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"904.3500\", ");
		strBuff.append("          \"Quantity\":\"47\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Chef Anton's Gumbo Mix\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1281.0000\", ");
		strBuff.append("          \"Quantity\":\"60\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Louisiana Hot Spiced Okra\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"408.0000\", ");
		strBuff.append("          \"Quantity\":\"24\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Chef Anton's Cajun Seasoning\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"550.0000\", ");
		strBuff.append("          \"Quantity\":\"25\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Chef Anton's Gumbo Mix\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1783.9000\", ");
		strBuff.append("          \"Quantity\":\"86\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Louisiana Fiery Hot Pepper Sauce\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"442.0500\", ");
		strBuff.append("          \"Quantity\":\"21\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Chef Anton's Cajun Seasoning\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1188.0000\", ");
		strBuff.append("          \"Quantity\":\"61\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Aniseed Syrup\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"40.0000\", ");
		strBuff.append("          \"Quantity\":\"4\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Grandma's Boysenberry Spread\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"25.0000\", ");
		strBuff.append("          \"Quantity\":\"1\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Northwoods Cranberry Sauce\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2320.0000\", ");
		strBuff.append("          \"Quantity\":\"72\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Louisiana Hot Spiced Okra\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"17.0000\", ");
		strBuff.append("          \"Quantity\":\"1\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Vegie-spread\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"4300.6000\", ");
		strBuff.append("          \"Quantity\":\"114\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Gula Malacca\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2553.5000\", ");
		strBuff.append("          \"Quantity\":\"152\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Aniseed Syrup\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"240.0000\", ");
		strBuff.append("          \"Quantity\":\"30\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Northwoods Cranberry Sauce\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"800.0000\", ");
		strBuff.append("          \"Quantity\":\"20\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Sirop d'\u00e9rable\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"855.0000\", ");
		strBuff.append("          \"Quantity\":\"30\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Condiments\", ");
		strBuff.append("          \"ProductName\":\"Genen Shouyu\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"930.0000\", ");
		strBuff.append("          \"Quantity\":\"60\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Maxilaku\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"680.0000\", ");
		strBuff.append("          \"Quantity\":\"40\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Scottish Longbreads\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3060.0000\", ");
		strBuff.append("          \"Quantity\":\"247\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Tarte au sucre\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"591.6000\", ");
		strBuff.append("          \"Quantity\":\"12\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Schoggi Schokolade\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3950.0000\", ");
		strBuff.append("          \"Quantity\":\"100\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Pavlova\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"4739.5000\", ");
		strBuff.append("          \"Quantity\":\"295\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Gumb\u00e4r Gummib\u00e4rchen\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"374.7600\", ");
		strBuff.append("          \"Quantity\":\"12\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"NuNuCa Nu\u00df-Nougat-Creme\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"568.4000\", ");
		strBuff.append("          \"Quantity\":\"42\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Gumb\u00e4r Gummib\u00e4rchen\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3397.3500\", ");
		strBuff.append("          \"Quantity\":\"125\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Zaanse koeken\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"627.0000\", ");
		strBuff.append("          \"Quantity\":\"66\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Maxilaku\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1260.0000\", ");
		strBuff.append("          \"Quantity\":\"63\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Teatime Chocolate Biscuits\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1278.2000\", ");
		strBuff.append("          \"Quantity\":\"141\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Sir Rodney's Scones\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1390.0000\", ");
		strBuff.append("          \"Quantity\":\"139\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Tarte au sucre\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"16953.8000\", ");
		strBuff.append("          \"Quantity\":\"371\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Pavlova\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"778.4000\", ");
		strBuff.append("          \"Quantity\":\"56\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Sir Rodney's Marmalade\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1215.0000\", ");
		strBuff.append("          \"Quantity\":\"15\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Sir Rodney's Marmalade\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2170.8000\", ");
		strBuff.append("          \"Quantity\":\"28\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Teatime Chocolate Biscuits\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"46.0000\", ");
		strBuff.append("          \"Quantity\":\"5\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Scottish Longbreads\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"250.0000\", ");
		strBuff.append("          \"Quantity\":\"20\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Confections\", ");
		strBuff.append("          \"ProductName\":\"Sir Rodney's Scones\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"262.0000\", ");
		strBuff.append("          \"Quantity\":\"29\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Mozzarella di Giovanni\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1356.8000\", ");
		strBuff.append("          \"Quantity\":\"41\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Geitost\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"136.5000\", ");
		strBuff.append("          \"Quantity\":\"57\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Raclette Courdavault\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"770.0000\", ");
		strBuff.append("          \"Quantity\":\"14\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Queso Manchego La Pastora\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"4636.0000\", ");
		strBuff.append("          \"Quantity\":\"122\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Gudbrandsdalsost\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"972.0000\", ");
		strBuff.append("          \"Quantity\":\"33\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Fl\u00f8temysost\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1689.9000\", ");
		strBuff.append("          \"Quantity\":\"82\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Gorgonzola Telino\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2832.5000\", ");
		strBuff.append("          \"Quantity\":\"241\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Queso Cabrales\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1365.0000\", ");
		strBuff.append("          \"Quantity\":\"65\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Mascarpone Fabioli\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1056.0000\", ");
		strBuff.append("          \"Quantity\":\"41\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Camembert Pierrot\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"4590.0000\", ");
		strBuff.append("          \"Quantity\":\"151\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Queso Cabrales\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1113.0000\", ");
		strBuff.append("          \"Quantity\":\"53\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Geitost\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"500.5000\", ");
		strBuff.append("          \"Quantity\":\"221\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Mozzarella di Giovanni\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3763.6000\", ");
		strBuff.append("          \"Quantity\":\"117\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Fl\u00f8temysost\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"4364.5000\", ");
		strBuff.append("          \"Quantity\":\"215\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Raclette Courdavault\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"14080.0000\", ");
		strBuff.append("          \"Quantity\":\"276\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Gudbrandsdalsost\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3600.0000\", ");
		strBuff.append("          \"Quantity\":\"100\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Gorgonzola Telino\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"700.0000\", ");
		strBuff.append("          \"Quantity\":\"70\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Camembert Pierrot\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"5603.2000\", ");
		strBuff.append("          \"Quantity\":\"173\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Dairy Products\", ");
		strBuff.append("          \"ProductName\":\"Mascarpone Fabioli\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"768.0000\", ");
		strBuff.append("          \"Quantity\":\"24\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Grains Cereals\", ");
		strBuff.append("          \"ProductName\":\"Ravioli Angelo\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"117.0000\", ");
		strBuff.append("          \"Quantity\":\"6\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Gustaf's Kn\u00e4ckebr\u00f6d\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"642.6000\", ");
		strBuff.append("          \"Quantity\":\"33\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Filo Mix\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"154.0000\", ");
		strBuff.append("          \"Quantity\":\"26\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Tunnbr\u00f6d\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"736.2000\", ");
		strBuff.append("          \"Quantity\":\"86\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Ravioli Angelo\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1072.5000\", ");
		strBuff.append("          \"Quantity\":\"65\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Filo Mix\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"252.0000\", ");
		strBuff.append("          \"Quantity\":\"36\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Singaporean Hokkien Fried Mee\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1246.0000\", ");
		strBuff.append("          \"Quantity\":\"89\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Gnocchi di nonna Alice\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"12980.8000\", ");
		strBuff.append("          \"Quantity\":\"366\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Tunnbr\u00f6d\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"414.0000\", ");
		strBuff.append("          \"Quantity\":\"46\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Gustaf's Kn\u00e4ckebr\u00f6d\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1008.0000\", ");
		strBuff.append("          \"Quantity\":\"48\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Wimmers gute Semmelkn\u00f6del\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3458.0000\", ");
		strBuff.append("          \"Quantity\":\"110\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Wimmers gute Semmelkn\u00f6del\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"239.4000\", ");
		strBuff.append("          \"Quantity\":\"9\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"GrainsCereals\", ");
		strBuff.append("          \"ProductName\":\"Gnocchi di nonna Alice\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1178.0000\", ");
		strBuff.append("          \"Quantity\":\"35\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Perth Pasties\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"5916.0000\", ");
		strBuff.append("          \"Quantity\":\"186\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Mishi Kobe Niku\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"291.0000\", ");
		strBuff.append("          \"Quantity\":\"3\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Th\u00fcringer Rostbratwurst\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"2079.0000\", ");
		strBuff.append("          \"Quantity\":\"21\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"P\u00e2t\u00e9 chinois\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"4008.0000\", ");
		strBuff.append("          \"Quantity\":\"191\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"P\u00e2t\u00e9 chinois\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"840.0000\", ");
		strBuff.append("          \"Quantity\":\"35\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Th\u00fcringer Rostbratwurst\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"19705.1600\", ");
		strBuff.append("          \"Quantity\":\"173\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Tourti\u00e8re\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1673.3000\", ");
		strBuff.append("          \"Quantity\":\"240\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Alice Mutton\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"13509.6000\", ");
		strBuff.append("          \"Quantity\":\"361\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Alice Mutton\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"975.0000\", ");
		strBuff.append("          \"Quantity\":\"25\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Tourti\u00e8re\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"52.1500\", ");
		strBuff.append("          \"Quantity\":\"7\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Perth Pasties\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1113.6000\", ");
		strBuff.append("          \"Quantity\":\"42\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"MeatPoultry\", ");
		strBuff.append("          \"ProductName\":\"Mishi Kobe Niku\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"582.0000\", ");
		strBuff.append("          \"Quantity\":\"6\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"R\u00f6ssle Sauerkraut\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1549.2000\", ");
		strBuff.append("          \"Quantity\":\"37\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"Manjimup Dried Apples\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3286.0000\", ");
		strBuff.append("          \"Quantity\":\"62\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"Uncle Bob's Organic Dried Pears\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3780.0000\", ");
		strBuff.append("          \"Quantity\":\"131\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"Tofu\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1850.7000\", ");
		strBuff.append("          \"Quantity\":\"91\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"R\u00f6ssle Sauerkraut\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1822.4000\", ");
		strBuff.append("          \"Quantity\":\"44\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"Uncle Bob's Organic Dried Pears\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"3840.0000\", ");
		strBuff.append("          \"Quantity\":\"134\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Produce\", ");
		strBuff.append("          \"ProductName\":\"Manjimup Dried Apples\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1420.4000\", ");
		strBuff.append("          \"Quantity\":\"31\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Konbu\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"202.8000\", ");
		strBuff.append("          \"Quantity\":\"34\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Spegesild\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"180.0000\", ");
		strBuff.append("          \"Quantity\":\"15\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Jack's New England Clam Chowder\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2139.8500\", ");
		strBuff.append("          \"Quantity\":\"227\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Escargots de Bourgogne\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1073.2500\", ");
		strBuff.append("          \"Quantity\":\"87\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Nord-Ost Matjeshering\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"388.3500\", ");
		strBuff.append("          \"Quantity\":\"15\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Ikura\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"2945.0000\", ");
		strBuff.append("          \"Quantity\":\"95\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Spegesild\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1368.0000\", ");
		strBuff.append("          \"Quantity\":\"120\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"R\u00f8gede sild\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1767.0000\", ");
		strBuff.append("          \"Quantity\":\"186\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Gravad lax\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1560.0000\", ");
		strBuff.append("          \"Quantity\":\"60\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Inlagd Sill\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"3515.0000\", ");
		strBuff.append("          \"Quantity\":\"185\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"R\u00f8gede sild\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"114.0000\", ");
		strBuff.append("          \"Quantity\":\"15\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Konbu\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1560.0000\", ");
		strBuff.append("          \"Quantity\":\"262\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"R\u00f6d Kaviar\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1230.0000\", ");
		strBuff.append("          \"Quantity\":\"82\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Gravad lax\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"468.0000\", ");
		strBuff.append("          \"Quantity\":\"18\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Jack's New England Clam Chowder\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"211.9000\", ");
		strBuff.append("          \"Quantity\":\"26\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Inlagd Sill\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1140.0000\", ");
		strBuff.append("          \"Quantity\":\"60\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Ikura\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"1116.0000\", ");
		strBuff.append("          \"Quantity\":\"36\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Boston Crab Meat\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"1876.6000\", ");
		strBuff.append("          \"Quantity\":\"104\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Boston Crab Meat\", ");
		strBuff.append("          \"Country\":\"UK\", ");
		strBuff.append("          \"Price\":\"147.0000\", ");
		strBuff.append("          \"Quantity\":\"10\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Carnarvon Tigers\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"5187.5000\", ");
		strBuff.append("          \"Quantity\":\"88\" ");
		strBuff.append("       }, ");
		strBuff.append("       { ");
		strBuff.append("          \"CategoryName\":\"Seafood\", ");
		strBuff.append("          \"ProductName\":\"Nord-Ost Matjeshering\", ");
		strBuff.append("          \"Country\":\"USA\", ");
		strBuff.append("          \"Price\":\"926.3700\", ");
		strBuff.append("          \"Quantity\":\"44\" ");
		strBuff.append("       } ");
		strBuff.append("    ] ");
		
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

