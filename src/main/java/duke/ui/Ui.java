package duke.ui;

import duke.task.Task;

import static duke.ui.Messages.MESSAGE_GOODBYE;
import static duke.ui.Messages.MESSAGE_ENQUIRY;
import static duke.ui.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

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

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void showWelcomeMessage() {
        showToUser(DIVIDER, "Hello from", LOGO, MESSAGE_WELCOME, MESSAGE_ENQUIRY, DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(DIVIDER, MESSAGE_GOODBYE, DIVIDER);
    }

    public void showTaskList(String message, ArrayList<Task> tasks) {
        showToUser(message);

        for (Task task : tasks) {
            int taskIndex = tasks.indexOf(task) + 1;
            showToUser(taskIndex + ". " + task);
        }
    }

    public void showFeedbackMessage(String... feedbackToUser) {
        showToUser(feedbackToUser);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    private void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }
}
