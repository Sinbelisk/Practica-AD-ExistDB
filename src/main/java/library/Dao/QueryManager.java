package library.Dao;

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

/**
 * A class for executing ExistDB queries with XQuery.
 * @author Rafael Franciso Jiménez Rayo.
 */
public class QueryManager {
    private final ExistDatabaseConnection dbConnection;

    public QueryManager(ExistDatabaseConnection databaseConnection){
        this.dbConnection = databaseConnection;
    }

    /**
     * Queries a collection in the eXist-db database and maps the results to objects of a specified class type.
     *
     * @param collectionName The name of the collection to query.
     * @param query The XPath query to execute.
     * @param clazz The class type of the objects to map the XML results to.
     * @param <T> The type of objects in the list.
     * @return A list of objects of type {@code T} that match the query.
     */
    public <T> List<T> queryItems(String collectionName, String query, Class<T> clazz) {
        try {
            Collection col = dbConnection.getCollection(collectionName);
            XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = service.query(query);

            return processQueryResults(result, clazz);
        } catch (Exception e) {
            logError("Error querying the collection", e);
            return Collections.emptyList();
        }
    }

    /**
     * Processes the query results and maps them to a list of objects.
     *
     * @param result The result set from the query execution.
     * @param clazz The class type of the objects.
     * @param <T> The type of objects in the list.
     * @return A list of objects of type {@code T}.
     */
    private <T> List<T> processQueryResults(ResourceSet result, Class<T> clazz) {
        List<T> items = new ArrayList<>();
        try {
            ResourceIterator iterator = result.getIterator();
            while (iterator.hasMoreResources()) {
                XMLResource resource = (XMLResource) iterator.nextResource();
                T item = mapXmlToObject((String) resource.getContent(), clazz);
                if (item != null) items.add(item);
            }
        } catch (Exception e) {
            logError("Error processing query results", e);
        }
        return items;
    }

    /**
     * Maps XML content to an object of the specified class type.
     *
     * @param content The XML content.
     * @param clazz The class type of the object.
     * @param <T> The type of the object.
     * @return An object of type {@code T}, or null if mapping fails.
     */
    private <T> T mapXmlToObject(String content, Class<T> clazz) {
        try {
            T item = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldValue = extractFieldValue(content, field.getName());
                setFieldValue(item, field, fieldValue);
            }
            return item;
        } catch (Exception e) {
            logError("Error mapping XML to object", e);
            return null;
        }
    }

    /**
     * Extracts a field value from XML content using a Regular Expression.
     *
     * @param content The XML content.
     * @param fieldName The field name to extract.
     * @return The extracted value, or null if not found.
     */
    private String extractFieldValue(String content, String fieldName) {
        try {
            Pattern pattern = Pattern.compile("<" + fieldName + ">(.*?)</" + fieldName + ">");
            Matcher matcher = pattern.matcher(content);
            return matcher.find() ? matcher.group(1) : null;
        } catch (Exception e) {
            logError("Error extracting field value", e);
            return null;
        }
    }

    /**
     * Sets a field value on the specified object using reflection.
     *
     * @param item The object.
     * @param field The field to set.
     * @param fieldValue The value to set.
     * @param <T> The type of the object.
     */
    private <T> void setFieldValue(T item, Field field, String fieldValue) {
        try {
            if (fieldValue != null) {
                if (field.getType().equals(String.class)) {
                    field.set(item, fieldValue);
                } else if (field.getType().equals(int.class)) {
                    field.set(item, Integer.parseInt(fieldValue));
                }
            }
        } catch (Exception e) {
            logError("Error setting field value", e);
        }
    }

    /**
     * Logs errors to the standard error output.
     *
     * @param message The error message.
     * @param e The exception.
     */
    private void logError(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
    }
}
