package com.neodem.recipe.common;

public class RSCheckedException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RSCheckedException() {
        super();
    }

    public RSCheckedException(String message) {
        super(message);
    }

    public RSCheckedException(Throwable cause) {
        super(cause);
    }

    public RSCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

}
