package de.wpsverlinden.workflowvalidator;

import de.wpsverlinden.wfvalidator.validators.ConnectionFromToValidator;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver Verlinden
 */
public class WorkflowValidatorApp {
    
    /**
     *
     * @param args Command line arguments (Argument 1 must be the input file path)
     */
    public static void main(String[] args) {
        WorkflowValidator validator;
        try {
            validator = new WorkflowValidator(args[0]);
            validator.addValidator(new ConnectionFromToValidator());
            Collection<ValidationError> errors = validator.validate();
            printErrors(errors);  
        } catch (IOException ex) {
            Logger.getLogger(WorkflowValidatorApp.class.getName()).log(Level.SEVERE, ex.getMessage());
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(WorkflowValidatorApp.class.getName()).log(Level.SEVERE, "Please specify the input file name");
        }
    }

    private static void printErrors(Collection<ValidationError> errors) {
        for (ValidationError e : errors) {
            System.out.println(e.getMessage());
        }
    }
}
