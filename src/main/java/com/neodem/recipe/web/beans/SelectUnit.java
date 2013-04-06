package com.neodem.recipe.web.beans;

public class SelectUnit {
	private Long value;
	private String label;
	
	public SelectUnit() {
		
	}
	
	public SelectUnit(Long key, String label) {
		setValue(key);
		setLabel(label);
	}
	/**
	 * @return Returns the key.
	 */
	public Long getValue() {
		return value;
	}
	/**
	 * @param key The key to set.
	 */
	public void setValue(Long key) {
		this.value = key;
	}
	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
