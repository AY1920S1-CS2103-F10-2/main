package seedu.ifridge.logic.commands.shoppinglist;

import static java.util.Objects.requireNonNull;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ifridge.model.Model.PREDICATE_SHOW_ALL_SHOPPING_ITEMS;

import java.util.List;
import java.util.Optional;

import seedu.ifridge.commons.core.Messages;
import seedu.ifridge.commons.core.index.Index;
import seedu.ifridge.commons.util.CollectionUtil;
import seedu.ifridge.logic.commands.Command;
import seedu.ifridge.logic.commands.CommandResult;
import seedu.ifridge.logic.commands.exceptions.CommandException;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.food.Amount;
import seedu.ifridge.model.food.Name;
import seedu.ifridge.model.food.ShoppingItem;

//For now edit shopping command will not be able to change the expiry date
/**
 * Edits the details of an existing person in the address book.
 */
public class EditShoppingCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the shopping item identified "
            + "by the index number used in the displayed shopping list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "apple"
            + PREFIX_AMOUNT + "2";

    public static final String MESSAGE_EDIT_SHOPPING_ITEM_SUCCESS = "Edited shopping item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditShoppingItemDescriptor editShoppingItemDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editShoppingItemDescriptor details to edit the person with
     */
    public EditShoppingCommand(Index index, EditShoppingItemDescriptor editShoppingItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editShoppingItemDescriptor);

        this.index = index;
        this.editShoppingItemDescriptor = new EditShoppingItemDescriptor(editShoppingItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ShoppingItem> lastShownList = model.getFilteredShoppingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SHOPPING_ITEM_DISPLAYED_INDEX);
        }

        ShoppingItem shoppingItemToEdit = lastShownList.get(index.getZeroBased());
        ShoppingItem editedShoppingItem = createEditedShoppingItem(shoppingItemToEdit, editShoppingItemDescriptor);

        if (!shoppingItemToEdit.isBought()) {
            model.setShoppingItem(shoppingItemToEdit, editedShoppingItem);
            model.updateFilteredShoppingList(PREDICATE_SHOW_ALL_SHOPPING_ITEMS);
            model.commitShoppingList();
            model.commitBoughtList();
        }
        model.sortShoppingItems();
        CommandResult commandResult =
                new CommandResult(String.format(MESSAGE_EDIT_SHOPPING_ITEM_SUCCESS, editedShoppingItem));
        commandResult.setShoppingListCommand();
        return commandResult;
    }

    /**
     * Creates and returns a {@code ShoppingItem} with the details of {@code shoppingItemToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static ShoppingItem createEditedShoppingItem(ShoppingItem shoppingItemToEdit,
                                                         EditShoppingItemDescriptor editShoppingItemDescriptor) {
        assert shoppingItemToEdit != null;

        Name updatedName = editShoppingItemDescriptor.getName().orElse(shoppingItemToEdit.getName());
        Amount updatedAmount = editShoppingItemDescriptor.getAmount().orElse(shoppingItemToEdit.getAmount());

        return new ShoppingItem(updatedName, updatedAmount);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditShoppingItemDescriptor {
        private Name name;
        private Amount amount;

        public EditShoppingItemDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditShoppingItemDescriptor(EditShoppingItemDescriptor toCopy) {
            setName(toCopy.name);
            setAmount(toCopy.amount);
            //setExpiryDate(toCopy.expiryDate);
            //setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, amount);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

    }
}
