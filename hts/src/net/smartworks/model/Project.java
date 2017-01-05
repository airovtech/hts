/*	
 * file 		 : Project.java
 * created by    : kmyu
 * creation-date : 2016. 12. 12.
 */

package net.smartworks.model;

public class Project {

	private String projectCode = null;
	private String pm = null;
	private String description = null;
	private String sales = null;
	private String customer = null;
	private String productType = null;
	private String devStart = null;
	private String sop = null;
	private String report = null;
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getDevStart() {
		return devStart;
	}
	public void setDevStart(String devStart) {
		this.devStart = devStart;
	}
	public String getSop() {
		return sop;
	}
	public void setSop(String sop) {
		this.sop = sop;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
}

