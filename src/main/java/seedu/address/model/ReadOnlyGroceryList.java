package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.food.GroceryItem;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyGroceryList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<GroceryItem> getPersonList();

}