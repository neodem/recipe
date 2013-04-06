package com.neodem.recipe.web.actions;


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

import com.neodem.recipe.common.RSRuntimeException;
import com.neodem.recipe.service.SecurityService;
import com.neodem.recipe.service.WebUserService;
import com.neodem.recipe.service.exceptions.SecurityBadPasswordException;
import com.neodem.recipe.service.exceptions.SecurityBadUsernameException;
import com.neodem.recipe.service.exceptions.SecurityLoggedInAlreadyException;
import com.neodem.recipe.service.exceptions.SecurityServiceCheckedException;
import com.neodem.recipe.service.exceptions.SecurityTooManyAttemptsException;
import com.neodem.recipe.web.beans.LoginToken;
import com.neodem.recipe.web.beans.UserLoginToken;
import com.neodem.recipe.web.beans.WebUser;
import com.neodem.recipe.web.forms.LoginForm;

/**Will process a login attempt. The success or failure is determined 
 * by the SecurityService instance. After the SecurityService 
 * determines that the user is valid and has correct credentials,
 *  a WebUser object is created. The WebUser object
 *  may not be created if the user can't be located
 * 
 * On any failure of the action, the reason is encoded into 
 * the Session via an appropriate ActionMessage.
 * 
 * @author Vincent Fumo
 * @version 1.0
 */
public class LoginAction extends BaseRecipeAction {

    protected static Log _log = LogFactory.getLog(LoginAction.class.getName());
    private WebUserService webUserService;
    private SecurityService securityService;
 
    public LoginAction() {
        _log.debug("constructor.");
    }
    
    /**
     * 
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        _log.debug("execute()");

        //-------------------------------------------------------------------------------
        //-----------------------------------------set up base variables and objects used
        //-------------------------------------------------------------------------------
        ActionMessages errors = new ActionMessages();
        HttpSession session = request.getSession(true);
        LoginToken sessionToken = (LoginToken)session.getAttribute(SESSION_LOGIN_TOKEN);

        // check to see if this is a reload of the page (by accident maybe). If so, we ignore
        if (sessionToken instanceof UserLoginToken) {
            // this user is already logged in and has pressed reload on her browser, we ignore
            _log.debug("reload of action : complete()");
            return mapping.findForward(FORWARD_SUCCESS);
        }

        // the form
        LoginForm loginForm = (LoginForm)form;

        // the form values 
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        // the number of attempts at logon for this session
        Integer attempts = (Integer)session.getAttribute(SESSION_ATTEMPTS);
                    
        UserLoginToken token = null;
 
        //-------------------------------------------------------------------------------

        _log.debug("using LoginForm : " + loginForm);

        // 1) get the number of logon attempts
        int attempt;
        if (attempts == null) {
            attempt = 1;
        }
        else {
            attempt = attempts.intValue() + 1;
        }
        _log.debug("logon attempt #" + attempt);

        // 2) invalidate the session
        _log.debug("invalidating session");
        session.setAttribute(SESSION_ATTEMPTS, new Integer(attempt));
        session.setAttribute(SESSION_LOGIN_TOKEN, null);

        // 3) attemt to log on the user
        _log.debug("checking bean against security Service");
        try {
            token = securityService.getToken(username, password, attempt);
        }

        // bad username?
        catch (SecurityBadUsernameException e) {
            ActionMessage error = new ActionMessage("recipe.error.login.badUsername");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            saveErrors(request, errors);
            return mapping.findForward(FORWARD_BAD_LOGIN);
        }

        // bad password?
        catch (SecurityBadPasswordException e) {
            ActionMessage error = new ActionMessage("recipe.error.login.badPassword");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            saveErrors(request, errors);
            return mapping.findForward(FORWARD_BAD_LOGIN);
        }

        // attempted to log in too many times?
        catch (SecurityTooManyAttemptsException e) {
            ActionMessage error = new ActionMessage("recipe.error.login.tooManyAttempts");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            saveErrors(request, errors);
            return mapping.findForward(FORWARD_FAILURE);
        }

        // other unknown exception?
        catch (SecurityServiceCheckedException u) {
            String msg = "unknown exception in LoginAction : " + u.getMessage();
            _log.error(msg);
            throw new RSRuntimeException(u);
        }

        // 4) get the webUser
        _log.debug("getting the webUser object");
        WebUser wu = webUserService.getValidWebUser(username, request.getLocale());

        // actually log the user into the system
        _log.debug("logging user into system");
        boolean result;
        try {
            result = securityService.login(username, token);
        } 
        
        // logged in already?
        catch (SecurityLoggedInAlreadyException e) {
            ActionMessage error = new ActionMessage("recipe.error.login.multipleLogins");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            saveErrors(request, errors);
            return mapping.findForward(FORWARD_FAILURE);
        }
        
        if(result == false) {
            ActionMessage error = new ActionMessage("recipe.error.login.unknown");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error); 
            saveErrors(request, errors);
            return mapping.findForward(FORWARD_FAILURE);
        }
        
        //--------------user has been validated and there is a WebParticipant object created
        // Lets now set up their session
        _log.debug("setting up session");

        // make a new session
        session = request.getSession(true);

        // set the attributes
        session.setAttribute(SESSION_USER, wu);
        session.setAttribute(SESSION_LOGIN_TOKEN, token);
        session.setAttribute(SESSION_ATTEMPTS, new Integer(0));

        _log.debug("complete()");
        return mapping.findForward(FORWARD_SUCCESS);
    }

    /**
     * @param securityService The securityService to set.
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

	/**
	 * @param webUserService The webUserService to set.
	 */
	public void setWebUserService(WebUserService webUserService) {
		this.webUserService = webUserService;
	}
}