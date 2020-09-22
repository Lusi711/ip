package duke.ui;

import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.ui.Messages.*;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------------";

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Generates and prints the welcome message upon the start of the application
     */
    public void showWelcomeMessage() {
        showToUser(DIVIDER, MESSAGE_WELCOME, LOGO, MESSAGE_ENQUIRY, DIVIDER);
    }

    /**
     * Generates and prints the goodbye message upon termination of the application
     */
    public void showGoodbyeMessage() {
        showToUser(DIVIDER, MESSAGE_GOODBYE, DIVIDER);
    }

    /**
     * Generates and prints all {@code Task} in the given {@code ArrayList<Task>}
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showToUser(MESSAGE_DISPLAY_LIST);

        for (Task task : tasks) {
            int taskIndex = tasks.indexOf(task) + 1;
            showToUser(taskIndex + ". " + task);
        }
    }

    /**
     * Prints feedback message to the user
     */
    public void showFeedbackMessage(String... feedbackToUser) {
        showToUser(feedbackToUser);
    }

    /**
     * Prints divider to distinguish input and different outputs
     */
    public void showLine() {
        showToUser(DIVIDER);
    }

    /**
     * Shows message(s) to the user
     */
    private void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }
}
