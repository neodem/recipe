package com.neodem.recipe.db.beans;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.neodem.recipe.db.common.BaseVO;

public class Ingredient extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String brand;
	private String notes;

	private Set recipeIngredients = new HashSet();

	public int hashCode() {
		return new HashCodeBuilder(23, 13)
		.appendSuper(super.hashCode())
		.append(name)
		.append(brand)
		.append(notes)
		.toHashCode();
	}

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
	public Ingredient() {
	}

	public Ingredient(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the ingredientID.
	 */
	public Long getIngredientID() {
		return getId();
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the recipeIngredients.
	 */
	public Set getRecipeIngredients() {
		return recipeIngredients;
	}

	/**
	 * @param recipeIngredients The recipeIngredients to set.
	 */
	public void setRecipeIngredients(Set recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}

	/**
	 * @return Returns the brand.
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand The brand to set.
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
