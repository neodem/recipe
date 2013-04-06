package com.neodem.recipe.web.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neodem.common.beans.AbstractSerializedBean;

public class LoginToken extends AbstractSerializedBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Log _log = LogFactory.getLog(LoginToken.class.getName());
 
    /** the lsat time this token was accessed
     * 
     */
    protected long lastAccess;
       
    /** the previous access time.. (ie. the time before lastAccess)
     * 
     */
    protected long previousAccess;
    
    /**
     * 
     */
    public String toString() {
        StringBuffer b = new StringBuffer(60);
        b.append("Login Token. Last access : ");
        b.append(lastAccess);

        return b.toString();
    }
   
    /**
     * 
     *
     */
    public LoginToken() {
        super();
        previousAccess = lastAccess = System.currentTimeMillis();
    }
    
    /**
     * 
     * @param allowable
     * @return
     */
    public boolean isTimedOut(long allowable) {  
        long delta = lastAccess - previousAccess;
        
        if(_log.isDebugEnabled()) {
            StringBuffer buff = new StringBuffer();
            buff.append("isTimedOut()");
            buff.append("-[");
            buff.append(delta);        
            buff.append(", allowable = ");
            buff.append(allowable);
            buff.append("]");
            
            _log.debug(buff.toString());
        }
        
        if(delta > allowable) {
            _log.debug("token is timed out (max = " + allowable + ")");
            return true;
        }
        
        _log.debug("token not timed out.");
        return false;
    }
    
    /** will set the internal counters to indicate that the token has been accessed
     * 
     *
     */
    public void updateAccess() {
        previousAccess = lastAccess;
        lastAccess = System.currentTimeMillis();
    }

    /**
     * @return Returns the lastAccess.
     */
    public long getLastAccess() {
        return lastAccess;
    }
}
