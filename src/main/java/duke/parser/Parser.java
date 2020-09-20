package duke.parser;

import duke.command.*;
import duke.command.addTask.AddDeadlineCommand;
import duke.command.addTask.AddEventCommand;
import duke.command.addTask.AddToDoCommand;

import static duke.ui.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.ui.Messages.MESSAGE_MISSING_COMMAND_FORMAT;

public class Parser {
    public Command parse(String userInput) {
        String[] command = userInput.trim().split(" ", 2);
        if (command.length == 0) {
            return new IncorrectCommand(MESSAGE_MISSING_COMMAND_FORMAT);
        }

        switch (command[0]) {
        case AddToDoCommand.COMMAND_WORD:
            return new AddToDoCommand(command[1].trim());
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(command[1].trim());
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEventCommand(command[1].trim());
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            return prepareDoneCommand(command[1].trim());
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(command[1].trim());
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    public Command prepareAddDeadlineCommand(String args) {
        String[] parts = args.split("/by");
        return new AddDeadlineCommand(parts[0].trim(),parts[1].trim());
    }

    public Command prepareAddEventCommand(String args) {
        String[] parts = args.split("/at");
        return new AddEventCommand(parts[0].trim(),parts[1].trim());
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
