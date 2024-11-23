package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Utility class for creating XML files from Java objects using reflection.
 *
 * @author Rafael Francisco Jiménez Rayo
 */
public class XMLFileCreator {

    /**
     * Creates an XML file from a list of Java objects by inspecting their fields using reflection.
     *
     * @param objects The list of objects to be serialized into an XML file.
     * @param outputPath The path where the XML file will be saved.
     * @param parentTagName The name of the parent tag that will wrap all the objects.
     * @param <T> The type of the objects in the list.
     * @throws Exception If an error occurs during XML file creation.
     */
    public static <T> void createXmlFile(List<T> objects, String outputPath, String parentTagName) throws Exception {
        Document document = createDocument();
        Element rootElement = createRootElement(parentTagName, document);

        // Iterate through the list of objects and create XML elements for each one.
        for (T object : objects) {
            populateObjectFields(object, document, rootElement);
        }

        transformToXml(document, outputPath);
    }

    /**
     * Creates a new empty Document object using the DOM parser.
     *
     * @return A new instance of Document.
     * @throws ParserConfigurationException If there is an error creating the document builder.
     */
    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }

    /**
     * Creates the root element of the XML file based on the specified parent tag name.
     *
     * @param parentTagName The name of the parent tag.
     * @param document The Document object to create the root element in.
     * @return The root element of the XML file.
     */
    private static Element createRootElement(String parentTagName, Document document) {
        Element rootElement = document.createElement(parentTagName);
        document.appendChild(rootElement);
        return rootElement;
    }

    /**
     * Populates the XML document with elements corresponding to the object's fields.
     *
     * @param object The object whose fields will be serialized.
     * @param document The Document object where elements will be added.
     * @param rootElement The root element to append field elements to.
     * @throws IllegalAccessException If an error occurs while accessing field values.
     */
    private static void populateObjectFields(Object object, Document document, Element rootElement) throws IllegalAccessException {
        String objectTagName = object.getClass().getSimpleName().toLowerCase();
        Element objectElement = document.createElement(objectTagName);
        rootElement.appendChild(objectElement);

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // Access private fields
            String fieldName = field.getName();
            Object fieldValue = field.get(object);

            if (fieldValue != null) {
                createFieldElement(document, objectElement, fieldName, fieldValue);
            }

            field.setAccessible(false);
        }
    }

    /**
     * Creates a field element for the XML document.
     *
     * @param document The Document object to create the element in.
     * @param objectElement The object element to append the field element to.
     * @param fieldName The name of the field.
     * @param fieldValue The value of the field.
     */
    private static void createFieldElement(Document document, Element objectElement, String fieldName, Object fieldValue) {
        Element fieldElement = document.createElement(fieldName);
        fieldElement.appendChild(document.createTextNode(fieldValue.toString()));
        objectElement.appendChild(fieldElement);
    }

    /**
     * Transforms the Document object into an XML file and saves it to the specified path.
     *
     * @param document The Document object to be transformed into an XML file.
     * @param outputPath The path where the XML file will be saved.
     * @throws TransformerException If an error occurs during the transformation process.
     */
    private static void transformToXml(Document document, String outputPath) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(outputPath));
        transformer.transform(domSource, streamResult);
    }
}


