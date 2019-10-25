package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalBoughtList.getTypicalBoughtList;
import static seedu.address.testutil.TypicalGroceryItems.getTypicalGroceryList;
import static seedu.address.testutil.TypicalShoppingList.getTypicalShoppingList;
import static seedu.address.testutil.TypicalTemplateList.getTypicalTemplateList;
import static seedu.address.testutil.TypicalWasteArchive.getTypicalWasteArchive;

import org.junit.jupiter.api.BeforeEach;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGroceryList(), new UserPrefs(), getTypicalTemplateList(),
                getTypicalWasteArchive(), getTypicalShoppingList(), getTypicalBoughtList());
    }

    /*@Test
    public void execute_duplicatePerson_throwsCommandException() {
        Food foodInList = model.getGroceryList().getPersonList().get(0);
        assertCommandFailure(new AddCommand(foodInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }*/

}
