package com.neodem.recipe.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neodem.recipe.service.LogInTrackingService;
import com.neodem.recipe.service.exceptions.ServiceCheckedException;
import com.neodem.recipe.web.beans.UserLoginToken;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class DefaultLogInTrackingService implements LogInTrackingService {
	
	protected static Log _log = LogFactory
			.getLog(DefaultLogInTrackingService.class.getName());

	private Map map;

	public DefaultLogInTrackingService() {
		super();
		map = new HashMap();
	}

	public void addUser(String username, UserLoginToken token)
			throws ServiceCheckedException {
		if (token != null) {
			if (username != null) {
				Map sMap = Collections.synchronizedMap(map);
				Object o = sMap.put(username, token);
				if (o != null) {
					// there has been a potential collision
					if (o.equals(token)) {
						return;
					} else {
						// put back the original object
						sMap.put(username, o);
						String msg = "hash map collision with partNumber ["
								+ username + "]";
						_log.error(msg);
						throw new ServiceCheckedException(msg);
					}
				}
			}
		}
	}

	public UserLoginToken getUser(String username) {
		if (username != null) {
			return (UserLoginToken) map.get(username);
		}

		return null;
	}

	public void removeUser(String username) {
		if (username != null) {
			Map sMap = Collections.synchronizedMap(map);
			sMap.remove(username);
		}
	}

	public boolean isUserLoggedIn(String username) {
		if (username == null)
			return false;
		return map.containsKey(username);
	}
}