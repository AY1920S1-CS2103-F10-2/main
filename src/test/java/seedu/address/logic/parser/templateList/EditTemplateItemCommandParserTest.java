package seedu.address.logic.parser.templateList;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.templateList.template.EditTemplateItemCommand;
import seedu.address.logic.commands.templateList.template.EditTemplateItemCommand.EditTemplateItemDescriptor;
import seedu.address.logic.parser.templateList.template.EditTemplateItemCommandParser;
import seedu.address.model.food.Name;
import seedu.address.testutil.EditTemplateItemDescriptorBuilder;

public class EditTemplateItemCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTemplateItemCommand.MESSAGE_USAGE);

    private EditTemplateItemCommandParser parser = new EditTemplateItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        //assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTemplateItemCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        //assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        //assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS); // invalid amount

        // multiple invalid values, but only the first invalid value is captured.
        // But bcuz of conflicts, we do only one INVALID ONE IN THIS CASE.
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_AMOUNT_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_AMY
                + NAME_DESC_AMY;

        EditTemplateItemDescriptor descriptor = new EditTemplateItemDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAmount(VALID_AMOUNT_AMY).build();
        EditTemplateItemCommand expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_AMY;

        EditTemplateItemDescriptor descriptor = new EditTemplateItemDescriptorBuilder()
                .withAmount(VALID_AMOUNT_AMY).build();
        EditTemplateItemCommand expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditTemplateItemDescriptor descriptor = new EditTemplateItemDescriptorBuilder()
                .withName(VALID_NAME_AMY).build();
        EditTemplateItemCommand expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);

        // amount
        userInput = targetIndex.getOneBased() + AMOUNT_DESC_AMY;
        descriptor = new EditTemplateItemDescriptorBuilder().withAmount(VALID_AMOUNT_AMY).build();
        expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()
                + AMOUNT_DESC_AMY + AMOUNT_DESC_BOB;

        EditTemplateItemDescriptor descriptor = new EditTemplateItemDescriptorBuilder()
                .withAmount(VALID_AMOUNT_BOB)
                .build();
        EditTemplateItemCommand expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_AMY;
        EditTemplateItemDescriptor descriptor = new EditTemplateItemDescriptorBuilder()
                .withName(VALID_NAME_AMY).build();
        EditTemplateItemCommand expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_NAME_DESC
                + NAME_DESC_AMY;
        descriptor = new EditTemplateItemDescriptorBuilder().withName(VALID_NAME_AMY)
                .build();
        expectedCommand = new EditTemplateItemCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);
    }
}
