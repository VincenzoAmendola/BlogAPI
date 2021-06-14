package it.rdev.blog.api.customexceptions;

public class UserNotProprietaryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495463248791502748L;
	 
	public UserNotProprietaryException() {
		
	}
	
	public UserNotProprietaryException(String errorMessage) {
	        super(errorMessage);
	    }
}
