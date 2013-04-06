package com.neodem.recipe.service;

import com.neodem.recipe.service.exceptions.SecurityBadPasswordException;
import com.neodem.recipe.service.exceptions.SecurityBadUsernameException;
import com.neodem.recipe.service.exceptions.SecurityLoggedInAlreadyException;
import com.neodem.recipe.service.exceptions.SecurityServiceCheckedException;
import com.neodem.recipe.service.exceptions.SecurityTooManyAttemptsException;
import com.neodem.recipe.web.beans.LoginToken;
import com.neodem.recipe.web.beans.UserLoginToken;

public interface SecurityService {
	  /** use the userName and password to check to see if a user exists and is valid. If they
     * are, a {@link UserLoginToken} is returned which can be used to log the user
     * into the system. If the user does not exist or their attempt is invalid for some
     * reason, an appropriate exception is thrown.
     * 
     * @param username
     * @param password
     * @param attempt the attemt number
     * @return
     * @throws SecurityTooManyAttemptsException if the user has unsuccessfully attempted to log on too many times in her session
     * @throws SecurityBadUsernameException if the username can't be found in the database
     * @throws SecurityBadPasswordException if the password doesn't match the one on record in the database
     * @throws SecurityServiceCheckedException on any other error
     */
    public UserLoginToken getToken(String username, String password, int attempt) throws SecurityServiceCheckedException;

    /** log the user into the system.
     * 
     * The tokens are tracked by the service and can therefore be checked for multiple
     * log on attempts. If a user attempts to log on and is already in the system as being
     * logged on, one of two things will happen:
     *
     * - If the current session is not timed-out (see isTokenTimedOut()), a
     * {@link SecurityLoggedInAlreadyException} is thrown. 
     * - If the current session is timed-out, the old user is logged out and the new attemt is allowed 
     *  
     * @param username
     * @param token
     * @return true if user has been logged in, false if not.
     * @throws SecurityLoggedInAlreadyException if the user is already logged in  
     */
    public boolean login(String username, UserLoginToken token) throws SecurityLoggedInAlreadyException;
    
    /** logout a token from the system
     * 
     * @param token
     */
    public void logout(LoginToken token);

    /** test to see if a token has timed out or not. This method will only check the timeout
     * value of a UserLoginToken.
     * 
     * @param token
     * @return
     */
    public boolean isTokenTimedOut(UserLoginToken token);
}
