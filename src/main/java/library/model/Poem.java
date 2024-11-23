package library.model;

/**
 * Represents a poem with a title and an author.
 * This class provides getters and setters for the poem's title and author.
 */
public class Poem implements DatabaseModel {
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
     * Empty constructor for building an instance using reflection.
     */
    public Poem(){

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
     * Retrieves the author of the poem.
     *
     * @return The author of the poem.
     */
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Poem{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
