package library.service;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

/**
 * Singleton class for handling the connection to the eXist-db database.
 * Provides methods to interact with multiple collections within the database.
 */
public class ExistDatabaseConnection {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";


    // NOTE: This is for testing purposes only, having credentials in plain files is a bad practice.
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "";


    // Static variable to hold the single instance of the connection
    private static ExistDatabaseConnection instance;

    private Database database;

    // Private constructor to prevent instantiation from outside the class
    private ExistDatabaseConnection() throws Exception {
        String driver = "org.exist.xmldb.DatabaseImpl";
        Class<?> cl = Class.forName(driver);
        database = (Database) cl.getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    /**
     * Returns the singleton instance of the DatabaseConnection.
     * If the instance is not created yet, it initializes it.
     *
     * @return the singleton instance of the DatabaseConnection.
     * @throws Exception if there is an error connecting to the database.
     */
    public static synchronized ExistDatabaseConnection getInstance() throws Exception {
        if (instance == null) {
            instance = new ExistDatabaseConnection();
        }
        return instance;
    }

    /**
     * Retrieves a collection from the database based on the provided collection name.
     *
     * @param collectionName the name of the collection to retrieve.
     * @return the collection object.
     * @throws Exception if there is an error retrieving the collection.
     */
    public Collection getCollection(String collectionName) throws Exception {
        Collection collection = DatabaseManager.getCollection(URI + collectionName, USERNAME, PASSWORD);
        if (collection == null) {
            throw new Exception("Could not get the collection: " + collectionName);
        }
        return collection;
    }

    /**
     * Closes the database connection.
     *
     * @throws XMLDBException if there is an error closing the connection.
     */
    public void close() throws XMLDBException {
        // Currently, eXist-db's Java API does not provide a direct method to close the connection
        // However, it is good practice to release resources when done
        DatabaseManager.deregisterDatabase(database);
    }
}
