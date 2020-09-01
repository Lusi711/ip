public class Duke {
    public static void Greet(String line) {
        for (int i = 0; i < 3; i++) {
            System.out.println(line);
            if (i == 0) {
                System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
            }
            else if (i == 1){
                System.out.println("Bye. Hope to see you again soon!\n");
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String indentation = "----------------------------------------";
        System.out.println("Hello from\n" + logo);
        Greet(indentation);
    }
}