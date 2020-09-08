import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String INDENTLINE = "----------------------------------------------------------";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int numberOfTasks = 0;

    private static void enterGreet() {
        System.out.println(INDENTLINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println(INDENTLINE);
    }

    private static void farewellGreet() {
        System.out.println(INDENTLINE);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(INDENTLINE);
    }
    /*
    public static void Echo(String line) {
        System.out.println(line);
    }
    */
    private static void addToDo(String input) {
        tasks[numberOfTasks] = new ToDo(input);
    }

    private static void addDeadline(String input) {
        String[] description = input.split("/");
        String by = description[1];
        tasks[numberOfTasks] = new Deadline(description[0],by.substring(by.indexOf(' ') + 1));
    }

    private static void addEvent(String input) {
        String[] description = input.split("/");
        String at = description[1];
        tasks[numberOfTasks] = new Event(description[0], at.substring(at.indexOf(' ') + 1));
    }

    private static void addList(CommandType commandType, String input) {
        switch(commandType) {
        case COMMAND_TODO:
            addToDo(input);
            break;
        case COMMAND_DEADLINE:
            try {
                addDeadline(input);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! There must be a description after /by.");
                return;
            }
            break;
        case COMMAND_EVENT:
            try {
                addEvent(input);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! There must be a description after /at.");
                return;
            }
            break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[numberOfTasks]);
        numberOfTasks++;
        if (numberOfTasks > 1) {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        } else {
            System.out.println("Now you have " + numberOfTasks + " task in the list.");
        }
    }

    private static void markAsDone(String input) {
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

    private static void displayList(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        int taskIndex = 1;
        for (Task task : tasks) {
            System.out.println(taskIndex + ". " + task);
            taskIndex++;
        }
    }

    private static void executeCommand() {
        Scanner in = new Scanner(System.in);

        //Process command-line input
        while (true) {
            String line = in.nextLine();
            String[] command = line.split(" ", 2);
            CommandType commandType = CommandType.COMMAND_UNKNOWN;
            System.out.println(INDENTLINE);
            try {
                commandType = readCommandType(command[0]);
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(INDENTLINE);
                continue;
            }
            switch (commandType) {
            case COMMAND_LIST:
                displayList(Arrays.copyOf(tasks, numberOfTasks));
                break;
            case COMMAND_DONE:
                markAsDone(command[1]);
                break;
            case COMMAND_BYE:
                return;
            default:
                try {
                    addList(commandType,command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a " + command[0] + " cannot be empty.");
                }
                break;
            }
            System.out.println(INDENTLINE);
        }
    }

    private static CommandType readCommandType(String command) throws DukeException {

        switch (command) {
        case ("bye"):
            return CommandType.COMMAND_BYE;
        case ("list"):
            return CommandType.COMMAND_LIST;
        case("todo"):
            return CommandType.COMMAND_TODO;
        case("deadline"):
            return CommandType.COMMAND_DEADLINE;
        case("event"):
            return CommandType.COMMAND_EVENT;
        case("done"):
            return CommandType.COMMAND_DONE;
        default:
            throw new DukeException();
            //return CommandType.COMMAND_UNKNOWN;
        }
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