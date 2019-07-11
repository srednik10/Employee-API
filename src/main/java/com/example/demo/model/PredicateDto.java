package com.example.demo.model;


public class PredicateDto {

	private String columnName;
	private String predicateName;
	private Object[] values;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getPredicateName() {
		return predicateName;
	}
	public void setPredicateName(String predicateName) {
		this.predicateName = predicateName;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	
	
	
	
	
}
