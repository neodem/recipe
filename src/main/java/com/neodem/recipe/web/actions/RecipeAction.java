package com.neodem.recipe.web.actions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.db.beans.RecipeIngredient;
import com.neodem.recipe.db.beans.Unit;
import com.neodem.recipe.service.RecipeDataService;
import com.neodem.recipe.web.RecipeWebConstants;
import com.neodem.recipe.web.beans.SelectUnit;
import com.neodem.recipe.web.forms.RecipeForm;

public class RecipeAction extends DispatchAction implements RecipeConstants, RecipeWebConstants, RecipeSpringBeanKeys {

	protected static Log _log = LogFactory.getLog(RecipeAction.class.getName());

	private RecipeDataService recipeDataService;

	/**
	 * @param recipeDataService
	 *            The recipeDataService to set.
	 */
	public void setRecipeDataService(RecipeDataService recipeDataService) {
		this.recipeDataService = recipeDataService;
	}
	
	private void fillSession(HttpServletRequest request) {
		_log.debug("fillSession()");
		
		HttpSession session = request.getSession();
			
		_log.debug("setting ingredients");
		// fill up the ingredients
		Set ingSet = new HashSet();
		Iterator ingredients = recipeDataService.getAllIngredients().iterator();	
		while (ingredients.hasNext()) {
			Ingredient ingredient = (Ingredient) ingredients.next();
			ingSet.add(new SelectUnit(ingredient.getId(), ingredient.getName()));
		}
		session.setAttribute("ingredients", ingSet);
		
		_log.debug("setting units");
		// fill up the units
		Set unitSet = new HashSet();
		Iterator units = recipeDataService.getAllUnits().iterator();	
		while (units.hasNext()) {
			Unit unit = (Unit) units.next();
			unitSet.add(new SelectUnit(unit.getId(), unit.getUnit()));
		}
		session.setAttribute("units", unitSet);
		
		_log.debug("fillSession() - complete");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("delete()");

		String id = request.getParameter("recipe.id");
		String name = request.getParameter("recipe.name");

		_log.debug("deleting id='" + id + "'");

		recipeDataService.deleteRecipe(id);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("recipe.deleted", name));
		saveMessages(request, messages);

		_log.debug("complete()");
		return mapping.findForward(FORWARD_HOME);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("edit()");
		
		RecipeForm recipeForm = (RecipeForm) form;
		Recipe recipe = recipeForm.getRecipe();
		
		_log.debug("found in form:");
		_log.debug(recipe);
		
		Long id = recipe.getId();
		
		if(id == null) {
			_log.debug("creating new");
		}
		else {
			_log.debug("loading anew");
			recipe = recipeDataService.findRecipeByID(id);
		}
		
		_log.debug("setting in form:");
		_log.debug(recipe);
		
		recipeForm.setRecipe(recipe);
		fillSession(request);
		
		_log.debug("complete()");
		return mapping.findForward(FORWARD_EDIT);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("view()");

		RecipeForm recipeForm = (RecipeForm) form;
		String id = request.getParameter("id");

		_log.debug("id == '" + id + "'");

		if (id != null) {
			Recipe recipe = recipeDataService.getRecipe(id);

			if (recipe == null) {
				_log.debug("recipe not found");
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("recipe.missing", id));
				saveErrors(request, errors);
			} else {
				_log.debug("recipe found");
				_log.debug(recipe);
			}

			recipeForm.setRecipe(recipe);
		}else {
			_log.warn("view called with a null id");
			return mapping.findForward(FORWARD_ERROR);
		}

		fillSession(request);
		
		_log.debug("complete()");
		return mapping.findForward(FORWARD_VIEW);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("save()");

		RecipeForm recipeForm = (RecipeForm) form;
		Recipe recipe = recipeForm.getRecipe();

		_log.debug("recipe will be saved :");
		_log.debug(recipe);

		recipeDataService.saveRecipe(recipe);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("recipe.saved", recipe.getName()));
		saveMessages(request, messages);

		fillSession(request);
		
		// reload the recipe
		Long rid = recipe.getId();
		recipe = recipeDataService.findRecipeByID(rid);
		recipeForm.setRecipe(recipe);
		
		_log.debug("complete()");
		return mapping.findForward(FORWARD_VIEW);
	}

	
	public ActionForward addIngredient(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("addIngredient()");

		RecipeForm recipeForm = (RecipeForm) form;
		Recipe recipe = recipeForm.getRecipe();
		
		_log.debug("recipe will be saved :");
		_log.debug(recipe);
		
		// save the recipe
		recipeDataService.saveRecipe(recipe);
		
		// create the RI and update
		Long ingredientID = recipeForm.getIngredientKey();
		Long unitID = recipeForm.getUnitKey();
		Float amount = recipeForm.getAmount();
		String details = recipeForm.getDetails();
		
		Ingredient ingredient = recipeDataService.findIngredientByID(ingredientID);
		Unit unit = recipeDataService.findUnitByID(unitID);
		
		RecipeIngredient ri = new RecipeIngredient();
		ri.setAmount(amount);
		ri.setDetails(details);
		ri.setRecipe(recipe);
		ri.setIngredient(ingredient);
		ri.setUnit(unit);

		recipeDataService.addRecipeIngredientToRecipe(recipe, ri);

		// reload the recipe
		Long rid = recipe.getId();
		recipe = recipeDataService.findRecipeByID(rid);
		
		recipeForm.setRecipe(recipe);

		fillSession(request);
		_log.debug("complete");
		return mapping.findForward(FORWARD_EDIT);
	}
	
	
	public ActionForward deleteIngredient(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("deleteIngredient()");

		String ingredientID = request.getParameter("iid");
		String recipeID = request.getParameter("rid");

		Recipe recipe = recipeDataService.getRecipe(recipeID);

		_log.debug("deleting iid='" + ingredientID + "' from recipeid='" + recipeID + "'");

		recipeDataService.deleteIngedientFromRecipe(recipe, ingredientID);

		RecipeForm recipeForm = (RecipeForm) form;
		recipeForm.setRecipe(recipe);

		fillSession(request);
		_log.debug("complete()");
		return mapping.findForward(FORWARD_EDIT);
	}

}
