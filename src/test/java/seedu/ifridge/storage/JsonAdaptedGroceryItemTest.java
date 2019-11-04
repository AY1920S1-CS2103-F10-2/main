package seedu.ifridge.storage;

import static seedu.ifridge.testutil.TypicalGroceryItems.SPAGHETTI;

import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedGroceryItemTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = SPAGHETTI.getName().toString();
    private static final String VALID_AMOUNT = SPAGHETTI.getAmount().toString();
    private static final String VALID_EXPIRY_DATE = SPAGHETTI.getExpiryDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = SPAGHETTI.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    /*@Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedFood person = new JsonAdaptedFood(BENSON);
        assertEquals(BENSON, person.toModelType());
    }*/

    /*@Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGroceryItem person =
                new JsonAdaptedGroceryItem(INVALID_NAME, VALID_AMOUNT, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    /*@Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGroceryItem person = new JsonAdaptedGroceryItem(null, VALID_AMOUNT, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    /*@Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGroceryItem person =
                new JsonAdaptedGroceryItem(VALID_NAME, VALID_AMOUNT, VALID_EXPIRY_DATE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }*/

}
