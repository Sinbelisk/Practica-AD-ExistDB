package library.Dao;

import library.model.Book;
import library.model.Poem;
import library.service.ExistDatabaseConnection;
import java.util.List;

/**
 * Implementation of the {@link LibraryDao} interface that interacts with the eXist-db database
 * to retrieve books and poems from the specified collections.
 * This class provides methods to query for books and poems based on various criteria.
 * <p>
 * Yes, this class has a comically complex structure for a simple query.
 */
public class LibraryDaoIMP implements LibraryDao {
    private ExistDatabaseConnection dbConnection;
    private QueryManager queryManager;

    /**
     * Constructor that initializes the singleton connection to the eXist-db database.
     * If the connection fails, it catches and logs the exception.
     */
    public LibraryDaoIMP(ExistDatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        queryManager = new QueryManager(dbConnection);
    }

    /**
     * Retrieves all books from the specified collection that were published before 1950.
     * The query is executed using XPath syntax on the XML data stored in the collection.
     *
     * @param collectionName The name of the collection to query for books.
     * @return A list of {@link Book} objects published before 1950.
     */
    @Override
    public List<Book> getBooksUnder1950(String collectionName) {
        String query = "for $book in //book[year < 1950] return $book";
        return queryManager.queryItems(collectionName, query, Book.class);
    }

    /**
     * Retrieves all books from the specified collection.
     * The query is executed using XPath syntax on the XML data stored in the collection.
     *
     * @param collectionName The name of the collection to query for books.
     * @return A list of all {@link Book} objects in the specified collection.
     */
    @Override
    public List<Book> getAllBooks(String collectionName) {
        String query = "for $book in //book return $book";
        return queryManager.queryItems(collectionName, query, Book.class);
    }

    /**
     * Retrieves all poems from the specified collection.
     * The query is executed using XPath syntax on the XML data stored in the collection.
     *
     * @param collectionName The name of the collection to query for poems.
     * @return A list of all {@link Poem} objects in the specified collection.
     */
    @Override
    public List<Poem> getAllPoems(String collectionName) {
        String query = "for $poem in //poem return $poem";
        return queryManager.queryItems(collectionName, query, Poem.class);
    }
}
