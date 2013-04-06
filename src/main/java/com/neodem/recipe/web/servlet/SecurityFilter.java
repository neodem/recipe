package com.neodem.recipe.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neodem.recipe.web.RecipeWebConstants;
import com.neodem.recipe.web.beans.LoginToken;
import com.neodem.recipe.web.beans.UserLoginToken;

public class SecurityFilter implements Filter, RecipeWebConstants {

	private String startURI;
    private String loginURI;
    private String denyURI;
        
    protected static Log _log = LogFactory.getLog(SecurityFilter.class.getName());
    private FilterConfig fc;

    public SecurityFilter() {
        super();
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession(true);

        // get the request URI
        String requestURI = request.getRequestURI();
        _log.debug("user requesting to go to : " + requestURI);
        
        // get the token from the session
        LoginToken token = (LoginToken)session.getAttribute(SESSION_LOGIN_TOKEN);
        
        // if no token is found, we place a default one into the session.   
        if (token == null) {
            _log.debug("no token found. Base token being created");
            token = new LoginToken();
            session.setAttribute(SESSION_LOGIN_TOKEN, token);
        }
        
        // use the token to check access
        boolean accessAllowed = checkAccess(token, requestURI);
        
        // if access allowed, update the token and send the user on her way
        if(accessAllowed) {
            _log.debug("accessAllowed");
            chain.doFilter(req, res);
        }
        else {
            _log.debug("accessDenied, redirecting to :'" + denyURI + "'");
            response.sendRedirect(denyURI);  
        }        
    }

    private boolean checkAccess(LoginToken token, String requestURI) {
        // UserLoginTokens are allowed full access
        if(token instanceof UserLoginToken) return true;
        
        // only 2 other cases will allow access
        if (requestURI.equals(startURI)) {
            return true;
        }

        if (requestURI.equals(loginURI)) {
            return true;
        }
      
        return false;
    }
    
    public void init(FilterConfig fc) throws ServletException {
        this.fc = fc;
        
        startURI = fc.getInitParameter("loginURI");
        loginURI = fc.getInitParameter("startURI");
        denyURI = fc.getInitParameter("denyURI");
    }

    public void destroy() {
        fc = null;
    }
}
