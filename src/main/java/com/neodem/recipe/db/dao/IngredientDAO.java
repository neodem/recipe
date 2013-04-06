package com.neodem.recipe.db.dao;

import java.util.Collection;

import com.neodem.recipe.db.beans.Ingredient;
import com.neodem.recipe.db.common.BaseDAO;

public interface IngredientDAO extends BaseDAO {

	public Collection findRecipeLikeName(String name);

	public void removeIngredientByID(Long id);

	public Ingredient getIngredientByID(Long id);

	 
}
