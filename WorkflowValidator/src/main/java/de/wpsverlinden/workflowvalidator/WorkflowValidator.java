package de.wpsverlinden.workflowvalidator;

import de.wpsverlinden.wfvalidator.validators.WFValidator;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Oliver Verlinden
 */
public class WorkflowValidator {

    private Document doc;
    private Collection<WFValidator> validators = new HashSet<>();

    /**
     *
     * @param fileName The input file in xml format (e.g. sif archive)
     * @throws IOException Throws IOException in case of parsing error or missing input file
     */
    public WorkflowValidator(String fileName) throws IOException {
        try {
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (SAXException | ParserConfigurationException ex) {
            throw new IOException("Error parsing input file '" + fileName + "'");
        } catch (IOException ex) {
            throw new IOException("Error accessing input file '" + fileName + "'");
        }
    }

    /**
     *
     * @return Runs alls registered {@link WFValidator} instances and returns the collection of {@link ValidationError}
     */
    public Collection<ValidationError> validate() {
        List<ValidationError> errors = new LinkedList<>();
        for (WFValidator v : validators) {
            errors.addAll(v.validate(doc));
        }
        return errors;
    }

    /**
     *
     * @param validator The validator that should be executed
     */
    public void addValidator(WFValidator validator) {
        validators.add(validator);
    }
}
