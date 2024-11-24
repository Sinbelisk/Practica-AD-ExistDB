package examples;

import library.Dao.LibraryDao;
import library.Dao.LibraryDaoIMP;
import library.model.Book;
import library.model.Essay;
import library.model.Poem;
import library.service.ExistDatabaseConnection;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

public class CollectionQueryExample {
    // The base name is the root folder of your collection; eg: /db/Store, /db/shop/storage, etc...
    private static final String BASE_COLLECTION = "/db/Libreria/";

    public static void main(String[] args) {
        ExistDatabaseConnection dbConnection = null;

        try {
            // Attempt to get the database connection using the singleton pattern
            dbConnection = ExistDatabaseConnection.getInstance();
        } catch (Exception e) {
            System.err.println("ERROR WHEN INSTANTIATING THE DATABASE.");
        }
        LibraryDao dao = new LibraryDaoIMP(dbConnection);

        // Shows the queries for books, poems and essays
        queryBooksAndPoems(dao);
        queryEssays(dao);


        // Closes the database connection.
        try{
            dbConnection.close();
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Show the result query from the Essay collection.
     * @param dao DAO instance
     */
    private static void queryEssays(LibraryDao dao) {
        List<Essay> essayQueryResult = dao.getEssaysUnderAge0(BASE_COLLECTION + "Ensayos");
        printCollection(essayQueryResult, "Essays: ");
    }

    private static void queryBooksAndPoems(LibraryDao dao) {
        // List which contains objects with the data of the query
        // "Collection name" is the name of the collection inside the base directory.
        List<Book> bookQueryResult = dao.getAllBooks(BASE_COLLECTION + "Novelas");
        List<Book> bookQueryUnder1950 = dao.getBooksUnder1950(BASE_COLLECTION + "Novelas");
        List<Poem> poemQueryResult = dao.getAllPoems(BASE_COLLECTION + "Poemas");

        // Prints
        printCollection(bookQueryResult, "Books: ");
        printCollection(bookQueryUnder1950, "Books before 1950: ");
        printCollection(poemQueryResult, "Poems: ");
    }

    /**
     * Calls the toString() method of the class.
     * @param list the list to print.
     * @param text display text
     * @param <T> item of the list
     */
    private static <T> void printCollection(List<T> list, String text){
        System.out.println("\n" + text);
        for (T item : list) {
            System.out.println(item);
        }
    }
}
