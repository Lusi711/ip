package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException fnfe) {
            storage.createFile(ui);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        ui.showWelcomeMessage();
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = new Parser().parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();

            ui.showLine();
        }
    }
/*
    protected static void addToDo(String input) {
        tasks.add(new ToDo(input));
    }

    protected static void addDeadline(String input, String delimiter) {
        String[] description = input.split(delimiter);
        tasks.add(new Deadline(description[0].trim(), description[1].trim()));
    }

    protected static void addEvent(String input, String delimiter) {
        String[] description = input.split(delimiter);
        tasks.add(new Event(description[0].trim(), description[1].trim()));
    }

    //Adds Task instance to tasks
    private static void addList(CommandType commandType, String input) {
        String regex;
        switch (commandType) {
        case COMMAND_TODO:
            addToDo(input);
            break;
        case COMMAND_DEADLINE:
            try {
                addDeadline(input, regex = " /by");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! There must be a description after /by.");
                return;
            }
            break;
        case COMMAND_EVENT:
            try {
                addEvent(input, regex = " /at ");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! There must be a description after /at.");
                return;
            }
            break;
        }
        isChanged = true;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        if (tasks.size() > 1) {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
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
            tasks.get(taskIndex - 1).markAsDone();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! There are only " + tasks.size() + " tasks on the list.");
            return;
        }
        isChanged = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex - 1));
    }

    private static void removeTask(String input) {
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please list a task number to be deleted: " + input);
            return;
        }

        try {
            Task deletedTask = tasks.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! There are only " + tasks.size() + " tasks on the list");
            return;
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskIndex - 1));
        tasks.remove(tasks.get(taskIndex - 1));
        isChanged = true;
        if (tasks.size() > 1) {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        }
    }

    //Prints the tasks array
    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + ". " + task);
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
                displayList();
                break;
            case COMMAND_DONE:
                try {
                    markAsDone(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Please provide a valid index number for: " + command[0]);
                }
                break;
            case COMMAND_DELETE:
                try {
                    removeTask(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Please provide a valid index number for: " + command[0]);
                }
                break;
            case COMMAND_BYE:
                return;
            default:
                try {
                    addList(commandType, command[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a " + command[0] + " cannot be empty.");
                    break;
                }
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
        case ("todo"):
            return CommandType.COMMAND_TODO;
        case ("deadline"):
            return CommandType.COMMAND_DEADLINE;
        case ("event"):
            return CommandType.COMMAND_EVENT;
        case ("done"):
            return CommandType.COMMAND_DONE;
        case ("delete"):
            return CommandType.COMMAND_DELETE;
        default:
            throw new DukeException();
        }
    }

    public static void main(String[] args) {
        Storage file = new Storage();

        System.out.println("Hello from\n" + logo);

        //Greets the user upon entering
        enterGreet();
        //Reads file content to load tasks
        try {
            file.readFileContents(tasks);
        } catch (FileNotFoundException e) {
            file.createFile();
        }
        //Executes command-line input of user
        executeCommand();
        //Save tasks to text file if there are any changes made
        if (isChanged) {
            try {
                file.writeToFile(tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        //Displays exit message
        farewellGreet();
    }
 */
}