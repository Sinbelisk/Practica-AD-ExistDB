package library.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a collection of items to be serialized into XML.
 * This class provides methods to add items to the collection and retrieve the list.
 *
 * @param <T> The type of items in the collection.
 */
public class XMLCollection<T> {

    // The name of the collection, which will be used as the XML root element name.
    private final String name;
    private final List<T> collectionList = new ArrayList<>();

    /**
     * Constructor to create a collection with a specified name.
     * The name will be used to represent the collection in XML.
     *
     * @param name The name of the collection.
     */
    public XMLCollection(String name){
        this.name = name.toLowerCase();
    }

    /**
     * Adds a single item to the collection.
     * @param item The item to be added to the collection.
     */
    public void addItem(T item){
        collectionList.add(item);
    }

    /**
     * Adds multiple items to the collection.
     * @param items The items to be added to the collection.
     */
    public void addItems(T... items){
        collectionList.addAll(Arrays.asList(items));
    }

    /**
     * Retrieves the list of items in the collection.
     * @return A list of items in the collection.
     */
    public List<T> getCollectionList() {
        return collectionList;
    }

    /**
     * Retrieves the name of the collection
     * @return The name of the collection.
     */
    public String getName() {
        return name;
    }
}
