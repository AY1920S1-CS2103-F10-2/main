package seedu.ifridge.logic.commands.shoppinglist;

import static java.util.Objects.requireNonNull;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.ifridge.logic.commands.Command;
import seedu.ifridge.logic.commands.CommandResult;
import seedu.ifridge.logic.commands.exceptions.CommandException;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.food.ShoppingItem;

/**
 * Adds a person to the address book.
 */
public class AddShoppingCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a food item to the shopping list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_AMOUNT + "AMOUNT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Apples "
            + PREFIX_AMOUNT + "300g";

    public static final String MESSAGE_SUCCESS = "New shopping item added: %1$s";
    public static final String MESSAGE_DUPLICATE_SHOPPING_ITEM = "This shopping item already exists in shopping list";

    private final ShoppingItem toAdd;

    /**
     * Creates an AddShoppingCommand to add the specified {@code ShoppingItem}
     */
    public AddShoppingCommand(ShoppingItem food) {
        requireNonNull(food);
        toAdd = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasShoppingItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SHOPPING_ITEM);
        }

        model.addShoppingItem(toAdd);
        CommandResult commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        commandResult.setShoppingListCommand();
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddShoppingCommand // instanceof handles nulls
                && toAdd.equals(((AddShoppingCommand) other).toAdd));
    }
}