package seedu.ifridge.model.food;

import org.junit.jupiter.api.Test;
import seedu.ifridge.model.food.exceptions.DuplicateShoppingItemException;
import seedu.ifridge.model.food.exceptions.FoodNotFoundException;
import seedu.ifridge.testutil.ShoppingItemBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.ifridge.testutil.Assert.assertThrows;
import static seedu.ifridge.testutil.TypicalShoppingList.BANANA;
import static seedu.ifridge.testutil.TypicalShoppingList.SPAGHETTI;

public class UniqueShoppingListTest {

    private final UniqueShoppingList uniqueShoppingList = new UniqueShoppingList();

    @Test
    public void contains_nullShoppingItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.contains(null));
    }

    @Test
    public void contains_shoppingItemNotInList_returnsFalse() {
        assertFalse(uniqueShoppingList.contains(BANANA));
    }

    @Test
    public void contains_shoppingItemInList_returnsTrue() {
        uniqueShoppingList.add(BANANA);
        assertTrue(uniqueShoppingList.contains(BANANA));
    }

    @Test
    public void contains_shoppingItemWithSameName_returnsTrue() {
        uniqueShoppingList.add(BANANA);
        ShoppingItem editedBanana = new ShoppingItemBuilder(BANANA).build();
        assertTrue(uniqueShoppingList.contains(editedBanana));
    }

    @Test
    public void add_nullShoppingItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.add(null));
    }

    @Test
    public void add_duplicateShoppingItem_throwsCommandException() {
        uniqueShoppingList.add(BANANA);
        assertThrows(DuplicateShoppingItemException.class, () -> uniqueShoppingList.add(BANANA));
    }

    @Test
    public void setShoppingItem_nullTargetShoppingItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.setShoppingItem(null, BANANA));
    }

    @Test
    public void setShoppingItem_nullEditedShoppingItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.setShoppingItem(BANANA, null));
    }

    @Test
    public void setShoppingItem_targetShoppingItemNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueShoppingList.setShoppingItem(BANANA, BANANA));
    }

    @Test
    public void setShoppingItem_editedShoppingItemIsSameShoppingItem_success() {
        uniqueShoppingList.add(BANANA);
        uniqueShoppingList.setShoppingItem(BANANA, BANANA);
        UniqueShoppingList expectedUniqueShoppingList = new UniqueShoppingList();
        expectedUniqueShoppingList.add(BANANA);
        assertEquals(expectedUniqueShoppingList, uniqueShoppingList);
    }

    @Test
    public void setShoppingItem_editedShoppingItemHasSameIdentity_success() {
        uniqueShoppingList.add(BANANA);
        ShoppingItem editedBanana = new ShoppingItemBuilder(BANANA).withAmount("5units").build();
        uniqueShoppingList.setShoppingItem(BANANA, editedBanana);
        UniqueShoppingList expectedUniqueShoppingList = new UniqueShoppingList();
        expectedUniqueShoppingList.add(editedBanana);
        assertEquals(expectedUniqueShoppingList, uniqueShoppingList);
    }

    @Test
    public void setShoppingItem_editedShoppingItemHasDifferentIdentity_success() {
        uniqueShoppingList.add(BANANA);
        uniqueShoppingList.setShoppingItem(BANANA, SPAGHETTI);
        UniqueShoppingList expectedUniqueShoppingList = new UniqueShoppingList();
        expectedUniqueShoppingList.add(SPAGHETTI);
        assertEquals(expectedUniqueShoppingList, uniqueShoppingList);
    }

    @Test
    public void setShoppingItem_editedShoppingItemHasNonUniqueIdentity_throwsDuplicateFoodException() {
        uniqueShoppingList.add(BANANA);
        uniqueShoppingList.add(SPAGHETTI);
        assertThrows(DuplicateShoppingItemException.class, () -> uniqueShoppingList.setShoppingItem(BANANA, SPAGHETTI));
    }

    @Test
    public void remove_nullShoppingItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.remove(null));
    }

    @Test
    public void remove_shoppingItemDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueShoppingList.remove(BANANA));
    }

    @Test
    public void remove_existingShoppingItem_removesShoppingItem() {
        uniqueShoppingList.add(BANANA);
        uniqueShoppingList.remove(BANANA);
        UniqueShoppingList expectedUniqueShoppingList = new UniqueShoppingList();
        assertEquals(expectedUniqueShoppingList, uniqueShoppingList);
    }

    @Test
    public void setShoppingList_nullUniqueShoppingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.setShoppingItems((UniqueShoppingList) null));
    }

    @Test
    public void setShoppingList_uniqueShoppingList_replacesOwnListWithProvidedUniqueShoppingList() {
        uniqueShoppingList.add(BANANA);
        UniqueShoppingList expectedUniqueShoppingList = new UniqueShoppingList();
        expectedUniqueShoppingList.add(SPAGHETTI);
        uniqueShoppingList.setShoppingItems(expectedUniqueShoppingList);
        assertEquals(expectedUniqueShoppingList, uniqueShoppingList);
    }

    @Test
    public void setShoppingList_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShoppingList.setShoppingItems((List<ShoppingItem>) null));
    }

    @Test
    public void setShoppingList_list_replacesOwnListWithProvidedList() {
        uniqueShoppingList.add(BANANA);
        List<ShoppingItem> groceryList = Collections.singletonList(SPAGHETTI);
        uniqueShoppingList.setShoppingItems(groceryList);
        UniqueShoppingList expectedUniqueShoppingList = new UniqueShoppingList();
        expectedUniqueShoppingList.add(SPAGHETTI);
        assertEquals(expectedUniqueShoppingList, uniqueShoppingList);
    }

    @Test
    public void setShoppingList_listWithDuplicateShoppingItem_throwsDuplicateFoodException() {
        List<ShoppingItem> listWithDuplicateShoppingItems = Arrays.asList(BANANA, BANANA);
        assertThrows(DuplicateShoppingItemException.class, () ->
                uniqueShoppingList.setShoppingItems(listWithDuplicateShoppingItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueShoppingList.asUnmodifiableObservableList().remove(0));
    }
}