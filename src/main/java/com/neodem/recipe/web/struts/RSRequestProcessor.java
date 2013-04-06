package com.neodem.recipe.web.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ContextLoaderPlugIn;
import org.springframework.web.struts.DelegatingActionUtils;

import com.neodem.recipe.common.RecipeSpringBeanKeys;
import com.neodem.recipe.web.service.StrutsTokenSupport;


/** RP that inserts the security before the call to the action. (also Spring aware)
 * @author Vincent Fumo
 * @version 1.0
 */
public class RSRequestProcessor extends TilesRequestProcessor implements RecipeSpringBeanKeys {

    public RSRequestProcessor() {
        super();
    }
    
    private WebApplicationContext webApplicationContext;
    private StrutsTokenSupport strutsTokenSupport;
    
    // stolen from spring DelegatingRequestProcessor.java
	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) throws ServletException {
		super.init(actionServlet, moduleConfig);
		if (actionServlet != null) {
			this.webApplicationContext = initWebApplicationContext(actionServlet, moduleConfig);
		
			// added code
			strutsTokenSupport = (StrutsTokenSupport)webApplicationContext.getBean(SPRING_BEANKEY_STRUTSTOKENSUPPORT);
		}	
	}
	
	
	//	 stolen from spring DelegatingRequestProcessor.java
	/**
	 * Fetch ContextLoaderPlugIn's WebApplicationContext from the
	 * ServletContext, containing the Struts Action beans to delegate to.
	 * @param actionServlet the associated ActionServlet
	 * @param moduleConfig the associated ModuleConfig
	 * @return the WebApplicationContext
	 * @throws IllegalStateException if no WebApplicationContext could be found
	 * @see DelegatingActionUtils#getRequiredWebApplicationContext
	 * @see ContextLoaderPlugIn#SERVLET_CONTEXT_PREFIX
	 */
	protected WebApplicationContext initWebApplicationContext(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws IllegalStateException {
		return DelegatingActionUtils.getRequiredWebApplicationContext(actionServlet, moduleConfig);
	}
	
    /**
     * overrides the Struts action perform to first check the users
     * token. If it is valid (returns null) the action is run as usual,
     * else the support class creates a forward and that's where the
     * user is sent
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param action The Action instance to be used
     * @param form The ActionForm instance to pass to this Action
     * @param mapping The ActionMapping instance to pass to this Action
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected ActionForward
        processActionPerform(HttpServletRequest request,
                             HttpServletResponse response,
                             Action action,
                             ActionForm form,
                             ActionMapping mapping)
        throws IOException, ServletException {
        
        ActionForward forward = strutsTokenSupport.checkToken(mapping, request);

        if (forward == null) {
            try {
                return (action.execute(mapping, form, request, response));
            } catch (Exception e) {
                return (processException(request, response,
                                         e, form, mapping));
            }
        }

        return forward;

    }

}
