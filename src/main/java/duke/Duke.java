package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.MESSAGE_DECODE_FAILURE_FORMAT;
import static duke.ui.Messages.MESSAGE_MISSING_FILEPATH;

import java.io.FileNotFoundException;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

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
        } catch (DukeException de) {
            ui.showFeedbackMessage(MESSAGE_DECODE_FAILURE_FORMAT + filePath, de.getMessage());
            exit();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        ui.showWelcomeMessage();
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = new Parser().parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();

            ui.showLine();
        }
    }
}