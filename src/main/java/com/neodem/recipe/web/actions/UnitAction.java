package com.neodem.recipe.web.actions;

import java.util.Collection;

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
import com.neodem.recipe.db.beans.Unit;
import com.neodem.recipe.service.RecipeDataService;
import com.neodem.recipe.web.RecipeWebConstants;
import com.neodem.recipe.web.forms.UnitForm;

public class UnitAction extends DispatchAction implements RecipeConstants, RecipeWebConstants, RecipeSpringBeanKeys {

	protected static Log _log = LogFactory.getLog(UnitAction.class.getName());

	private RecipeDataService recipeDataService;

	/**
	 * @param recipeDataService
	 *            The recipeDataService to set.
	 */
	public void setRecipeDataService(RecipeDataService recipeDataService) {
		this.recipeDataService = recipeDataService;
	}

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("show()");

		UnitForm unitForm = (UnitForm) form;

		Collection units = recipeDataService.getAllUnits();

		unitForm.setUnits(units);

		_log.debug("complete()");
		return mapping.findForward(FORWARD_SUCCESS);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("delete()");

		String id = request.getParameter("uid");

		String name = recipeDataService.getUnitNameFromID(id);

		_log.debug("deleting id='" + id + "'");

		recipeDataService.deleteUnit(id);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("unit.deleted", name));
		saveMessages(request, messages);

		UnitForm unitForm = (UnitForm) form;
		Collection units = recipeDataService.getAllUnits();
		unitForm.setUnits(units);
		
		_log.debug("complete()");
		return mapping.findForward(FORWARD_SUCCESS);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		_log.debug("save()");

		UnitForm unitForm = (UnitForm) form;
		String name = unitForm.getUnitName();
		Unit unit = new Unit(name);

		_log.debug("unit will be saved :");
		_log.debug(unit);

		recipeDataService.saveUnit(unit);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("unit.saved", name));
		saveMessages(request, messages);

		Collection units = recipeDataService.getAllUnits();
		unitForm.setUnits(units);
		
		_log.debug("complete()");
		return mapping.findForward(FORWARD_SUCCESS);
	}
}
