package com.neodem.recipe.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.service.RecipeDataService;
import com.neodem.recipe.web.forms.ViewForm;

public class ViewAction extends BaseRecipeAction {


	protected static Log _log = LogFactory.getLog(ViewAction.class.getName());
    private RecipeDataService recipeDataService;

 
    public ViewAction() {
        _log.debug("constructor.");
    }
    
    /**
     * 
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        _log.debug("execute()");

        // the form
        ViewForm viewForm = (ViewForm)form;

        // convert the string into a Long
        String id = viewForm.getId();
        if(StringUtils.isBlank(id)) {
        	String msg = "id is blank";
        	_log.debug(msg);
        	return mapping.findForward(FORWARD_FAILURE);
        }
        
        Long recipeID = new Long(id);
        
        if(recipeID==null) {
        	String msg = "recipeID could not be created from id='" + id + "'";
        	_log.warn(msg);
        	return mapping.findForward(FORWARD_ERROR);
        }
      
        _log.debug("getting recipes from service");
        Recipe recipe = recipeDataService.findRecipeByID(recipeID);

        if(recipe == null) {
        	String msg = "recipe could not be found with recipeID='" + recipeID + "'";
        	_log.warn(msg);
        	return mapping.findForward(FORWARD_NOTFOUND);
        }
  
        _log.debug("placing recipe into form");
        viewForm.setRecipe(recipe);
        
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
	 * @param recipeDataService The recipeDataService to set.
	 */
	public void setRecipeDataService(RecipeDataService recipeDataService) {
		this.recipeDataService = recipeDataService;
	}

}

