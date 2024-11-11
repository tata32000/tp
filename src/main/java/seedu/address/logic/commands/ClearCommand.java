package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.schedule.Meeting;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_PERSON_IN_MEETING =
            "Cannot clear address book as there is a person in a meeting.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Check if person in the meeting
        List<Meeting> meetings = model.getWeeklySchedule();
        for (Meeting meeting : meetings) {
            if (!meeting.getContactUids().isEmpty()) {
                // There is a person in the meeting
                throw new CommandException(MESSAGE_PERSON_IN_MEETING);
            }
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
