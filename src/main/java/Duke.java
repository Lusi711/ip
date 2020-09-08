import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String indentLine = "--------------------------------------------------";
    public static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int numberOfTasks = 0;

    public static void enterGreet() {
        System.out.println(indentLine);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println(indentLine);
    }

    public static void farewellGreet() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(indentLine);
    }
    /*
    public static void Echo(String line) {
        System.out.println(line);
    }
    */

    public static void addList(String type, String input) {
        System.out.println("Got it. I've added this task:");
        String[] description = input.split("/");
        switch (type) {
        case "todo":
            try {
                tasks[numberOfTasks] = new ToDo(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The todo description cannot be empty. :(");
                return;
            }
            break;
        case "deadline":
            String by = description[1];
            tasks[numberOfTasks] = new Deadline(description[0], by.substring(by.indexOf(' ') + 1));
            break;
        case "event":
            String at = description[1];
            tasks[numberOfTasks] = new Event(description[0], at.substring(at.indexOf(' ') + 1));
            break;
        default:
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        System.out.println("  " + tasks[numberOfTasks]);
        numberOfTasks++;
        if (numberOfTasks > 1) {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        } else {
            System.out.println("Now you have " + numberOfTasks + " task in the list.");
        }
    }

    public static void markAsDone(String input) {
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please list a task number to be marked done: " + input);
            return;
        }

        try {
            tasks[taskIndex - 1].markAsDone();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Task number does not exist in the list: " + taskIndex);
            return;
        } catch (NullPointerException e) {
            System.out.println("OOPS!!! This is an invalid task number : " + taskIndex);
            return;
        }

        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + tasks[taskIndex - 1]);
    }

    public static void displayList(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        int taskIndex = 1;
        for (Task task : tasks) {
            System.out.println(taskIndex + ". " + task);
            taskIndex++;
        }
    }

    public static void executeCommand() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        //Process command-line input
        while (!line.equals("bye")) {
            String[] command = line.split(" ", 2);
            System.out.println(indentLine);
            switch (command[0]) {
            case "list":
                displayList(Arrays.copyOf(tasks, numberOfTasks));
                break;
            case "done":
                markAsDone(command[1]);
                break;
            default:
                addList(command[0], command[1]);
                break;
            }
            System.out.println(indentLine);
            line = in.nextLine();
        }
        System.out.println(indentLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greets the user upon entering
        enterGreet();
        //Executes command-line input of user
        executeCommand();
        //Displays exit message
        farewellGreet();
    }
}