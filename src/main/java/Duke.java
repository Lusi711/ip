import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static String indentLine = "----------------------------------------";

    public static void enterGreet() {
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
    }

    public static void addList(String[] items, String task, int numberOfTasks) {
        System.out.print(indentLine + "\n");

        items[numberOfTasks] = task;
        System.out.println("added: " + task);

        System.out.println(indentLine);
    }

    public static void displayList(String[] items) {
        int itemIndex = 1;
        for (String item : items) {
            System.out.println(itemIndex + ". " + item + "\n");
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
        System.out.println(indentLine);

        //Level-0
        enterGreet();

        //Level-2
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
        String line = in.nextLine();
        int numberOfTasks = 0;
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                displayList(Arrays.copyOf(items, numberOfTasks));
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