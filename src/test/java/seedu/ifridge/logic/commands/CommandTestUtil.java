package seedu.ifridge.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.ifridge.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ifridge.commons.core.index.Index;
import seedu.ifridge.logic.commands.exceptions.CommandException;
import seedu.ifridge.logic.commands.templatelist.template.EditTemplateItemCommand;
import seedu.ifridge.model.GroceryList;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.food.Food;
import seedu.ifridge.model.food.NameContainsKeywordsPredicate;
import seedu.ifridge.model.food.UniqueTemplateItems;
import seedu.ifridge.testutil.EditFoodDescriptorBuilder;
import seedu.ifridge.testutil.EditTemplateItemDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHEESE = "Cheddar Cheese";
    public static final String VALID_NAME_TOMATO_JUICE = "Tomato Juice";
    public static final String VALID_AMOUNT_AMY = "300g";
    public static final String VALID_AMOUNT_BOB = "300g";
    public static final String VALID_AMOUNT_CHEESE = "300g";
    public static final String VALID_AMOUNT_TOMATO_JUICE = "300ml";
    public static final String VALID_EXPIRY_DATE_AMY = "10.08.2019";
    public static final String VALID_EXPIRY_DATE_BOB = "10.08.2019";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CHEESE = " " + PREFIX_NAME + VALID_NAME_CHEESE;
    public static final String NAME_DESC_TOMATO_JUICE = " " + PREFIX_NAME + VALID_NAME_TOMATO_JUICE;
    public static final String AMOUNT_DESC_AMY = " " + PREFIX_AMOUNT + VALID_AMOUNT_AMY;
    public static final String AMOUNT_DESC_BOB = " " + PREFIX_AMOUNT + VALID_AMOUNT_BOB;
    public static final String AMOUNT_DESC_CHEESE = " " + PREFIX_AMOUNT + VALID_AMOUNT_CHEESE;
    public static final String AMOUNT_DESC_TOMATO_JUICE = " " + PREFIX_AMOUNT + VALID_NAME_TOMATO_JUICE;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "300D"; // 'D' is not a valid unit

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFoodDescriptor DESC_AMY;
    public static final EditCommand.EditFoodDescriptor DESC_BOB;
    public static final EditTemplateItemCommand.EditTemplateItemDescriptor DESC_TEMP_MINCED_MEAT;
    public static final EditTemplateItemCommand.EditTemplateItemDescriptor DESC_TEMP_TOMATO_JUICE;

    static {
        DESC_AMY = new EditFoodDescriptorBuilder().withName(VALID_NAME_AMY).withAmount(VALID_AMOUNT_AMY)
                .withExpiryDate(VALID_EXPIRY_DATE_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditFoodDescriptorBuilder().withName(VALID_NAME_BOB).withAmount(VALID_AMOUNT_BOB)
                .withExpiryDate(VALID_EXPIRY_DATE_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_TEMP_MINCED_MEAT = new EditTemplateItemDescriptorBuilder().withName(VALID_NAME_CHEESE)
                .withAmount(VALID_AMOUNT_CHEESE).build();
        DESC_TEMP_TOMATO_JUICE = new EditTemplateItemDescriptorBuilder().withName(VALID_NAME_TOMATO_JUICE)
                .withAmount(VALID_AMOUNT_TOMATO_JUICE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        GroceryList expectedGroceryList = new GroceryList(actualModel.getGroceryList());
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredGroceryItemList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedGroceryList, actualModel.getGroceryList());
        assertEquals(expectedFilteredList, actualModel.getFilteredGroceryItemList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        // For grocery list
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGroceryItemList().size());
        Food food = model.getFilteredGroceryItemList().get(targetIndex.getZeroBased());
        final String[] splitName = food.getName().fullName.split("\\s+");
        model.updateFilteredGroceryItemList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredGroceryItemList().size());

        // For template list
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTemplateList().size());
        UniqueTemplateItems template = model.getFilteredTemplateList().get(targetIndex.getZeroBased());
        assertEquals(1, model.getFilteredTemplateList().size());
    }

}
