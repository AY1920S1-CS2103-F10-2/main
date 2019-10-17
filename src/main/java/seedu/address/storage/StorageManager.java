package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGroceryList;
import seedu.address.model.ReadOnlyTemplateList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWasteList;
import seedu.address.model.UserPrefs;
import seedu.address.storage.wastelist.WasteListStorage;


/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GroceryListStorage groceryListStorage;
    private TemplateListStorage templateListStorage;
    private WasteListStorage wasteListStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(GroceryListStorage groceryListStorage, UserPrefsStorage userPrefsStorage,
                          TemplateListStorage templateListStorage, WasteListStorage wasteListStorage) {
        super();
        this.groceryListStorage = groceryListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.templateListStorage = templateListStorage;
        this.wasteListStorage = wasteListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getGroceryListFilePath() {
        return groceryListStorage.getGroceryListFilePath();
    }

    @Override
    public Optional<ReadOnlyGroceryList> readGroceryList() throws DataConversionException, IOException {
        return readGroceryList(groceryListStorage.getGroceryListFilePath());
    }

    @Override
    public Optional<ReadOnlyGroceryList> readGroceryList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return groceryListStorage.readGroceryList(filePath);
    }

    @Override
    public void saveGroceryList(ReadOnlyGroceryList addressBook) throws IOException {
        saveGroceryList(addressBook, groceryListStorage.getGroceryListFilePath());
    }

    @Override
    public void saveGroceryList(ReadOnlyGroceryList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        groceryListStorage.saveGroceryList(addressBook, filePath);
    }

    // ================ TemplateList methods ==============================

    @Override
    public Path getTemplateListFilePath() {
        return templateListStorage.getTemplateListFilePath();
    }

    @Override
    public Optional<ReadOnlyTemplateList> readTemplateList() throws DataConversionException, IOException {
        return readTemplateList(templateListStorage.getTemplateListFilePath());
    }

    @Override
    public Optional<ReadOnlyTemplateList> readTemplateList(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return templateListStorage.readTemplateList(filePath);
    }

    @Override
    public void saveTemplateList(ReadOnlyTemplateList templateList) throws IOException {
        saveTemplateList(templateList, templateListStorage.getTemplateListFilePath());
    }

    @Override
    public void saveTemplateList(ReadOnlyTemplateList templateList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        templateListStorage.saveTemplateList(templateList, filePath);
    }

    // ================ WasteList methods ==============================

    @Override
    public Path getWasteListFilePath() {
        return wasteListStorage.getWasteListFilePath();
    }

    @Override
    public Optional<ReadOnlyWasteList> readWasteList() throws DataConversionException, IOException {
        return readWasteList(wasteListStorage.getWasteListFilePath());
    }

    @Override
    public Optional<ReadOnlyWasteList> readWasteList(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return wasteListStorage.readWasteList(filePath);
    }

    @Override
    public void saveWasteList(ReadOnlyWasteList wasteList) throws IOException {
        saveWasteList(wasteList, wasteListStorage.getWasteListFilePath());
    }

    @Override
    public void saveWasteList(ReadOnlyWasteList wasteList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        wasteListStorage.saveWasteList(wasteList, filePath);
    }

}
