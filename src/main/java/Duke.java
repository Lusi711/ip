import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static String indentLine = "----------------------------------------";

    public static void enterGreet() {
        System.out.println(indentLine);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println(indentLine);
    }

    public static void farewellGreet() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(indentLine);
    }

    public static void Echo() {
        String line = null;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.print(indentLine + "\n" + line + "\n" + indentLine + "\n");
            line = in.nextLine();
        }
        System.out.println(indentLine);
    }

    public static void addList(Task[] items, String task, int numberOfTasks) {
        System.out.println(indentLine);

        items[numberOfTasks] = new Task(task);
        System.out.println("added: " + task);

        System.out.println(indentLine);
    }

    public static void markAsDone(Task[] items, String line) {
        System.out.println(indentLine);

        System.out.println("Nice! I've marked this task as done: ");
        int itemIndex = Integer.parseInt(line.substring(line.length() - 1));
        items[itemIndex - 1].markAsDone();
        Task t = items[itemIndex - 1];
        System.out.println("  " + t.getStatusIcon() + " " + t.description);

        System.out.println(indentLine);
    }

    public static void displayList(Task[] items) {
        System.out.println(indentLine);

        System.out.println("Here are the tasks in your list:");
        int itemIndex = 1;
        for (Task item : items) {
            System.out.println(itemIndex + ". " + item.getStatusIcon() + " " + item.description);
            itemIndex++;
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

        //Level-0
        enterGreet();

        //Level-3
        Scanner in = new Scanner(System.in);
        Task[] items = new Task[100];
        String line = in.nextLine();
        int numberOfTasks = 0;
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                displayList(Arrays.copyOf(items, numberOfTasks));
            } else if (line.contains("done")) {
                markAsDone(items, line);
            } else {
                addList(items, line, numberOfTasks);
                numberOfTasks++;
            }
            line = in.nextLine();
        }
        System.out.println(indentLine);

        farewellGreet();
    }
}