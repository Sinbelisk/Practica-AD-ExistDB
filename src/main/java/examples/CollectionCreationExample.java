package examples;

import library.model.Book;
import library.model.XMLCollection;
import library.model.Poem;
import util.XMLFileCreator;

import java.io.File;

public class CollectionCreationExample {
    // I use File.separator because depending of the OS, it can be / or \.
    private static final String OUT_PATH = "Output" + File.separator;
    public static void main(String[] args) {
        createBookCollection();
        createPoemCollection();
    }

    /**
     * Creates a book collection.
     */
    private static void createBookCollection(){
        Book b1 = new Book("Don Quijote", "Miguel de Cervantes", 1605);
        Book b2 = new Book("1984", "George Orwell", 1949);

        // Collection names are automatically parsed to lowercase.
        XMLCollection<Book> library = new XMLCollection<>("BiBLIOTEca");
        library.addItems(b1, b2);

        createCollection(library, "libreria.xml");
    }

    /**
     * Creates a poem collection.
     */
    private static void createPoemCollection(){
        Poem p1 = new Poem("Rima I", "Gustavo Adolfo Bécquer");
        Poem p2 = new Poem("Altazor", "Vicente Huidobro");

        // Collection names are automatically parsed to lowercase.
        XMLCollection<Poem> poems = new XMLCollection<>("POEMARIO");
        poems.addItems(p1, p2);
        
        createCollection(poems, "poemario.xml");
    }


    /**
     * Initializes the specified collection
     * @param collection the XMLCollection class to fill
     * @param name name of the file.
     * @param <T> The list item.
     */
    private static <T> void createCollection (XMLCollection<T> collection, String name){
        String parentName = collection.getName();
        try{
            XMLFileCreator.createXmlFile(collection.getCollectionList(), OUT_PATH + name, parentName);
            System.out.printf("\nFile %s successfully created\n", name);
        } catch (Exception e){
            System.err.printf("\nError when creating XML collection %s\n", parentName);
        }
    }
}
