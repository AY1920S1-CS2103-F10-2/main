package seedu.ifridge.logic.commands.grocerylist;

import static java.util.Objects.requireNonNull;

import seedu.ifridge.logic.commands.Command;
import seedu.ifridge.logic.commands.CommandResult;
import seedu.ifridge.logic.commands.exceptions.CommandException;
import seedu.ifridge.logic.parser.CliSyntax;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.food.GroceryItem;

/**
 * Adds a person to the address book.
 */
public class AddGroceryCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a food item to the grocery list. "
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_AMOUNT + "AMOUNT "
            + CliSyntax.PREFIX_EXPIRY_DATE + "EXPIRY_DATE "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: glist " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "Apples "
            + CliSyntax.PREFIX_AMOUNT + "300g"
            + CliSyntax.PREFIX_EXPIRY_DATE + "30/11/2019"
            + CliSyntax.PREFIX_TAG + "healthy "
            + CliSyntax.PREFIX_TAG + "fruit";

    public static final String MESSAGE_SUCCESS = "New grocery item added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROCERY_ITEM = "This food item already exists in the grocery list";

    private final GroceryItem toAdd;

    /**
     * Creates an AddGroceryCommand to add the specified {@code Person}
     */
    public AddGroceryCommand(GroceryItem food) {
        requireNonNull(food);
        toAdd = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGroceryItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROCERY_ITEM);
        }

        model.addGroceryItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroceryCommand // instanceof handles nulls
                && toAdd.equals(((AddGroceryCommand) other).toAdd));
    }
}
