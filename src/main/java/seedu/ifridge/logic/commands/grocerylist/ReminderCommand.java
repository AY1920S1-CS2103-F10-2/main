package seedu.ifridge.logic.commands.grocerylist;

import static java.util.Objects.requireNonNull;

import seedu.ifridge.commons.core.Messages;
import seedu.ifridge.logic.commands.Command;
import seedu.ifridge.logic.commands.CommandResult;
import seedu.ifridge.logic.parser.CliSyntax;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.food.NameContainsCloseExpiryDatePredicate;

/**
 * Finds and lists all food in iFridge whose expiry dates is within r days.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "rem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display list with all food expiring within r days "
            + "Parameters: "
            + CliSyntax.PREFIX_REMINDER + "REMINDER \n"
            + "Example: glist " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_REMINDER + "3";

    private final NameContainsCloseExpiryDatePredicate predicate;

    public ReminderCommand(NameContainsCloseExpiryDatePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroceryItemList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredGroceryItemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderCommand // instanceof handles nulls
                && predicate.equals(((ReminderCommand) other).predicate)); // state check
    }
}
