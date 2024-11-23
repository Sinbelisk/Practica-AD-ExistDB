package library.Dao;

import library.model.Book;
import library.model.Poem;

import java.util.List;

public interface LibraryDao {
    List<Book> getBooksUnder1950(String collectionName);
    List<Book> getAllBooks(String collectionName);
    List<Poem> getAllPoems(String collectionName);
}
