package com.neodem.recipe.db.beans;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.neodem.recipe.db.common.BaseVO;

public class Recipe extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Integer serves;
	private String steps;
	private String difficulty;
	private Integer prepTime;
	private Integer inactivePrep;
	private Integer cookTime;

	private Set recipeIngredients = new HashSet();

	public Recipe() {
	}
	
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

	public int hashCode() {
		return new HashCodeBuilder(61, 3).appendSuper(super.hashCode()).append(name).append(serves).append(steps)
				.append(difficulty).append(prepTime).append(inactivePrep).append(cookTime).toHashCode();
	}

	public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
		if (recipeIngredient == null) {
			throw new IllegalArgumentException("recipeIngredient is null");
		}

		recipeIngredient.setRecipe(this);
		recipeIngredients.add(recipeIngredient);
	}
	
	public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
		recipeIngredients.remove(recipeIngredient);
	}
	
	

	// public void addIngredient(Ingredient ingredient, String amount) {
	// if(ingredient == null) {
	// throw new IllegalArgumentException("ingredient is null");
	// }
	//		
	// if(amount == null) {
	// throw new IllegalArgumentException("amount is null");
	// }
	//		
	// RecipeIngredient ri = new RecipeIngredient(ingredient, amount);
	// addRecipeIngredient(ri);
	// }

	/**
	 * @return Returns the cookTime.
	 */
	public Integer getCookTime() {
		return cookTime;
	}

	/**
	 * @param cookTime
	 *            The cookTime to set.
	 */
	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	/**
	 * @return Returns the difficulty.
	 */
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty
	 *            The difficulty to set.
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return Returns the inactivePrep.
	 */
	public Integer getInactivePrep() {
		return inactivePrep;
	}

	/**
	 * @param inactivePrep
	 *            The inactivePrep to set.
	 */
	public void setInactivePrep(Integer inactivePrep) {
		this.inactivePrep = inactivePrep;
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
	 * @return Returns the prepTime.
	 */
	public Integer getPrepTime() {
		return prepTime;
	}

	/**
	 * @param prepTime
	 *            The prepTime to set.
	 */
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	/**
	 * @return Returns the recipeIngredients.
	 */
	public Set getRecipeIngredients() {
		return recipeIngredients;
	}

	/**
	 * @param recipeIngredients
	 *            The recipeIngredients to set.
	 */
	public void setRecipeIngredients(Set recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}

	/**
	 * @return Returns the serves.
	 */
	public Integer getServes() {
		return serves;
	}

	/**
	 * @param serves
	 *            The serves to set.
	 */
	public void setServes(Integer serves) {
		this.serves = serves;
	}

	/**
	 * @return Returns the steps.
	 */
	public String getSteps() {
		return steps;
	}

	/**
	 * @param steps
	 *            The steps to set.
	 */
	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Long getRecipeID() {
		return getId();
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
