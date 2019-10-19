package seedu.address.logic.commands.templatelist;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.UniqueTemplateItems;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteTemplateListCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "tlist " + COMMAND_WORD
            + ": Deletes the template  identified by the index number used in the displayed template list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: tlist " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Template: %1$s";

    private final Index targetIndex;

    public DeleteTemplateListCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<UniqueTemplateItems> lastShownList = model.getFilteredTemplateList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
        }

        UniqueTemplateItems templateToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTemplate(templateToDelete);

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, templateToDelete));
        commandResult.setTemplateListCommand();

        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTemplateListCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTemplateListCommand) other).targetIndex)); // state check
    }
}
