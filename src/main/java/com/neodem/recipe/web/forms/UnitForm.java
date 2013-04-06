package com.neodem.recipe.web.forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vincent Fumo
 * @version 1.0
 */
public class UnitForm extends AbstractEJValidatorForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String unitName;
	private Collection units;

    public UnitForm() {
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
    	unitName = "";
    }

	/**
	 * @return Returns the unitName.
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName The unitName to set.
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return Returns the units.
	 */
	public Collection getUnits() {
		return units;
	}

	/**
	 * @param units The units to set.
	 */
	public void setUnits(Collection units) {
		this.units = units;
	}



 
}
