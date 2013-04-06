package com.neodem.recipe.db.beans;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.neodem.recipe.db.common.BaseVO;

public class RecipeIngredient extends BaseVO {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Ingredient ingredient;
	private Recipe recipe;
	private Float amount;
	private Unit unit;
	private String details;
	
	/** hash is purely dependant on the ID
	 * 
	 */
	public int hashCode() {
		return new HashCodeBuilder(35, 7)
		.append(id)
		.toHashCode();
	}
	
	/** two items are equal if and only if they have equal ID's
	 * 
	 */
    public boolean equals(Object o) {
    	if(o==null) return false;
    	if(!(o instanceof RecipeIngredient)) return false;
    	if(id == null) return false;
    	
    	if(id.equals(((RecipeIngredient)o).getId())) return true;
    	return false;
    }
	
	public String toString() {
		StringBuffer b = new StringBuffer(40);
		b.append(amount);
		b.append(" of ");
		b.append(ingredient.getName());
		
		return b.toString();
	}
	
	public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
		setIngredient(ingredient);
		setRecipe(recipe);		
	}

	
	public RecipeIngredient() {
	}



	public RecipeIngredient(Ingredient ingredient, Float amount) {
		this.ingredient = ingredient;
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
	 * @return Returns the unit.
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit The unit to set.
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
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
	

}
