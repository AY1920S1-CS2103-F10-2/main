package seedu.ifridge.logic.commands.templatelist;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.ifridge.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ifridge.model.Model.PREDICATE_SHOW_ALL_TEMPLATES;
import static seedu.ifridge.model.Model.PREDICATE_SHOW_ALL_TEMPLATES_ITEMS;
import static seedu.ifridge.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.ifridge.commons.core.index.Index;
import seedu.ifridge.logic.commands.Command;
import seedu.ifridge.logic.commands.CommandResult;
import seedu.ifridge.logic.commands.CommandTestUtil;
import seedu.ifridge.logic.commands.EditCommand;
import seedu.ifridge.logic.commands.exceptions.CommandException;
import seedu.ifridge.logic.commands.templatelist.template.EditTemplateItemCommand;
import seedu.ifridge.model.GroceryList;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.TemplateList;
import seedu.ifridge.model.food.Food;
import seedu.ifridge.model.food.TemplateItem;
import seedu.ifridge.model.food.UniqueTemplateItems;
import seedu.ifridge.testutil.EditFoodDescriptorBuilder;
import seedu.ifridge.testutil.EditTemplateItemDescriptorBuilder;
import seedu.ifridge.testutil.TemplateItemBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class TemplateCommandTestUtil {

    public static final String VALID_NAME_NUTS = "Peauts";
    public static final String VALID_NAME_ORANGES = "Oranges";
    public static final String VALID_NAME_CHEESE = "Cheddar Cheese";
    public static final String VALID_NAME_TOMATO_JUICE = "Tomato Juice";
    public static final String VALID_NAME_RICE = "Rice";
    public static final String VALID_AMOUNT_NUTS = "120g";
    public static final String VALID_AMOUNT_ORANGES = "15units";
    public static final String VALID_AMOUNT_CHEESE = "300g";
    public static final String VALID_AMOUNT_TOMATO_JUICE = "300ml";
    public static final String VALID_AMOUNT_RICE = "2kg";
    public static final String VALID_EXPIRY_DATE_NUTS = "10/12/2019";
    public static final String INVALID_AMOUNT_FOR_VOLUME = "300g";
    public static final String INVALID_AMOUNT_FOR_QUANTITY = "300ml";
    public static final String INVALID_AMOUNT_FOR_UNIT = "300g";

    public static final String NAME_DESC_CHEESE = " " + PREFIX_NAME + VALID_NAME_CHEESE;
    public static final String NAME_DESC_TOMATO_JUICE = " " + PREFIX_NAME + VALID_NAME_TOMATO_JUICE;
    public static final String AMOUNT_DESC_CHEESE = " " + PREFIX_AMOUNT + VALID_AMOUNT_CHEESE;
    public static final String AMOUNT_DESC_TOMATO_JUICE = " " + PREFIX_AMOUNT + VALID_NAME_TOMATO_JUICE;

    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "300D"; // 'D' is not a valid unit

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditTemplateItemCommand.EditTemplateItemDescriptor DESC_TEMP_MINCED_MEAT;
    public static final EditTemplateItemCommand.EditTemplateItemDescriptor DESC_TEMP_TOMATO_JUICE;
    public static final TemplateItem TEMPLATE_ITEM_CHEESE;
    public static final TemplateItem TEMPLATE_ITEM_RICE;

    static {
        DESC_TEMP_MINCED_MEAT = new EditTemplateItemDescriptorBuilder().withName(VALID_NAME_CHEESE)
                .withAmount(VALID_AMOUNT_CHEESE).build();
        DESC_TEMP_TOMATO_JUICE = new EditTemplateItemDescriptorBuilder().withName(VALID_NAME_TOMATO_JUICE)
                .withAmount(VALID_AMOUNT_TOMATO_JUICE).build();
        TEMPLATE_ITEM_CHEESE = new TemplateItemBuilder().withName(VALID_NAME_CHEESE)
                .withAmount(VALID_AMOUNT_CHEESE).build();
        TEMPLATE_ITEM_RICE = new TemplateItemBuilder().withName(VALID_NAME_RICE).withAmount(VALID_AMOUNT_RICE).build();
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
        TemplateList expectedTemplateList = new TemplateList(actualModel.getTemplateList());
        List<UniqueTemplateItems> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTemplateList());
        List<TemplateItem> expectedFilteredTemplate = new ArrayList<>(actualModel.getFilteredTemplateToBeShown());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTemplateList, actualModel.getTemplateList());
        assertEquals(expectedFilteredList, actualModel.getFilteredTemplateList());
        assertEquals(expectedFilteredTemplate, actualModel.getFilteredTemplateToBeShown());
    }

    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showItemAtIndex(Model model, Index templateIndex, Index itemIndex) {
        requireNonNull(templateIndex);

        //Template list
        assertTrue(templateIndex.getZeroBased() < model.getFilteredTemplateList().size());
        UniqueTemplateItems template = model.getFilteredTemplateList().get(templateIndex.getZeroBased());
        model.updateFilteredTemplateList(PREDICATE_SHOW_ALL_TEMPLATES);
        model.updateFilteredTemplateToBeShown(PREDICATE_SHOW_ALL_TEMPLATES_ITEMS);
        assertEquals(3, model.getFilteredTemplateList().size());
        assertEquals(0, model.getFilteredTemplateToBeShown().size());

        //Template to be shown
        if (itemIndex != null) {
            assertTrue(itemIndex.getZeroBased() < model.getFilteredTemplateToBeShown().size());
            TemplateItem templateItem = model.getFilteredTemplateToBeShown().get(itemIndex.getZeroBased());
        }
    }
}
