package com.neodem.recipe.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.db.beans.Ingredient;
import com.neodem.recipe.db.beans.Recipe;

/**
 * 
 * @author Vincent Fumo
 * @version 1.0
 */
public class ViewForm extends AbstractEJValidatorForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Recipe recipe;
	private Ingredient ingredient;


    public String toString() {
        return "ViewForm : id = " + id;
    }

    public ViewForm() {
        super();
        resetFields();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest req) {
        resetFields();
    }

    /**
     *  
     */
    private void resetFields() {
    	id = "";
    	recipe = null;
    	ingredient = null;
    }

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the recipe.
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe The recipe to set.
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	/**
	 * @return Returns the ingredient.
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	/**
	 * @param ingredient The ingredient to set.
	 */
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}





 
}
