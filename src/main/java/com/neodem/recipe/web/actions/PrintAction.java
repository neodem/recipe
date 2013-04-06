package com.neodem.recipe.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.service.RecipeDataService;
import com.neodem.recipe.web.forms.RecipeForm;

public class PrintAction extends BaseRecipeAction {
	protected static Log _log = LogFactory.getLog(PrintAction.class.getName());

	private RecipeDataService recipeDataService;



	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		_log.debug("execute()");

		// the form
		
		String rid = request.getParameter("rid");
		Long id = Long.valueOf(rid);
		
		Recipe recipe = recipeDataService.findRecipeByID(id);
		
		RecipeForm rForm = (RecipeForm)form;
		rForm.setRecipe(recipe);

		_log.debug("complete()");
		return mapping.findForward(FORWARD_SUCCESS);
	}

	/**
	 * @return Returns the recipeDataService.
	 */
	public RecipeDataService getRecipeDataService() {
		return recipeDataService;
	}

	/**
	 * @param recipeDataService
	 *            The recipeDataService to set.
	 */
	public void setRecipeDataService(RecipeDataService recipeDataService) {
		this.recipeDataService = recipeDataService;
	}

}
