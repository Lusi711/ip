package duke.parser;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FindDateCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.addTask.AddDeadlineCommand;
import duke.command.addTask.AddEventCommand;
import duke.command.addTask.AddToDoCommand;

import static duke.ui.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.ui.Messages.MESSAGE_INVALID_TIMING_DESCRIPTION;
import static duke.ui.Messages.MESSAGE_MISSING_DESCRIPTION;
import static duke.ui.Messages.MESSAGE_MISSING_TIMING_DESCRIPTION;

import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parse(String userInput) {
        String[] command = userInput.trim().split(" ", 2);

        if (command[0].equals(ListCommand.COMMAND_WORD)) {
            return new ListCommand();
        } else if (command[0].equals(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        }

        try {
            switch (command[0]) {
            case AddToDoCommand.COMMAND_WORD:
                return new AddToDoCommand(command[1].trim());
            case AddDeadlineCommand.COMMAND_WORD:
                return prepareAddDeadlineCommand(command[1].trim());
            case AddEventCommand.COMMAND_WORD:
                return prepareAddEventCommand(command[1].trim());
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(command[1].trim());
            case DoneCommand.COMMAND_WORD:
                return prepareDone(command[1].trim());
            case FindCommand.COMMAND_WORD:
                return prepareFind(command[1].trim());
            case FindDateCommand.COMMAND_WORD:
                return prepareFindDate(command[1].trim());
            default:
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException abe) {
            return new IncorrectCommand(MESSAGE_MISSING_DESCRIPTION);
        }
    }

    /**
     * Parses arguments in the context of the add deadline command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddDeadlineCommand(String args) {
        String[] parts = args.split("/by");
        try {
            return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand(MESSAGE_MISSING_TIMING_DESCRIPTION + "/by.");
        } catch (DateTimeParseException dpe) {
            return new IncorrectCommand(MESSAGE_INVALID_TIMING_DESCRIPTION + parts[1].trim());
        }
    }

    /**
     * Parses arguments in the context of the add event command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddEventCommand(String args) {
        String[] parts = args.split("/at");
        try {
            return new AddEventCommand(parts[0].trim(), parts[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand(MESSAGE_MISSING_TIMING_DESCRIPTION + "/at.");
        } catch (DateTimeParseException dpe) {
            return new IncorrectCommand(MESSAGE_INVALID_TIMING_DESCRIPTION + parts[1].trim());
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            int targetIndex = Integer.parseInt(args);
            return new DeleteCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_MISSING_DESCRIPTION);
        }
    }

    /**
     * Parses arguments in the context of the done task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDone(String args) {
        try {
            int targetIndex = Integer.parseInt(args);
            return new DoneCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_MISSING_DESCRIPTION);
        }

    }

    /**
     * Parses arguments in the context of the find task command based on description.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        return new FindCommand(args.trim().toLowerCase());
    }

    /**
     * Parses arguments in the context of the find task command based on date.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFindDate(String args) {
        return new FindDateCommand(args.trim());
    }
}
