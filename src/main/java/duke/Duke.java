package duke;

import duke.command.CommandType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    private static final String INDENTLINE = "----------------------------------------------------------";
    private static final int MAX_TASKS = 100;
    protected static Task[] tasks = new Task[MAX_TASKS];
    private static int numberOfTasks = 0;
    private static boolean isChanged = false;

    //Prints greeting message when opening the application
    private static void enterGreet() {
        System.out.println(INDENTLINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println(INDENTLINE);
    }

    //Prints farewell message after command = 'bye'
    private static void farewellGreet() {
        System.out.println(INDENTLINE);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(INDENTLINE);
    }

    protected static void addToDo(String input) {
        tasks[numberOfTasks] = new ToDo(input.trim());
    }

    protected static void addDeadline(String input, String delimiter) {
        String[] description = input.split(delimiter);
        tasks[numberOfTasks] = new Deadline(description[0].trim(), description[1]);
    }

    protected static void addEvent(String input, String delimiter) {
        String[] description = input.split(delimiter);
        tasks[numberOfTasks] = new Event(description[0].trim(), description[1]);
    }

    //Adds Task instance to tasks
    private static void addList(CommandType commandType, String input) {
        String regex;
        switch(commandType) {
        case COMMAND_TODO:
            addToDo(input);
            break;
        case COMMAND_DEADLINE:
            regex = " /by ";
            try {
                addDeadline(input, regex);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! There must be a description after /by.");
                return;
            }
            break;
        case COMMAND_EVENT:
            regex = " /at ";
            try {
                addEvent(input, regex);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! There must be a description after /at.");
                return;
            }
            break;
        }
        isChanged = true;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[numberOfTasks]);
        numberOfTasks++;
        if (numberOfTasks > 1) {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        } else {
            System.out.println("Now you have " + numberOfTasks + " task in the list.");
        }
    }

    //Changes isDone of specified Task as True
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

    //Prints the tasks array
    private static void displayList(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        int taskIndex = 1;
        for (Task task : tasks) {
            System.out.println(taskIndex + ". " + task);
            taskIndex++;
        }
    }

    //Processes command-line input
    private static void executeCommand() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            String[] command = line.split(" ", 2);
            //Identify the specific command by user
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
                isChanged = true;
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
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        FileProcessing file = new FileProcessing();

        System.out.println("Hello from\n" + logo);

        //Greets the user upon entering
        enterGreet();
        //Reads file content to load tasks
        try {
            numberOfTasks = file.readFileContents(tasks);
        } catch (FileNotFoundException e) {
            file.createFile();
        }
        //Executes command-line input of user
        executeCommand();
        //Save tasks to text file if there are any changes made
        if (isChanged) {
            try {
                file.writeToFile(Arrays.copyOf(tasks, numberOfTasks));
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        //Displays exit message
        farewellGreet();
    }
}