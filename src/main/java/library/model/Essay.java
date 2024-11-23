package library.model;

/**
 * Data class for Essays.
 */
public class Essay implements DatabaseModel{
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

    @Override
    public String toString() {
        return "Essay{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
