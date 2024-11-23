package Examples;

import library.model.Essay;
import library.model.XMLCollection;
import util.XMLFileCreator;

import java.io.File;


public class NewCollectionQueryExample {
    // I use File.separator because depending of the OS, it can be / or \.
    private static final String OUT_PATH = "Output" + File.separator;

    public static void main(String[] args) {
        createBookCollection();
    }

    /**
     * Creates a book collection.
     */
    private static void createBookCollection(){
        Essay e1 = new Essay("La Rebelión de las Masas", "José Ortega y Gasset", 1930);
        Essay e2 = new Essay("El arte de la guerra", "Sun Tzu", -500);

        XMLCollection<Essay> essays = new XMLCollection<>("ensayos");
        essays.addItems(e1, e2);

        createCollection(essays, "ensayos.xml");
    }

    /**
     * Initializes the specified collection
     * @param collection the XMLCollection class to fill
     * @param name name of the file.
     */
    private static void createCollection (XMLCollection<Essay> collection, String name){
        String parentName = collection.getName();
        try{
            XMLFileCreator.createXmlFile(collection.getCollectionList(), OUT_PATH + name, parentName);
            System.out.printf("\nFile %s successfully created\n", name);
        } catch (Exception e){
            System.err.printf("\nError when creating XML collection %s\n", parentName);
            System.err.println(e.getMessage());
        }
    }
}
