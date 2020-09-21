package duke.parser;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.addTask.AddDeadlineCommand;
import duke.command.addTask.AddEventCommand;
import duke.command.addTask.AddToDoCommand;

import java.time.format.DateTimeParseException;

import static duke.ui.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.ui.Messages.MESSAGE_INVALID_TIMING_DESCRIPTION;
import static duke.ui.Messages.MESSAGE_MISSING_DEADLINE_DESCRIPTION;
import static duke.ui.Messages.MESSAGE_MISSING_EVENT_DESCRIPTION;
import static duke.ui.Messages.MESSAGE_MISSING_INDEX;
import static duke.ui.Messages.MESSAGE_MISSING_TIMING_DESCRIPTION;
import static duke.ui.Messages.MESSAGE_MISSING_TODO_DESCRIPTION;

public class Parser {
    public Command parse(String userInput) {
        String[] command = userInput.trim().split(" ", 2);

        switch (command[0]) {
        case AddToDoCommand.COMMAND_WORD:
            try {
                return new AddToDoCommand(command[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand(MESSAGE_MISSING_TODO_DESCRIPTION);
            }
        case AddDeadlineCommand.COMMAND_WORD:
            try {
                return prepareAddDeadlineCommand(command[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand(MESSAGE_MISSING_DEADLINE_DESCRIPTION);
            }
        case AddEventCommand.COMMAND_WORD:
            try {
                return prepareAddEventCommand(command[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand(MESSAGE_MISSING_EVENT_DESCRIPTION);
            }
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            try {
                return prepareDoneCommand(command[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand(MESSAGE_MISSING_INDEX);
            }
        case DeleteCommand.COMMAND_WORD:
            try {
                return prepareDeleteCommand(command[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand(MESSAGE_MISSING_INDEX);
            }
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    public Command prepareAddDeadlineCommand(String args) {
        String[] parts = args.split("/by");
        try {
            return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand(MESSAGE_MISSING_TIMING_DESCRIPTION + "/by.");
        } catch (DateTimeParseException dpe) {
            return new IncorrectCommand(MESSAGE_INVALID_TIMING_DESCRIPTION + parts[1].trim());
        }
    }

    public Command prepareAddEventCommand(String args) {
        String[] parts = args.split("/at");
        try {
            return new AddEventCommand(parts[0].trim(), parts[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand(MESSAGE_MISSING_TIMING_DESCRIPTION + "/at.");
        } catch (DateTimeParseException dpe) {
            return new IncorrectCommand(MESSAGE_INVALID_TIMING_DESCRIPTION + parts[1].trim());
        }
    }

    public Command prepareDeleteCommand(String args) {
        int targetIndex = Integer.parseInt(args);
        return new DeleteCommand(targetIndex);
    }

    public Command prepareDoneCommand(String args) {
        int targetIndex = Integer.parseInt(args);
        return new DoneCommand(targetIndex);
    }
}
