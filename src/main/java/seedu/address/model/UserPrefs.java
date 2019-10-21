package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.IFridgeSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path templateListFilePath = Paths.get("data" , "templateList.json");
    private Path wasteArchiveFilePath = Paths.get("data", "wastearchive.json");
    private Path shoppingListFilePath = Paths.get("data", "shoppingList.json");
    private Path boughtListFilePath = Paths.get("data", "boughtList.json");
    private IFridgeSettings iFridgeSettings = new IFridgeSettings();


    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setIFridgeSettings(newUserPrefs.getIFridgeSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setWasteArchiveFilePath(newUserPrefs.getWasteArchiveFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public IFridgeSettings getIFridgeSettings() {
        return iFridgeSettings;
    }

    public void setIFridgeSettings(IFridgeSettings iFridgeSettings) {
        requireNonNull(iFridgeSettings);
        this.iFridgeSettings = iFridgeSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getTemplateListFilePath() {
        return templateListFilePath;
    }

    public void setTemplateListFilePath(Path templateListFilePath) {
        requireNonNull(templateListFilePath);
        this.templateListFilePath = templateListFilePath;
    }

    public Path getWasteArchiveFilePath() {
        return wasteArchiveFilePath;
    }

    public void setWasteArchiveFilePath(Path wasteArchiveFilePath) {
        requireNonNull(wasteArchiveFilePath);
        this.wasteArchiveFilePath = wasteArchiveFilePath;
    }

    public Path getShoppingListFilePath() {
        return shoppingListFilePath;
    }

    public void setShoppingListFilePath(Path shoppingListFilePath) {
        requireNonNull(shoppingListFilePath);
        this.shoppingListFilePath = shoppingListFilePath;
    }

    public Path getBoughtListFilePath() {
        return boughtListFilePath;
    }

    public void setBoughtListFilePath(Path boughtListFilePath) {
        requireNonNull(boughtListFilePath);
        this.boughtListFilePath = boughtListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && templateListFilePath.equals(o.templateListFilePath)
                && wasteArchiveFilePath.equals(o.wasteArchiveFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, templateListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nIFridge settings : " + iFridgeSettings);
        sb.append("\nLocal data file location (AddressBook): " + addressBookFilePath);
        sb.append("\nLocal data file location (TemplateList): " + templateListFilePath);
        sb.append("\nLocal data file location (WasteList): " + wasteArchiveFilePath);
        return sb.toString();
    }
}
