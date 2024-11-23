package library.model;

/**
 * Data class for Essays.
 */
public class Essay {
    private String title;
    private String author;
    private int year;
    public Essay(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public Essay(){

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
