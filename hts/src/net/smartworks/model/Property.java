package net.smartworks.model;

public class Property {
	
	public static final String A_NAME = "name";
	public static final String A_VALUE = "value";
	
	private String name;
	private String value;
	
	public Property() {
		super();
	}
	public Property(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}


