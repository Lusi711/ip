package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
        Command command;
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