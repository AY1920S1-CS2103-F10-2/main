package seedu.address.logic.commands.wastelist;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ListWasteCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = "wlist " + COMMAND_WORD
            + ": Lists out your current food waste for a given month. "
            + "If no month is specified, the food waste for the current month is displayed.\n"
            + "Optional Parameters: " + PREFIX_MONTH + "MONTH_OF_YEAR"
            + "Example: wlist " + COMMAND_WORD + "09.2019";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //model.updateFilteredWasteList(PREDICATE_SHOW_ALL_PERSONS)
        /*
        Include updateFilteredWasteList method in model interface

         */
        return null;
    }
}