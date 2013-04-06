
package com.neodem.recipe.service.exceptions;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class SecurityTooManyAttemptsException extends SecurityServiceCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityTooManyAttemptsException() {
        super();
    }

    public SecurityTooManyAttemptsException(String message) {
        super(message);
    }

    public SecurityTooManyAttemptsException(Throwable cause) {
        super(cause);
    }

    public SecurityTooManyAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }

}
