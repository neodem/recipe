package com.neodem.recipe.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.db.beans.Recipe;


public class RecipeForm extends AbstractEJValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Recipe recipe;
	
	//private String iid; // to hold ingredient id for edits to the ingredient
	
	private Float amount;
	private Long unitKey ;
	private Long ingredientKey;
	private String details;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		recipe = new Recipe();
	}

	/**
	 * @return Returns the recipe.
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe The recipe to set.
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	/**
	 * @return Returns the amount.
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return Returns the ingredientKey.
	 */
	public Long getIngredientKey() {
		return ingredientKey;
	}

	/**
	 * @param ingredientKey The ingredientKey to set.
	 */
	public void setIngredientKey(Long ingredientKey) {
		this.ingredientKey = ingredientKey;
	}

	/**
	 * @return Returns the unitKey.
	 */
	public Long getUnitKey() {
		return unitKey;
	}

	/**
	 * @param unitKey The unitKey to set.
	 */
	public void setUnitKey(Long unitKey) {
		this.unitKey = unitKey;
	}

//	/**
//	 * @return Returns the iid.
//	 */
//	public String getIid() {
//		return iid;
//	}
//
//	/**
//	 * @param iid The iid to set.
//	 */
//	public void setIid(String iid) {
//		this.iid = iid;
//	}




}
