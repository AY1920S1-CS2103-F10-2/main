package seedu.ifridge.logic.commands.templatelist.template;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ifridge.testutil.Assert.assertThrows;
import static seedu.ifridge.testutil.TypicalIndexes.INDEX_FIRST;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

import seedu.ifridge.commons.core.GuiSettings;
import seedu.ifridge.commons.core.IFridgeSettings;
import seedu.ifridge.model.Model;
import seedu.ifridge.model.ReadOnlyGroceryList;
import seedu.ifridge.model.ReadOnlyShoppingList;
import seedu.ifridge.model.ReadOnlyTemplateList;
import seedu.ifridge.model.ReadOnlyUserPrefs;
import seedu.ifridge.model.ReadOnlyWasteList;
import seedu.ifridge.model.TemplateList;
import seedu.ifridge.model.UnitDictionary;
import seedu.ifridge.model.WasteList;
import seedu.ifridge.model.food.Food;
import seedu.ifridge.model.food.GroceryItem;
import seedu.ifridge.model.food.Name;
import seedu.ifridge.model.food.ShoppingItem;
import seedu.ifridge.model.food.TemplateItem;
import seedu.ifridge.model.food.UniqueTemplateItems;
import seedu.ifridge.model.waste.WasteMonth;
import seedu.ifridge.model.waste.WasteReport;
import seedu.ifridge.testutil.TemplateItemBuilder;

public class AddTemplateItemCommandTest {
    @Test
    public void constructor_nullTemplateItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTemplateItemCommand(null, null));
    }

    /**@Test
    public void execute_templateItemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTemplateItemAdded modelStub = new ModelStubAcceptingTemplateItemAdded();
        TemplateItem validTemplateItem = new TemplateItemBuilder().build();

        CommandResult commandResult = new AddTemplateItemCommand(INDEX_FIRST, validTemplateItem).execute(modelStub);

        assertEquals(String.format(AddTemplateItemCommand.MESSAGE_SUCCESS, validTemplateItem),
        commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTemplateItem), modelStub.templateItemsAdded);
    }**/

    @Test
    public void equals() {
        TemplateItem mincedMeat = new TemplateItemBuilder().withName("Ground Pork").build();
        TemplateItem freshVeg = new TemplateItemBuilder().withName("Tomato").build();
        AddTemplateItemCommand addMincedMeatCommand = new AddTemplateItemCommand(INDEX_FIRST, mincedMeat);
        AddTemplateItemCommand addFreshVegCommand = new AddTemplateItemCommand(INDEX_FIRST, freshVeg);

        // same object -> returns true
        assertTrue(addMincedMeatCommand.equals(addMincedMeatCommand));

        // same values -> returns true
        AddTemplateItemCommand addMincedMeatCommandCopy = new AddTemplateItemCommand(INDEX_FIRST, mincedMeat);
        assertTrue(addMincedMeatCommand.equals(addMincedMeatCommandCopy));

        // different types -> returns false
        assertFalse(addMincedMeatCommand.equals(1));

        // null -> returns false
        assertFalse(addMincedMeatCommand.equals(null));

        // different person -> returns false
        assertFalse(addMincedMeatCommand.equals(addFreshVegCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public IFridgeSettings getIFridgeSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIFridgeSettings(IFridgeSettings iFridgeSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getGroceryListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroceryListFilePath(Path groceryListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UnitDictionary getUnitDictionary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroceryItem(GroceryItem food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroceryList(ReadOnlyGroceryList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGroceryList getGroceryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroceryItem(GroceryItem food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroceryItem(GroceryItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroceryItem(GroceryItem target, GroceryItem editedFood) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<GroceryItem> getFilteredGroceryItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroceryItemList(Predicate<GroceryItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitGroceryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGroceryList undoGroceryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGroceryList redoGroceryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoGroceryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoGroceryList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public Path getTemplateListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTemplateListFilePath(Path templateListFilePath) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setTemplateList(ReadOnlyTemplateList templateList) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ReadOnlyTemplateList getTemplateList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasTemplate(UniqueTemplateItems toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteTemplate(UniqueTemplateItems target) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void addTemplate(UniqueTemplateItems toAdd) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setTemplate(UniqueTemplateItems target, UniqueTemplateItems editedTemplate) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<UniqueTemplateItems> getFilteredTemplateList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void updateFilteredTemplateList(Predicate<UniqueTemplateItems> predicate) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setShownTemplate(UniqueTemplateItems templateToBeShown) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TemplateItem> getFilteredTemplateToBeShown() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TemplateItem> updateFilteredTemplateToBeShown() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean containsTemplateItemWithName(Food foodItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Name getNameTemplateToBeShown() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getWasteListFilePath() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setWasteListFilePath(Path wasteListFilePath) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setWasteList(ReadOnlyWasteList wasteList) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ReadOnlyWasteList getWasteList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ReadOnlyWasteList getWasteListByMonth(WasteMonth wasteMonth) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void addWasteItem(GroceryItem toAdd) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<GroceryItem> getFilteredWasteItemList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<GroceryItem> getFilteredWasteItemListByMonth(WasteMonth wasteMonth) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public SortedSet<WasteMonth> getDescendingWasteMonths() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasWasteMonth(WasteMonth wasteMonth) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public WasteMonth getEarliestWasteMonth() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public WasteMonth getLatestWasteMonth() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TreeMap<WasteMonth, WasteList> getWasteArchive() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredWasteItemList(WasteMonth wasteMonth) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWasteReport(WasteReport wasteReport) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public WasteReport getWasteReport() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitWasteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyWasteList undoWasteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyWasteList redoWasteList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public Path getShoppingListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShoppingListFilePath(Path shoppingListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addShoppingItem(ShoppingItem food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ShoppingItem getShoppingItem(ShoppingItem shoppingItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShoppingList(ReadOnlyShoppingList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyShoppingList getShoppingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasShoppingItem(ShoppingItem food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteShoppingItem(ShoppingItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShoppingItem(ShoppingItem target, ShoppingItem editedFood) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ShoppingItem> getFilteredShoppingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredShoppingList(Predicate<ShoppingItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getBoughtListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBoughtListFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBoughtItem(GroceryItem food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBoughtList(ReadOnlyGroceryList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGroceryList getBoughtList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBoughtItem(GroceryItem food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBoughtItem(GroceryItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBoughtItem(GroceryItem target, GroceryItem editedFood) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<GroceryItem> getFilteredBoughtItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBoughtItemList(Predicate<GroceryItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTemplateItem extends ModelStub {
        private final TemplateItem templateItem;

        ModelStubWithTemplateItem(TemplateItem templateItem) {
            requireNonNull(templateItem);
            this.templateItem = templateItem;
        }

        public boolean hasTemplateItem(TemplateItem templateItem) {
            requireNonNull(templateItem);
            return this.templateItem.isSameFood(templateItem);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTemplateItemAdded extends ModelStub {
        final ArrayList<TemplateItem> templateItemsAdded = new ArrayList<TemplateItem>();
        final ArrayList<UniqueTemplateItems> templatesAdded = new ArrayList<>();

        public ObservableList<UniqueTemplateItems> getFilteredTemplateList() {
            return (ObservableList<UniqueTemplateItems>) templatesAdded;
        }

        public boolean hasTemplateItem(TemplateItem templateItem) {
            requireNonNull(templateItem);
            return templateItemsAdded.stream().anyMatch(templateItem::isSameFood);
        }

        public void addTemplateItem(TemplateItem templateItem) {
            requireNonNull(templateItem);
            templateItemsAdded.add(templateItem);
        }

        public ReadOnlyTemplateList getTemplateList() {
            return new TemplateList();
        }
    }

}
