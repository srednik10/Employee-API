package exception;

public class ResourceDoesNotExistException extends RuntimeException{

	public ResourceDoesNotExistException(String message) {
		super(message);
	}
	
	public ResourceDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
