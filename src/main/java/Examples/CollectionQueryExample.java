package Examples;

import library.Dao.LibraryDao;
import library.Dao.LibraryDaoIMP;
import library.model.Book;
import library.model.Poem;
import library.service.ExistDatabaseConnection;

import java.util.List;

public class CollectionQueryExample {
    private static final String BASE_NAME = "/db/Libreria/";

    public static void main(String[] args) {
        ExistDatabaseConnection dbConnection = null;

        try {
            // Attempt to get the database connection using the singleton pattern
            dbConnection = ExistDatabaseConnection.getInstance();
        } catch (Exception e) {
            System.err.println("ERROR WHEN INSTANTIATING THE DATABASE.");
        }
        LibraryDao dao = new LibraryDaoIMP(dbConnection);

        // List which contains objects with the data of the query
        List<Book> bookQueryResult = dao.getAllBooks(BASE_NAME + "Novelas");
        List<Book> bookQueryUnder1950 = dao.getBooksUnder1950(BASE_NAME + "Novelas");
        List<Poem> poemQueryResult = dao.getAllPoems(BASE_NAME + "Poemas");

        printCollection(bookQueryResult, "Libros: ");
        printCollection(bookQueryUnder1950, "Libros anteriores a 1950: ");
        printCollection(poemQueryResult, "Poemas: ");

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
