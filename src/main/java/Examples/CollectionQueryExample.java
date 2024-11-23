package Examples;

import library.Dao.LibraryDao;
import library.Dao.LibraryDaoIMP;
import library.model.Book;
import library.service.ExistDatabaseConnection;

import java.util.List;

public class CollectionQueryExample {

    public static void main(String[] args) {
        ExistDatabaseConnection dbConnection = null;

        try {
            // Attempt to get the database connection using the singleton pattern
            dbConnection = ExistDatabaseConnection.getInstance();
        } catch (Exception e) {
            System.err.println("ERROR WHEN INSTANCIATING THE DATABASE.");
        }
        LibraryDao dao = new LibraryDaoIMP(dbConnection);


        List<Book> bookQueryResult = dao.getAllBooks("/db/Libreria/Novelas");

        for (Book book : bookQueryResult) {
            System.out.println(book.getAuthor());
            System.out.println(book.getTitle());
            System.out.println(book.getPublishYear());
        }

    }

}
