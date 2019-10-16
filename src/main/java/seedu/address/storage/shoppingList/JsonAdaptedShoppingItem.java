package seedu.address.storage.shoppingList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Food}.
 */
public class JsonAdaptedShoppingItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String amount;
    private final String expiryDate;
    private final boolean urgent;
    private final boolean bought;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedShoppingItem(
            @JsonProperty("name") String name,
            @JsonProperty("amount") String amount,
            @JsonProperty("expiryDate") String expiryDate,
            @JsonProperty("bought") boolean bought,
            @JsonProperty("urgent") boolean urgent) {
        this.name = name;
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.bought = bought;
        this.urgent = urgent;
    }

    /**
     * Converts a given {@code ShoppingItem} into this class for Jackson use.
     */
    public JsonAdaptedShoppingItem(ShoppingItem source) {
        name = source.getName().fullName;
        amount = source.getAmount().fullAmt;
        expiryDate = source.getExpiryDate().expiryDate;
        bought = source.isBought();
        urgent = source.isUrgent();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code ShoppingItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public ShoppingItem toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        final Amount modelAmount = new Amount(amount);
        if (bought) {
            final ExpiryDate modelExpiryDate = new ExpiryDate(expiryDate);
            return new ShoppingItem(modelName, modelAmount, modelExpiryDate);
        }

        return new ShoppingItem(modelName, modelAmount, urgent);
    }

}
