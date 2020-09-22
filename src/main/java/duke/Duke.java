package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.MESSAGE_DECODE_FAILURE_FORMAT;
import static duke.ui.Messages.MESSAGE_MISSING_FILEPATH;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Initializes the application and starts the interaction with the user
     *
     * @param args arguments supplied by the user at program launch
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Class constructor to set up the required objects and loads up the data from the storage file
     *
     * @param filePath the file path of the saved copy of the task list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException fnfe) {
            storage.createFile(ui);
            tasks = new TaskList();
        } catch (ArrayIndexOutOfBoundsException ioe) {
            ui.showFeedbackMessage(MESSAGE_DECODE_FAILURE_FORMAT + filePath, ioe.getMessage());
            exit();
        } catch (NullPointerException npe) {
            ui.showFeedbackMessage(MESSAGE_MISSING_FILEPATH, npe.getMessage());
        } catch (DateTimeParseException dpe) {
            ui.showFeedbackMessage(MESSAGE_DECODE_FAILURE_FORMAT + filePath, dpe.getMessage());
            exit();
        } catch (DukeException de) {
            ui.showFeedbackMessage(MESSAGE_DECODE_FAILURE_FORMAT + filePath, de.getMessage());
            exit();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Prints welcome message
     */
    private void start() {
        ui.showWelcomeMessage();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = new Parser().parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = ExitCommand.isExit(c);

            ui.showLine();
        }
    }
}