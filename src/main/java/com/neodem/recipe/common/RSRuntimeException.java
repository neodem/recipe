package com.neodem.recipe.common;

public class RSRuntimeException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RSRuntimeException() {
        super();
    }

    public RSRuntimeException(String message) {
        super(message);
    }

    public RSRuntimeException(Throwable cause) {
        super(cause);
    }

    public RSRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
