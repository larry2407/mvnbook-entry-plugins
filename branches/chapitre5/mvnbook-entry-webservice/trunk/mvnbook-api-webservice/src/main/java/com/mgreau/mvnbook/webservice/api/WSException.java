package com.mgreau.mvnbook.webservice.api;

/**
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 *
 */
public class WSException extends Exception {
	
	private static final long serialVersionUID = -2419066600038488382L;

	/**
     * Constructs a <tt>WSException</tt> with no specified detail
     * message.
     */
    public WSException() {}

    /**
     * Constructs a <tt>WSException</tt> with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public WSException(String message) {
        super(message);
    }
    
    /**
     * Creates a new <code>WSException</code> with the given
     * detail message and cause.
     * @param msg The exceptions detail message.
     * @param cause The exceptions cause.
     */
    public WSException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    

}
