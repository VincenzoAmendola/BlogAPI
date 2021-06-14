package it.rdev.blog.api.customexceptions;

public class IdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8007130266733720508L;
	
	public IdNotFoundException() {
	}
	
	public IdNotFoundException(String message) {
		super(message);
	}
}
