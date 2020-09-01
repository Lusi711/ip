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
        System.out.println(indentLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String indentation = "----------------------------------------";
        System.out.println("Hello from\n" + logo);
        System.out.println(indentLine);

        //Level-0
        enterGreet();
        farewellGreet();
        enterGreet();

        //Level-1
        Echo();

        farewellGreet();
    }
}