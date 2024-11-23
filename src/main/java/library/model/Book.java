package library.model;

/**
 * Represents a book with a title, author, and publication year.
 * This class provides getters and setters for these attributes.
 */
public class Book implements DatabaseModel{
    private String title;
    private String author;
    private int publishYear;

    /**
     * Constructs a new Book object with the specified title, author, and publication year.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param publishYear The year the book was published.
     */
    public Book(String title, String author, int publishYear) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
    }

    /**
     * Empty constructor for building an instance using reflection.
     */
    public Book(){

    }

    /**
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }


    /**
     * Retrieves the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }


    /**
     * Retrieves the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public int getPublishYear() {
        return publishYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                '}';
    }
}
