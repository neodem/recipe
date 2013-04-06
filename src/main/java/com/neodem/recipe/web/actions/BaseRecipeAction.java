package com.neodem.recipe.web.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.struts.ActionSupport;

import com.neodem.recipe.common.RecipeConstants;
import com.neodem.recipe.common.RecipeSpringBeanKeys;
import com.neodem.recipe.web.RecipeWebConstants;

public abstract class BaseRecipeAction extends ActionSupport implements
		RecipeConstants, RecipeWebConstants, RecipeSpringBeanKeys {

	protected static Log _log = LogFactory.getLog(BaseRecipeAction.class.getName());


}