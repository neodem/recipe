package com.neodem.recipe.service.exceptions;

import com.neodem.recipe.common.RSCheckedException;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class ServiceCheckedException extends RSCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceCheckedException() {
        super();
    }

    public ServiceCheckedException(String message) {
        super(message);
    }

    public ServiceCheckedException(Throwable cause) {
        super(cause);
    }

    public ServiceCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

}
