import jdk.jfr.Event;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String indentLine = "----------------------------------------";
    private static Task[] tasks = new Task[100];
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

    public static void Echo(String line) {
        System.out.print(indentLine + "\n" + line + "\n" + indentLine + "\n");
        System.out.println(indentLine);
    }

    public static void addList(String line) {
        System.out.println(indentLine);

        //input[0] = command by user, input[1] = task
        String[] input = line.split(" ", 2);
        if (input[0].equals("todo")) {
            System.out.println("Got it. I've added this task:");
            tasks[numberOfTasks] = new ToDo(input[1]);
            System.out.println("  " + tasks[numberOfTasks]);
        } else if (input[0].equals("deadline")) {
            System.out.println("Got it. I've added this task:");
            String[] description = input[1].split("/");
            tasks[numberOfTasks] = new Deadline(description[0],
                    description[1].substring(description[1].indexOf(' ')+1));
            System.out.println("  " + tasks[numberOfTasks]);
        } else if (input[0].equals("event")) {
            System.out.println("Got it. I've added this task:");
            String[] description = input[1].split("/");
            tasks[numberOfTasks] = new Events(description[0],
                    description[1].substring(description[1].indexOf(' ')+1));
            System.out.println("  " + tasks[numberOfTasks]);
        } else {
            System.out.println("added: " + input);
        }
        numberOfTasks++;
        if (numberOfTasks > 1) {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        } else {
            System.out.println("Now you have " + numberOfTasks + " task in the list.");
        }

        System.out.println(indentLine);
    }

    public static void markAsDone(String input) {
        System.out.println(indentLine);

        System.out.println("Nice! I've marked this task as done: ");
        int taskIndex = Integer.parseInt(input);
        tasks[taskIndex - 1].markAsDone();
        System.out.println("  "+tasks[taskIndex - 1]);

        System.out.println(indentLine);
    }

    public static void displayList(Task[] tasks) {
        System.out.println(indentLine);

        System.out.println("Here are the tasks in your list:");
        int taskIndex = 1;
        for (Task task : tasks) {
            System.out.println(taskIndex + ". " + task);
            taskIndex++;
        }

        System.out.println(indentLine);
    }

    public static void executeCommand() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        //Process command-line input
        while (!line.equals("bye")) {
            String[] command = line.split(" ", 2);
            switch (command[0]) {
            case "list":
                displayList(Arrays.copyOf(tasks, numberOfTasks));
                break;
            case "done":
                markAsDone(command[1]);
                break;
            case "todo":
                addList(line);
                break;
            case "deadline":
                addList(line);
                break;
            case "event":
                addList(line);
                break;
            //default:
            //addList(line);
            //break;
            }
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