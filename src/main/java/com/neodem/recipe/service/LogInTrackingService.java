package com.neodem.recipe.service;

import com.neodem.recipe.service.exceptions.ServiceCheckedException;
import com.neodem.recipe.web.beans.UserLoginToken;

public interface LogInTrackingService {

    /** Adds a user to be tracked by the service. User is keyed by her username
     * 
     * @param username
     * @param token
     * @throws ServiceCheckedException 
     */
    public void addUser(String username, UserLoginToken token) throws ServiceCheckedException;
    
    /** Return the users token
     * 
     * @param partNumber
     * @return
     */
    public UserLoginToken getUser(String username);
    
    /** remove the user from tracking
     * 
     * @param partNumber
     */
    public void removeUser(String username);
    
    /** determine if the user is being tracked by the service 
     * 
     * @param partNumber
     * @return
     */
    public boolean isUserLoggedIn(String username);
}
