package com.neodem.recipe.service.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neodem.recipe.common.RSObject;
import com.neodem.recipe.db.beans.Ingredient;
import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.db.beans.RecipeIngredient;
import com.neodem.recipe.db.beans.Unit;
import com.neodem.recipe.db.common.BaseDAO;
import com.neodem.recipe.db.dao.IngredientDAO;
import com.neodem.recipe.db.dao.RecipeDAO;
import com.neodem.recipe.service.RecipeDataService;

public class DefaultRecipeDataService extends RSObject implements RecipeDataService {

	protected static Log _log = LogFactory.getLog(DefaultRecipeDataService.class.getName());

	// need to be set externally
	private RecipeDAO recipeDAO;

	private IngredientDAO ingredientDAO;

	private BaseDAO baseDAO;

	public DefaultRecipeDataService() {
		super();
	}

	public Collection findRecipesLikeName(String searchName, boolean exact) {
		_log.debug("findRecipesLikeName");

		if (StringUtils.isBlank(searchName)) {
			String msg = "searchName may not be blank";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		String name = searchName;

		if (!exact) {
			name = "%" + searchName + "%";
		}

		Collection result = recipeDAO.findRecipeLikeName(name);

		_log.debug("complete");
		return result;
	}

	public Collection findIngredientsLikeName(String searchName, boolean exact) {
		_log.debug("findIngredientsLikeName");

		if (StringUtils.isBlank(searchName)) {
			String msg = "searchName may not be blank";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		String name = searchName;

		if (!exact) {
			name = "%" + searchName + "%";
		}

		Collection result = ingredientDAO.findRecipeLikeName(name);

		_log.debug("complete");
		return result;
	}

	public Ingredient createIngredient(String name) {
		_log.debug("createIngredient");

		if (StringUtils.isBlank(name)) {
			String msg = "name may not be blank";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		Ingredient ingredient = new Ingredient(name);

		ingredientDAO.insert(ingredient);

		_log.debug("complete");
		return ingredient;
	}

	public Recipe findRecipeByID(Long id) {
		_log.debug("findRecipeByID");

		if (id == null) {
			String msg = "id may not be null";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		Recipe result = (Recipe) recipeDAO.findByID(Recipe.class, id);

		_log.debug("complete");
		return result;
	}

	public Ingredient findIngredientByID(Long id) {
		_log.debug("findIngredientByID");

		if (id == null) {
			String msg = "id may not be null";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		Ingredient result = (Ingredient) ingredientDAO.findByID(Ingredient.class, id);

		_log.debug("complete");
		return result;
	}

	public void deleteIngedient(Ingredient ingredient) {
		ingredientDAO.delete(ingredient);
	}

	public void updateIngredient(Ingredient ingredient) {
		ingredientDAO.update(ingredient);
	}

	/**
	 * @return Returns the recipeDAO.
	 */
	public RecipeDAO getRecipeDAO() {
		return recipeDAO;
	}

	/**
	 * @return Returns the ingredientDAO.
	 */
	public IngredientDAO getIngredientDAO() {
		return ingredientDAO;
	}

	/**
	 * @param ingredientDAO
	 *            The ingredientDAO to set.
	 */
	public void setIngredientDAO(IngredientDAO ingredientDAO) {
		this.ingredientDAO = ingredientDAO;
	}

	/**
	 * @param recipeDAO
	 *            The recipeDAO to set.
	 */
	public void setRecipeDAO(RecipeDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}

	public void deleteIngedient(String id) {
		ingredientDAO.removeIngredientByID(Long.valueOf(id));
	}

	public Ingredient getIngredient(String id) {
		Ingredient ingredient = ingredientDAO.getIngredientByID(Long.valueOf(id));
		if (ingredient == null) {
			_log.warn("id '" + id + "' not found in Ingredient db");
		}
		return ingredient;
	}

	public void saveIngredient(Ingredient ingredient) {
		ingredientDAO.save(ingredient);
	}

	public void deleteRecipe(String id) {
		recipeDAO.removeRecipeByID(Long.valueOf(id));
	}

	public Recipe getRecipe(String id) {
		Recipe recipe = recipeDAO.getRecipeByID(Long.valueOf(id));
		if (recipe == null) {
			_log.warn("id '" + id + "' not found in Recipe db");
		}
		return recipe;
	}

	public void saveRecipe(Recipe recipe) {
		_log.debug("saveRecipe()");
		recipeDAO.save(recipe);
		_log.debug("saveRecipe() - complete");
	}

	public void deleteIngedientFromRecipe(Recipe recipe, String ingredientID) {
		_log.debug("deleteIngedientFromRecipe()");

		Long iid = Long.valueOf(ingredientID);

		Collection ingredients = recipe.getRecipeIngredients();
		Iterator i = ingredients.iterator();

		while (i.hasNext()) {
			RecipeIngredient ri = (RecipeIngredient) i.next();
			if (ri.getIngredient().getId().equals(iid)) {
				// update the recipe
				recipe.removeRecipeIngredient(ri);
				recipeDAO.save(recipe);
				// exit the loop
				break;
			}
		}

		_log.debug("deleteIngedientFromRecipe() - complete");
	}

	public void addRecipeIngredientToRecipe(Recipe recipe, RecipeIngredient ri) {
		_log.debug("addRecipeIngredientToRecipe()");

		recipe.addRecipeIngredient(ri);
		recipeDAO.save(recipe);

		_log.debug("addRecipeIngredientToRecipe() - complete");

	}

	/**
	 * @return Returns the baseDAO.
	 */
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	/**
	 * @param baseDAO
	 *            The baseDAO to set.
	 */
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public String getUnitNameFromID(String id) {
		Unit unit = (Unit) baseDAO.findByID(Unit.class, Long.valueOf(id));
		return unit.getUnit();
	}

	public void deleteUnit(String id) {
		Unit unit = (Unit) baseDAO.findByID(Unit.class, Long.valueOf(id));
		if (unit != null) {
			baseDAO.delete(unit);
		}
	}

	public void saveUnit(Unit unit) {
		_log.debug("saveUnit()");
		baseDAO.save(unit);
		_log.debug("saveUnit() - complete");
	}

	public Collection getAllUnits() {
		return baseDAO.findAll(Unit.class);
	}

	public Collection getAllIngredients() {
		return baseDAO.findAll(Ingredient.class);
	}

	public Unit findUnitByID(Long unitID) {
		Unit unit = (Unit) baseDAO.findByID(Unit.class, unitID);
		return unit;
	}

}
