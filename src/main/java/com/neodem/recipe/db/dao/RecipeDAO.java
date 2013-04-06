package com.neodem.recipe.db.dao;

import java.util.Collection;

import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.db.common.BaseDAO;

public interface RecipeDAO extends BaseDAO {

	/** return a collection of Recipe objects that are like the name
	 * 
	 * @param searchName
	 * @return
	 */
	public Collection findRecipeLikeName(String searchName);

	public void removeRecipeByID(Long long1);

	public Recipe getRecipeByID(Long long1);

	
}
