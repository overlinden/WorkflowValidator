package de.wpsverlinden.wfvalidator.validators;

import de.wpsverlinden.workflowvalidator.ValidationError;
import java.util.Collection;
import org.w3c.dom.Document;

/**
 *
 * Defines basic functionalities of all validators
 * 
 * @author Oliver Verlinden
 */
public interface WFValidator {
    
    /**
     *
     * @param document The document to validate
     * @return A collection of validation errors
     */
    public Collection<ValidationError> validate(Document document);
    
}
