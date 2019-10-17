package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGroceryList;
import seedu.address.model.ReadOnlyTemplateList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWasteList;
import seedu.address.model.UserPrefs;
import seedu.address.storage.wastelist.WasteListStorage;

/**
 * API of the Storage component
 */
public interface Storage extends GroceryListStorage, UserPrefsStorage, TemplateListStorage, WasteListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGroceryListFilePath();

    @Override
    Optional<ReadOnlyGroceryList> readGroceryList() throws DataConversionException, IOException;

    @Override
    void saveGroceryList(ReadOnlyGroceryList addressBook) throws IOException;

    @Override
    Path getTemplateListFilePath();

    @Override
    Optional<ReadOnlyTemplateList> readTemplateList() throws DataConversionException, IOException;

    @Override
    void saveTemplateList(ReadOnlyTemplateList templateList) throws IOException;

    @Override
    Path getWasteListFilePath();

    @Override
    Optional<ReadOnlyWasteList> readWasteList() throws DataConversionException, IOException;

    @Override
    void saveWasteList(ReadOnlyWasteList wasteList) throws IOException;

}
