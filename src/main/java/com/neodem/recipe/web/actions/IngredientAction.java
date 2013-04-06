package com.neodem.recipe.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.neodem.recipe.common.RecipeConstants;
import com.neodem.recipe.common.RecipeSpringBeanKeys;
import com.neodem.recipe.db.beans.Ingredient;
import com.neodem.recipe.service.RecipeDataService;
import com.neodem.recipe.web.RecipeWebConstants;
import com.neodem.recipe.web.forms.IngredientForm;

public class IngredientAction extends DispatchAction implements RecipeConstants, RecipeWebConstants,
		RecipeSpringBeanKeys {

	protected static Log _log = LogFactory.getLog(IngredientAction.class.getName());

	private RecipeDataService recipeDataService;

	/**
	 * @param recipeDataService
	 *            The recipeDataService to set.
	 */
	public void setRecipeDataService(RecipeDataService recipeDataService) {
		this.recipeDataService = recipeDataService;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("delete()");

		String id = request.getParameter("ingredient.id");
		_log.debug("deleting id='" + id + "'");
	
		if (id != null) {
			Ingredient ingredient = recipeDataService.getIngredient(id);
			String name = ingredient.getName();
			_log.debug("deleting :");
			_log.debug(ingredient);

			recipeDataService.deleteIngedient(id);

			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ingredient.deleted", name));
			saveMessages(request, messages);
		}

		_log.debug("complete()");
		return mapping.findForward(FORWARD_HOME);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("edit()");
		
		IngredientForm ingredientForm = (IngredientForm) form;
		Ingredient ingredient = ingredientForm.getIngredient();
		
		_log.debug("found in form:");
		_log.debug(ingredient);
		
		
		Long id = ingredient.getId();
		
		if(id == null) {
			_log.debug("creating new");
		}
		else {
			_log.debug("loading anew");
			ingredient = recipeDataService.findIngredientByID(id);
		}
		
		_log.debug("setting in form:");
		_log.debug(ingredient);
		
		ingredientForm.setIngredient(ingredient);

		_log.debug("complete()");
		return mapping.findForward(FORWARD_EDIT);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("view()");

		IngredientForm ingredientForm = (IngredientForm) form;
		String id = request.getParameter("id");

		_log.debug("id == '" + id + "'");

		if (id != null) {
			Ingredient ingredient = recipeDataService.getIngredient(id);

			if (ingredient == null) {
				_log.debug("ingredient not found");
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ingredient.missing", id));
				saveErrors(request, errors);
			} else {
				_log.debug("ingredient found");
				_log.debug(ingredient);
			}

			ingredientForm.setIngredient(ingredient);
		}
		else {
			_log.warn("view called with a null id");
			return mapping.findForward(FORWARD_ERROR);
		}

		_log.debug("complete()");
		return mapping.findForward(FORWARD_VIEW);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("save()");
		
		IngredientForm ingredientForm = (IngredientForm) form;
		Ingredient ingredient = ingredientForm.getIngredient();

		_log.debug("ingredient will be saved :");
		_log.debug(ingredient);

		recipeDataService.saveIngredient(ingredient);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ingredient.saved", ingredient.getName()));
		saveMessages(request, messages);

		_log.debug("complete()");
		return mapping.findForward(FORWARD_HOME);
	}
}
