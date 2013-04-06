package com.neodem.recipe.web.actions;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.neodem.recipe.service.SecurityService;
import com.neodem.recipe.web.beans.LoginToken;


/**
 * 
 * @author Vincent Fumo
 * @version 1.0
 */
public class LogoutAction extends BaseRecipeAction {
    protected static Log _log = LogFactory.getLog(LogoutAction.class.getName());
    
    private SecurityService securityService;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        _log.debug("execute()");

        HttpSession session = request.getSession(false);       
        LoginToken token = (LoginToken)session.getAttribute(SESSION_LOGIN_TOKEN);

        // alert the security service that a user has logged out
        securityService.logout(token);

        // clear out the session
        if (session != null) {
            session.invalidate();
            _log.debug("session invalidated");
        }

        _log.debug("complete()");

        return mapping.findForward(FORWARD_SUCCESS);
    }
    
    /**
     * @param securityService The securityService to set.
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    
}

