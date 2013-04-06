package com.neodem.recipe.service.impl;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neodem.recipe.service.WebUserService;
import com.neodem.recipe.web.beans.WebUser;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class DefaultWebUserService implements WebUserService {

	protected static Log _log = LogFactory
			.getLog(DefaultWebUserService.class.getName());


	public WebUser getValidWebUser(String username, Locale locale) {
		_log.debug("getValidWebUser()");

		if (StringUtils.isBlank(username)) {
			String msg = "userName may not be blank";
			throw new IllegalArgumentException(msg);
		}

		WebUser wu = new WebUser();
		wu.setLocale(locale);
		wu.setUsername(username);

		_log.debug("complete.");
		return wu;
	}

}