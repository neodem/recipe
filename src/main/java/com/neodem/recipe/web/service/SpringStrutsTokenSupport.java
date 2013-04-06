package com.neodem.recipe.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.neodem.recipe.service.SecurityService;
import com.neodem.recipe.web.RecipeWebConstants;
import com.neodem.recipe.web.beans.LoginToken;
import com.neodem.recipe.web.beans.UserLoginToken;

public class SpringStrutsTokenSupport implements StrutsTokenSupport, RecipeWebConstants {

    protected static Log _log = LogFactory.getLog(SpringStrutsTokenSupport.class.getName());
    private SecurityService securityService;

    public ActionForward checkToken(ActionMapping mapping, HttpServletRequest request) {
        // determine if there is a LoginToken present (used to check time out)
        HttpSession session = request.getSession();
        LoginToken token = (LoginToken)session.getAttribute(SESSION_LOGIN_TOKEN);
        if (token == null) {
            ActionMessages errors = new ActionMessages();
            ActionMessage error = new ActionMessage("global.error.login.noToken");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            String msg = "no loginToken found";
            _log.error(msg);
            return mapping.findForward(FORWARD_ERROR);
        }

        //update the tokens access
        token.updateAccess();

        // if we are working with a UserLoginToken, we check it's timeout status
        if (token instanceof UserLoginToken) {

            UserLoginToken uToken = (UserLoginToken)token;

            //determine if the user has timed out
            if (securityService.isTokenTimedOut(uToken)) {
                _log.debug("user timed out : " + token);

                // log out the user with the security service
                securityService.logout(token);

                // invalidate their session
                if (session != null) {
                    session.invalidate();
                    _log.debug("session invalidated");
                }

                // send an error
                ActionMessages errors = new ActionMessages();
                ActionMessage error = new ActionMessage("global.error.login.timeout");
                errors.add(ActionMessages.GLOBAL_MESSAGE, error);

                //-------- had to steal saveErrors from Action
                // saveErrors(request, errors);
                if ((errors == null) || errors.isEmpty()) {
                    request.removeAttribute(Globals.ERROR_KEY);
                }
                else {
                    request.setAttribute(Globals.ERROR_KEY, errors);
                }
                //------------ end stolen code

                _log.debug("user being redirected to the timeout page");
                return mapping.findForward(FORWARD_TIMEOUT);
            }
        }

        return null;
    }

    /**
     * @return Returns the securityService.
     */
    public SecurityService getSecurityService() {
        return securityService;
    }

    /**
     * @param securityService The securityService to set.
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}
