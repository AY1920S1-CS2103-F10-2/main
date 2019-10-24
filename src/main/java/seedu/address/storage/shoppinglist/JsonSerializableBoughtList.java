package seedu.address.storage.shoppinglist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GroceryList;
import seedu.address.model.ReadOnlyGroceryList;
import seedu.address.model.food.GroceryItem;
import seedu.address.storage.JsonAdaptedFood;

/**
 * An Immutable GroceryList that is serializable to JSON format.
 */
@JsonRootName(value = "boughtlist")
class JsonSerializableBoughtList {

    public static final String MESSAGE_DUPLICATE_BOUGHT_ITEMS = "Bought list contains duplicate grocery_items(s).";

    private final List<JsonAdaptedFood> boughtItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBoughtList(@JsonProperty("boughtItems") List<JsonAdaptedFood> boughtItems) {
        this.boughtItems.addAll(boughtItems);
    }

    /**
     * Converts a given {@code ReadOnlyShoppingList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShoppingList}.
     */
    public JsonSerializableBoughtList(ReadOnlyGroceryList source) {
        boughtItems.addAll(source.getGroceryList().stream()
                .map(JsonAdaptedFood::new).collect(Collectors.toList()));
    }

    /**
     * Converts this shopping list into the model's {@code ShoppingList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GroceryList toModelType() throws IllegalValueException {
        GroceryList groceryList = new GroceryList();
        for (JsonAdaptedFood jsonAdaptedFood : boughtItems) {
            GroceryItem groceryItem = jsonAdaptedFood.toModelType();
            if (groceryList.hasPerson(groceryItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOUGHT_ITEMS);
            }
            groceryList.addPerson(groceryItem);
        }
        return groceryList;
    }

}