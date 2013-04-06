package com.neodem.recipe.web.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.web.beans.LoginToken;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public interface StrutsTokenSupport {
    
    /** will check the {@link LoginToken} from the users session for time out status
     * and also to see if it exists at all.. will return appropriate error forwards
     * 
     * @param mapping
     * @param request
     * @return a forward for an error case or null if all is ok
     */
    public ActionForward checkToken(ActionMapping mapping, HttpServletRequest request);
}
