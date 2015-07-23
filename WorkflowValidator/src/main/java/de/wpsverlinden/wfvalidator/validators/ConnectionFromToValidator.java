package de.wpsverlinden.wfvalidator.validators;

import de.wpsverlinden.workflowvalidator.ValidationError;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * This validator checks if the "From" and "To" attributes of the "Branch Connectors" are not equal
 * 
 * @author Oliver Verlinden
 */
public class ConnectionFromToValidator implements WFValidator {

    /**
     *
     * @param document The document to validate
     * @return A collection of validation errors
     */
    @Override
    public Collection<ValidationError> validate(Document document) {
        List<ValidationError> errorList = new LinkedList<>();

        NodeList branchConnectors = document.getElementsByTagName("WF_BRANCH_CONNECTOR");    
        for (int temp = 0; temp < branchConnectors.getLength(); temp++) {
            Node con = branchConnectors.item(temp);
            NamedNodeMap attributes = con.getAttributes();
            String connectorName = attributes.getNamedItem("NAME").getNodeValue();
            String fromStepName = attributes.getNamedItem("FROM_STEP_NAME").getNodeValue();
            String toStepName = attributes.getNamedItem("TO_STEP_NAME").getNodeValue();
            if (fromStepName.equals(toStepName)) {
                errorList.add(new ValidationError("Error in connector '" + connectorName + "' at node '" + fromStepName + "'"));
            }
        }
        return errorList;
    }
    
}
