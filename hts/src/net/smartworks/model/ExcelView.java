/*	
 * file 		 : TestExcelView.java
 * created by    : kmyu
 * creation-date : 2016. 12. 1.
 */

package net.smartworks.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import net.sf.json.JSONArray;

//@Component("TestExcelView")
public class ExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	
    	String resultDate = (String)model.get("resultDate");
    	String resultColumnData = (String)model.get("resultColumnData");

    	String resultSummaryData = (String)model.get("resultSummaryData");
    	String resultSummaryColumnData = (String)model.get("resultSummaryColumnData");
    	
    	ObjectMapper om = new ObjectMapper();

    	List<Map<String, Object>> resultDataList = om.readValue(resultDate, new TypeReference<List<Map<String, Object>>>(){});	
    	List<String> resultColumnDataList = om.readValue(resultColumnData, new TypeReference<List<String>>(){});	

    	List<Map<String, Object>> resultSummaryDataList = null;
    	List<String> resultSummaryColumnDataList = null;
    	
    	if (resultSummaryColumnData != null && !resultSummaryColumnData.equalsIgnoreCase("undefined")) {
    		resultSummaryDataList = om.readValue(resultSummaryData, new TypeReference<List<Map<String, Object>>>(){});	
    		resultSummaryColumnDataList = om.readValue(resultSummaryColumnData, new TypeReference<List<String>>(){});	
    	}
    	
    	
        HSSFSheet sheet = workbook.createSheet();
        
        HSSFRow header = sheet.createRow(0);
    	for (int i = 0; i < resultColumnDataList.size(); i++) {
    		String name = resultColumnDataList.get(i);
            header.createCell((short) i).setCellValue(new HSSFRichTextString(name));
		}
    	for (int i = 0; i < resultDataList.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
    		Map resultMap = (Map)resultDataList.get(i);
            for (int j = 0; j < resultColumnDataList.size(); j++) {
        		String name = resultColumnDataList.get(j);
        		
        		if (name != null && name.equalsIgnoreCase("Employee ID")) {
        			name = "userNo";
        		} else if (name != null && name.equalsIgnoreCase("K-Name")) {
        			name = "name";
        		} else if (name != null && name.equalsIgnoreCase("E-Name")) {
        			name = "engName";
        		} else if (name != null && name.equalsIgnoreCase("Dept.")) {
        			name = "dept";
        		} else if (name != null && name.equalsIgnoreCase("Type")) {
        			name = "type";
        		} else if (name != null && name.equalsIgnoreCase("Title")) {
        			name = "position";
        		} else if (name != null && name.equalsIgnoreCase("project")) {
        			name = "project.description";
        		} else if (name != null && name.equalsIgnoreCase("project code")) {
         			name = "projectCode";
         		} else if (name != null && name.equalsIgnoreCase("PM")) {
         			name = "project.pm";
         		} else if (name != null && name.equalsIgnoreCase("Sales")) {
         			name = "project.sales";
         		} else if (name != null && name.equalsIgnoreCase("Customer")) {
         			name = "project.customer";
         		} else if (name != null && name.equalsIgnoreCase("ProductType")) {
         			name = "project.productType";
         		} else if (name != null && name.equalsIgnoreCase("SOP")) {
         			name = "project.sop";
         		} else if (name != null && name.equalsIgnoreCase("SOD")) {
         			name = "project.devStart";
         		} else if (name != null && name.equalsIgnoreCase("Total")) {
         			name = "sum";
         		}
        		
        		
        		String value = (String)resultMap.get(name);

                row.createCell((short) j).setCellValue(new HSSFRichTextString(value));
			}
		}
    	
    	
    	if (resultSummaryColumnData != null && !resultSummaryColumnData.equalsIgnoreCase("undefined")) {
    		int startRowNo = resultDataList.size() + 2;
        	
            HSSFRow summaryHeader = sheet.createRow(startRowNo);
        	for (int i = 0; i < resultSummaryColumnDataList.size(); i++) {
        		String name = resultSummaryColumnDataList.get(i);
        		summaryHeader.createCell((short) i).setCellValue(new HSSFRichTextString(name));
    		}
        	for (int i = 0; i < resultSummaryDataList.size(); i++) {
                HSSFRow row = sheet.createRow((startRowNo+1) + i);
        		Map resultMap = (Map)resultSummaryDataList.get(i);
                for (int j = 0; j < resultSummaryColumnDataList.size(); j++) {
            		String name = resultSummaryColumnDataList.get(j);
            		
            		if (name != null && name.equalsIgnoreCase("Total")) {
            			name = "totalSum";
            		} else if (name != null && name.equalsIgnoreCase("H/C")) {
            			name = "hc";
            		}
            		
            		String value = (String)resultMap.get(name);

                    row.createCell((short) j).setCellValue(new HSSFRichTextString(value));
    			}
    		}
    	}
    	
    }
}
