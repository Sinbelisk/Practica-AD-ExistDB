package library.model;

/**
 * Represents a poem with a title and an author.
 * This class provides getters and setters for the poem's title and author.
 */
public class Poem {
    private String title;
    private String author;

    /**
     * Constructs a new Poem object with the specified title and author.
     *
     * @param title The title of the poem.
     * @param author The author of the poem.
     */
    public Poem(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Retrieves the title of the poem.
     *
     * @return The title of the poem.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the poem.
     *
     * @param title The new title of the poem.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the author of the poem.
     *
     * @return The author of the poem.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the poem.
     *
     * @param author The new author of the poem.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
