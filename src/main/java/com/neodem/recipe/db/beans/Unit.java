package com.neodem.recipe.db.beans;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.neodem.recipe.db.common.BaseVO;

public class Unit extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String unit;

	public int hashCode() {
		return new HashCodeBuilder(23, 13)
		.appendSuper(super.hashCode())
		.append(unit)
		.toHashCode();
	}

	public Unit() {
	}

	public Unit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return Returns the unit.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit The unit to set.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
