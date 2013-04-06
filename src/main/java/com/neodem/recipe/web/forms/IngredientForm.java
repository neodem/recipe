package com.neodem.recipe.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.db.beans.Ingredient;

public class IngredientForm extends AbstractEJValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Ingredient ingredient;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		ingredient = new Ingredient();
	}

	
	/**
	 * @return Returns the ingredient.
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	
	/**
	 * @param ingredient The ingredient to set.
	 */
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

}
