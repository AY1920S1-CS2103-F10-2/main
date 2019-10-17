package seedu.address.logic.commands.grocerylist;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.food.NameContainsCloseExpiryDatePredicate;

/**
 * Finds and lists all food in iFridge whose expiry dates is within r days.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "rem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display list with all food expiring within r days "
            + "Parameters: "
            + PREFIX_REMINDER + "REMINDER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_REMINDER + "3";

    private final NameContainsCloseExpiryDatePredicate predicate;

    public ReminderCommand(NameContainsCloseExpiryDatePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroceryItemList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GROCERY_LIST_LISTED_OVERVIEW,
                        model.getFilteredGroceryItemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderCommand // instanceof handles nulls
                && predicate.equals(((ReminderCommand) other).predicate)); // state check
    }
}
