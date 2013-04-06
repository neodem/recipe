package com.neodem.recipe.service.exceptions;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class SecurityServiceCheckedException extends ServiceCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityServiceCheckedException() {
        super();
    }

    public SecurityServiceCheckedException(String message) {
        super(message);
    }

    public SecurityServiceCheckedException(Throwable cause) {
        super(cause);
    }

    public SecurityServiceCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

}
