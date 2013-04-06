package com.neodem.recipe.web.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class CustomExceptionHandler extends ExceptionHandler {

	protected static Log _log = LogFactory.getLog(CustomExceptionHandler.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception,
	 *      org.apache.struts.config.ExceptionConfig,
	 *      org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response) throws ServletException {

		_log.warn("execute()");

		ActionForward forward = super.execute(ex, ae, mapping, formInstance, request, response);

		String msg = "Exception Encountered in Web Layer : " + ex.getMessage();
		_log.error(msg, ex);

		_log.warn("execute() - complete");
		return forward;
	}
}
