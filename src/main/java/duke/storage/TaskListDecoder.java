package duke.storage;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Decodes the storage data file into an {@code ArrayList<Task>} object.
 */
public class TaskListDecoder {

    private static final String CHECKMARK = "\u2713";
    private static final String TODO_DESCRIPTOR = "[T]";
    private static final String DEADLINE_DESCRIPTOR = "[D]";
    private static final String EVENT_DESCRIPTOR = "[E]";

    /**
     * Decodes sentence strings in file into a {@code ArrayList<Task>} containing the decoded tasks
     *
     * @param s the Scanner object which parses strings from the file
     *
     * @return {@code ArrayList<Task>} data decoded from this file
     *
     * @throws DukeException if there were errors reading and/or converting data from file.
     */
    public static ArrayList<Task> decodeTaskList(Scanner s) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String[] parts = s.nextLine().split("] ", 2);
            if (parts[0].startsWith(TODO_DESCRIPTOR)) {
                tasks.add(decodeToDoFromString(parts[0].trim(), parts[1].trim()));
            } else if (parts[0].startsWith(DEADLINE_DESCRIPTOR)) {
                tasks.add(decodeDeadlineFromString(parts[0].trim(), parts[1].trim(), "\\| "));
            } else if (parts[0].startsWith(EVENT_DESCRIPTOR)) {
                tasks.add(decodeEventFromString(parts[0].trim(), parts[1].trim(), "\\| "));
            } else {
                throw new DukeException();
            }
        }

        return tasks;
    }

    /** Decodes a string into an {@code Event} if indicated
     *
     * @param isDone string that represents whether the task was done
     * @param description description of the event with the start time
     * @param regex delimiter between text description and start time
     *
     * @return {@code Event} object created from string
     */
    private static Task decodeEventFromString(String isDone, String description, String regex) {
        String[] parts = description.split(regex);
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
        Task event = new Event(parts[0].trim(), by.format(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm")));
        if (isDone.contains(CHECKMARK)) {
            event.markAsDone();
        }

        return event;
    }

    /**
     * Decodes a string into a {@code Deadline} if indicated
     *
     * @param isDone string that represents whether the task was done
     * @param description description of the task with the deadline
     * @param regex delimiter between text description and deadline
     *
     * @return {@code Deadline} object created from string
     */
    private static Task decodeDeadlineFromString(String isDone, String description, String regex) {
        String[] parts = description.split(regex);
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
        Task deadline = new Deadline(parts[0].trim(), by.format(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm")));
        if (isDone.contains(CHECKMARK)) {
            deadline.markAsDone();
        }

        return deadline;
    }

    /**
     * Decodes a string into a {@code ToDo} object if indicated
     *
     * @param isDone string that represents whether the task was done
     * @param description description of the task
     *
     * @return {@code ToDo} object created from string
     */
    private static Task decodeToDoFromString(String isDone, String description) {
        Task todo = new ToDo(description);
        if (isDone.contains(CHECKMARK)) {
            todo.markAsDone();
        }

        return todo;
    }
}
