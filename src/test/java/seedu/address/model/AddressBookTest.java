package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroceryItems.ALICE;
import static seedu.address.testutil.TypicalGroceryItems.getTypicalAddressBook;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.GroceryItem;
import seedu.address.testutil.GroceryItemBuilder;

public class AddressBookTest {

    private final GroceryList addressBook = new GroceryList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        GroceryList newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    /*
    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        GroceryItem editedAlice = new GroceryItemBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<GroceryItem> newFoods = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newFoods);

        assertThrows(DuplicateFoodException.class, () -> addressBook.resetData(newData));
    }

     */

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addGroceryItem(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addGroceryItem(ALICE);
        GroceryItem editedAlice = new GroceryItemBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class GroceryListStub implements ReadOnlyGroceryList {
        private final ObservableList<GroceryItem> foods = FXCollections.observableArrayList();

        GroceryListStub(Collection<GroceryItem> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<GroceryItem> getPersonList() {
            return foods;
        }
    }

}
