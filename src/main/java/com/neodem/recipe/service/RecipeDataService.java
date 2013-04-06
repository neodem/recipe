package com.neodem.recipe.service;

import java.util.Collection;

import com.neodem.recipe.db.beans.Ingredient;
import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.db.beans.RecipeIngredient;
import com.neodem.recipe.db.beans.Unit;

public interface RecipeDataService {

	/**
	 * find recipes that are like the name given
	 * 
	 * @param searchName
	 * @param exact
	 *            if true the search will send the exact name as the param. If
	 *            false the search will be surrounded with '%'
	 * @return
	 */
	public Collection findRecipesLikeName(String searchName, boolean exact);

	/**
	 * find recipe by the id
	 * 
	 * @param id
	 * @return
	 */
	public Recipe findRecipeByID(Long id);

	/**
	 * find ingredients that are like the name given
	 * 
	 * @param searchName
	 * @param exact
	 *            if true the search will send the exact name as the param. If
	 *            false the search will be surrounded with '%'
	 * @return
	 */
	public Collection findIngredientsLikeName(String searchName, boolean exact);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Ingredient findIngredientByID(Long id);

	/**
	 * 
	 * @param parameter
	 */
	public void deleteIngedient(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Ingredient getIngredient(String id);

	/**
	 * 
	 * @param ingredient
	 */
	public void saveIngredient(Ingredient ingredient);

	/**
	 * 
	 * @param id
	 */
	public void deleteRecipe(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Recipe getRecipe(String id);

	/**
	 * 
	 * @param recipe
	 */
	public void saveRecipe(Recipe recipe);

	/**
	 * 
	 * @param recipe
	 * @param ingredientID
	 */
	public void deleteIngedientFromRecipe(Recipe recipe, String ingredientID);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getUnitNameFromID(String id);

	/**
	 * 
	 * @param id
	 */
	public void deleteUnit(String id);

	/**
	 * 
	 * @param unit
	 */
	public void saveUnit(Unit unit);

	/**
	 * 
	 * @return
	 */
	public Collection getAllUnits();

	/**
	 * 
	 * @return
	 */
	public Collection getAllIngredients();

	/**
	 * 
	 * @param unitID
	 * @return
	 */
	public Unit findUnitByID(Long unitID);

	/**
	 * 
	 * @param recipe
	 * @param ri
	 */
	public void addRecipeIngredientToRecipe(Recipe recipe, RecipeIngredient ri);

}
