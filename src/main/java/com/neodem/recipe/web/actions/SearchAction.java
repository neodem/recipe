package com.neodem.recipe.web.actions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.service.RecipeDataService;
import com.neodem.recipe.web.forms.SearchForm;

public class SearchAction extends BaseRecipeAction {

	protected static Log _log = LogFactory.getLog(SearchAction.class.getName());

	private RecipeDataService recipeDataService;

	public SearchAction() {
		_log.debug("constructor.");
	}

	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		_log.debug("execute()");

		// the form
		SearchForm searchForm = (SearchForm) form;

		// the form values
		String searchName = searchForm.getSearchName();
		String searchType = searchForm.getType();

		Collection results;
		String forward;

		if (searchType.equalsIgnoreCase("INGREDIENT")) {
			_log.debug("getting ingredients from service");
			results = recipeDataService.findIngredientsLikeName(searchName,
					false);
			
			forward = FORWARD_ING_SUCCESS;
		}

		else {
			_log.debug("getting recipes from service");
			results = recipeDataService.findRecipesLikeName(searchName, false);
			
			forward = FORWARD_SUCCESS;
		}

		_log.debug("placing results into session");
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_SEARCHRESULTSET, results);

		_log.debug("complete()");
		return mapping.findForward(forward);
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
