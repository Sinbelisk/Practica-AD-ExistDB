package Examples;

import library.model.Book;
import library.model.XMLCollection;
import library.model.Poem;
import util.XMLFileCreator;

public class CollectionCreationExample {
    private static final String OUT_PATH = "Output/";
    public static void main(String[] args) {
        createBookCollection();
        createPoemCollection();
    }

    private static void createBookCollection(){
        Book b1 = new Book("Don Quijote", "Miguel de Cervantes", 1605);
        Book b2 = new Book("1984", "George Orwell", 1949);

        XMLCollection<Book> library = new XMLCollection<>("BiBLIOTEca");
        library.addItems(b1, b2);

        createCollection(library, "libreria.xml");
    }

    private static void createPoemCollection(){
        Poem p1 = new Poem("Rima I", "Gustavo Adolfo Bécquer");
        Poem p2 = new Poem("Altazor", "Vicente Huidobro");

        XMLCollection<Poem> poems = new XMLCollection("POEMARIO");
        poems.addItems(p1, p2);
        
        createCollection(poems, "poemario.xml");
    }
    
    private static <T> void createCollection (XMLCollection<T> collection, String name){
        String parentName = collection.getName();
        try{
            XMLFileCreator.createXmlFile(collection.getCollectionList(), OUT_PATH + name, parentName);
        } catch (Exception e){
            System.err.printf("\nError when creating XML collection %s\n", parentName);
        }
    }
}
