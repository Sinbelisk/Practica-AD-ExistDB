package library.Dao;

import library.model.DatabaseModel;
import library.service.ExistDatabaseConnection;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Manages the execution of database queries and maps the results to instances of {@link DatabaseModel} objects.
 * <p>
 * This class facilitates querying collections in an eXist-db database and automatically maps the resulting XML data
 * to objects of a specified {@link DatabaseModel} implementation using reflection. This approach allows for dynamic
 * data retrieval and object population, making it flexible for various use cases.
 * </p>
 *
 * <p>
 * The class handles the creation of query services, processing of query results, and the mapping of XML content to
 * Java objects while ensuring that errors are logged for debugging and troubleshooting purposes.
 * </p>
 *
 * @author Rafael Francisco Jiménez Rayo
 */
public class QueryManager {

    private static final Logger logger = Logger.getLogger(QueryManager.class.getName());
    private final ExistDatabaseConnection dbConnection;

    /**
     * Creates a new {@code QueryManager} with the specified database connection.
     *
     * @param databaseConnection The connection to the eXist-db database.
     */
    public QueryManager(ExistDatabaseConnection databaseConnection) {
        this.dbConnection = databaseConnection;
    }

    /**
     * Executes an XPath query on a specified collection in the eXist-db database and maps the results to objects
     * of the specified {@link DatabaseModel} implementation.
     *
     * @param collectionName The name of the collection to query in the database.
     * @param query The XPath query to execute.
     * @param clazz The class type of the objects to map the XML results to.
     * @param <T> The type of objects to be returned, which must extend {@link DatabaseModel}.
     * @return A list of objects of type {@code T} that match the query, or an empty list if no matches are found or an error occurs.
     */
    public <T extends DatabaseModel> List<T> queryItems(String collectionName, String query, Class<T> clazz) {
        try (Collection col = dbConnection.getCollection(collectionName)) {
            XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = service.query(query);
            col.close();

            return processQueryResults(result, clazz);
        } catch (Exception e) {
            logError(Level.SEVERE, "Error querying the collection", e);
            return Collections.emptyList();
        }
    }

    /**
     * Processes the query results and maps them to a list of objects of the specified type.
     *
     * @param result The {@link ResourceSet} containing the results of the query.
     * @param clazz The class type of the objects to map the results to.
     * @param <T> The type of objects to be returned, which must extend {@link DatabaseModel}.
     * @return A list of objects of type {@code T} derived from the XML query results.
     */
    private <T extends DatabaseModel> List<T> processQueryResults(ResourceSet result, Class<T> clazz) {
        List<T> items = new ArrayList<>();
        try {
            ResourceIterator iterator = result.getIterator();
            while (iterator.hasMoreResources()) {
                XMLResource resource = (XMLResource) iterator.nextResource();
                T item = mapXmlToObject(resource.getContent().toString(), clazz);
                if (item != null) items.add(item);
            }
        } catch (Exception e) {
            logError(Level.SEVERE, "Error processing query results", e);
        }
        return items;
    }

    /**
     * Maps the XML content of a {@link XMLResource} to an object of the specified class type.
     *
     * @param content The XML content as a {@link String} to be mapped.
     * @param clazz The class type of the object to map the XML content to.
     * @param <T> The type of the object, which must extend {@link DatabaseModel}.
     * @return An object of type {@code T} populated with data from the XML content, or {@code null} if the mapping fails.
     */
    private <T extends DatabaseModel> T mapXmlToObject(String content, Class<T> clazz) {
        try {
            // Create a new instance of the specified class type
            T item = clazz.getDeclaredConstructor().newInstance();

            // Iterate over the declared fields in the class
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Ensure field is accessible

                // Extract the field value from the XML content using its name
                String fieldValue = extractFieldValue(content, field.getName());
                if (fieldValue != null) {
                    // Assign the extracted value to the field of the object
                    setFieldValue(item, field, fieldValue);
                }
                // Ensure the field is no longer accessible.
                field.setAccessible(false);
            }
            return item;

        } catch (Exception e) {
            logError(Level.SEVERE, "Error mapping XML to object", e);
            return null;
        }
    }

    /**
     * Extracts the value of a specified field from XML content using a Regular Expression to match the field name.
     *
     * @param content The XML content to search within.
     * @param fieldName The name of the field to extract from the XML.
     * @return The field value as a {@link String}, or {@code null} if the field is not found.
     */
    private String extractFieldValue(String content, String fieldName) {
        Matcher matcher = Pattern.compile("<" + fieldName + ">(.*?)</" + fieldName + ">").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Sets the value of a field in an object using reflection.
     *
     * @param item The object whose field value is to be set.
     * @param field The field to set the value for.
     * @param value The value to assign to the field.
     * @param <T> The type of the object whose field is being set.
     */
    private <T extends DatabaseModel> void setFieldValue(T item, Field field, String value) {
        try {
            if (value != null) {
                // Assign the value based on the field's type
                if (field.getType().equals(String.class)) {
                    field.set(item, value);
                } else if (field.getType().equals(int.class)) {
                    field.set(item, Integer.parseInt(value));
                }
            }
        } catch (Exception e) {
            logError(Level.SEVERE, "Error setting field value", e);
        }
    }

    /**
     * Logs errors using the Java {@link Logger} with a specific severity level.
     *
     * @param level The level of the log message (e.g., {@link Level#SEVERE}).
     * @param message A descriptive error message.
     * @param e The exception associated with the error, if any.
     */
    private void logError(Level level, String message, Exception e) {
        logger.log(level, message, e);
    }
}
