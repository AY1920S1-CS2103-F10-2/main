package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.food.ShoppingItem;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyShoppingList {

    /**
     * Returns an unmodifiable view of the shopping list.
     * This list will not contain any duplicate shopping items.
     */
    ObservableList<ShoppingItem> getShoppingList();

}
