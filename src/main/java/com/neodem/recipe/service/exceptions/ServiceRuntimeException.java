package com.neodem.recipe.service.exceptions;

import com.neodem.recipe.common.RSRuntimeException;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class ServiceRuntimeException extends RSRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
