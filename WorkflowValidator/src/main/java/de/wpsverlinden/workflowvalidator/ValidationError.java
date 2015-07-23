package de.wpsverlinden.workflowvalidator;

/**
 * 
 * The ValidationError class is a container class for validation error messages
 * 
 * @author Oliver Verlinden
 */
public class ValidationError {
    
    private final String message;

    /**
     *
     * @param message The validation error message
     */
    public ValidationError(String message) {
        this.message = message;
    }

    /**
     *
     * @return The validation error message
     */
    public String getMessage() {
        return message;
    } 
}
