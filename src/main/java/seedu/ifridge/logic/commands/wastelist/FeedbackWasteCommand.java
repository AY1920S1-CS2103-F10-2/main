package seedu.ifridge.logic.commands.wastelist;

import static java.util.Objects.requireNonNull;

import seedu.ifridge.logic.commands.Command;
import seedu.ifridge.logic.commands.CommandResult;
import seedu.ifridge.logic.commands.exceptions.CommandException;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.WasteList;
import seedu.ifridge.model.food.Amount;
import seedu.ifridge.model.waste.WasteStatistic;

/**
 * Provides feedback to the user about user's current food waste
 */
public class FeedbackWasteCommand extends Command {

    public static final String COMMAND_WORD = "feedback";

    public static final String MESSAGE_USAGE = "wlist " + COMMAND_WORD
            + ": Gives you feedback based on your current food waste performance for the month.\n"
            + "Example: wlist " + COMMAND_WORD;

    private static final String MESSAGE_SUCCESS = "Successfully displayed feedback results";

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
        WasteStatistic predictedWastage = WasteList.getCurrentMonthPredictedWasteStatistic();
        String feedbackMessage = "This month, your predicted food wastage will be \n"
                + predictedWastage.getTotalWeight() + Amount.UNIT_KILOGRAM + ", "
                + predictedWastage.getTotalVolume() + Amount.UNIT_LITRE + ", "
                + predictedWastage.getTotalQuantity() + Amount.UNIT_QUANTITY;
        /*
        //Give feedback based on current month's waste
        model.
        1. Get the current waste list
        2. Compute current food waste, predicted food waste
        3. Compare with last month's statistics, remark if there is an increase or decrease in food waste.
         */
        return new CommandResult(feedbackMessage);
    }
}
