package library.model;

/**
 * Represents a book with a title, author, and publication year.
 * This class provides getters and setters for these attributes.
 */
public class Book {
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
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The new title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Sets the author of the book.
     *
     * @param author The new author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param publishYear The new publication year of the book.
     */
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
