package com.neodem.recipe.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vincent Fumo
 * @version 1.0
 */
public class SearchForm extends AbstractEJValidatorForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String searchName;
	String type;


    public String toString() {
        return "SearchForm : searchName = " + searchName;
    }

    public SearchForm() {
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
    	searchName = "";
    }

	/**
	 * @return Returns the searchName.
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName The searchName to set.
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}


 
}
