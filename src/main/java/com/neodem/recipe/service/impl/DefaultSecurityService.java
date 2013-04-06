package com.neodem.recipe.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neodem.recipe.common.RSObject;
import com.neodem.recipe.db.beans.User;
import com.neodem.recipe.db.dao.UserDAO;
import com.neodem.recipe.service.LogInTrackingService;
import com.neodem.recipe.service.SecurityService;
import com.neodem.recipe.service.exceptions.SecurityBadPasswordException;
import com.neodem.recipe.service.exceptions.SecurityBadUsernameException;
import com.neodem.recipe.service.exceptions.SecurityLoggedInAlreadyException;
import com.neodem.recipe.service.exceptions.SecurityServiceCheckedException;
import com.neodem.recipe.service.exceptions.SecurityTooManyAttemptsException;
import com.neodem.recipe.service.exceptions.ServiceCheckedException;
import com.neodem.recipe.web.beans.LoginToken;
import com.neodem.recipe.web.beans.UserLoginToken;


/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class DefaultSecurityService extends RSObject implements SecurityService {

    protected static Log _log =  LogFactory.getLog(DefaultSecurityService.class.getName());

    // set internally
    private long timeOutAmount;
    private int allowed;
   
    // need to be set externally
    private UserDAO userDAO;
    private LogInTrackingService logInTrackingService;
    
    public DefaultSecurityService() {
        super();
    }
    
    // called after properties have been set
    public void init() {

    }

	public UserLoginToken getToken(String username, String password, int attempts) throws SecurityServiceCheckedException {

        _log.debug("getToken(" + username + ")");

        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("username cannot be blank");
        if (StringUtils.isBlank(password)) throw new IllegalArgumentException("password cannot be blank");
        
        _log.debug("processing login attempt...");

        // 1) check attempts
        if (attempts > allowed) {
            String msg = "user is allowed " + allowed + " failed attempts. Has tried " + attempts + " times.";
            _log.error(msg);
            throw new SecurityTooManyAttemptsException(msg);
        }

        // 2) get the User 
        User user = userDAO.findByUsername(username);
        if (user == null) {
            String msg = "no user record found for username = '" + username + "'";
            _log.error(msg);
            throw new SecurityBadUsernameException(msg);
        }

        // 3) check the password
        if (!password.equals(user.getPassword())) {
            String msg = "password doesn't match record";
            _log.error(msg);
            throw new SecurityBadPasswordException(msg);
        }

        // 4) create new token
        UserLoginToken token = new UserLoginToken(username);

        _log.info("user validated, token created : " + token);
        return token;
    }

    public boolean login(String partNumber, UserLoginToken token) throws SecurityLoggedInAlreadyException {
        _log.debug("logging user into system :" + token);
        
        if (StringUtils.isBlank(partNumber)) {
            _log.warn("partNumber cannot be blank");
            return false;
        }
        
        if (token == null){
            _log.warn("token cannot be null");
            return false;
        }
        
        // check if user is logged in already
        if (logInTrackingService.isUserLoggedIn(partNumber)) {
            String msg = "user '" + partNumber + "' is logged in already";
            _log.debug(msg);

            // 1a) check to see if the old session is timed out. If so, we log them out
            UserLoginToken uToken = logInTrackingService.getUser(partNumber);
            
            long delta = System.currentTimeMillis() - uToken.getLastAccess();
     
            if(_log.isDebugEnabled()) {           
                StringBuffer buff = new StringBuffer();
                buff.append("checking to see if old session is timed out..");
                buff.append("[");
                buff.append(delta);        
                buff.append(", allowable = ");
                buff.append(timeOutAmount);
                buff.append("]");
                
                _log.debug(buff.toString());
            }
  
            if (delta > timeOutAmount) {
                _log.debug("old session has timed out.. logging out user...");
                logout(uToken);
            }
            else {
                throw new SecurityLoggedInAlreadyException(msg);
            }
        }
            
        try {
            logInTrackingService.addUser(partNumber, token);
        } catch (ServiceCheckedException e) {
            _log.debug("user not logged in.");
            return false;
        }
        _log.debug("user logged in.");
        return true;
    }

    public void logout(LoginToken token) {
        _log.debug("logout()");
        if (token instanceof UserLoginToken) {
            UserLoginToken userToken = (UserLoginToken)token;
            String username = userToken.getUsername();
            logInTrackingService.removeUser(username);
        }
        _log.debug("complete.");
    }

    public boolean isTokenTimedOut(UserLoginToken token) {
        return token.isTimedOut(timeOutAmount);
    }



    /**
     * @return Returns the logInTrackingService.
     */
    public LogInTrackingService getLogInTrackingService() {
        return logInTrackingService;
    }

    /**
     * @param logInTrackingService The logInTrackingService to set.
     */
    public void setLogInTrackingService(LogInTrackingService logInTrackingService) {
        this.logInTrackingService = logInTrackingService;
    }

	/**
	 * @return Returns the allowed.
	 */
	public int getAllowed() {
		return allowed;
	}

	/**
	 * @param allowed The allowed to set.
	 */
	public void setAllowed(int allowed) {
		this.allowed = allowed;
	}


	/**
	 * @return Returns the timeOutAmount.
	 */
	public long getTimeOutAmount() {
		return timeOutAmount;
	}

	/**
	 * @param timeOutAmount The timeOutAmount to set.
	 */
	public void setTimeOutAmount(long timeOutAmount) {
		this.timeOutAmount = timeOutAmount;
	}

	/**
	 * @return Returns the userDAO.
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO The userDAO to set.
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}






}