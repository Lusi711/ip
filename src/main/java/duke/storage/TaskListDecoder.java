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

public class TaskListDecoder {

    private static final String CHECKMARK = "\u2713";
    private static final String TODO_DESCRIPTOR = "[T]";
    private static final String DEADLINE_DESCRIPTOR = "[D]";
    private static final String EVENT_DESCRIPTOR = "[E]";

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

    private static Task decodeEventFromString(String isDone, String description, String regex) {
        String[] parts = description.split(regex);
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
        Task event = new Event(parts[0].trim(), by.format(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm")));
        if (isDone.contains(CHECKMARK)) {
            event.markAsDone();
        }

        return event;
    }

    private static Task decodeDeadlineFromString(String isDone, String description, String regex) {
        String[] parts = description.split(regex);
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
        Task deadline = new Deadline(parts[0].trim(), by.format(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm")));

        if (isDone.contains(CHECKMARK)) {
            deadline.markAsDone();
        }

        return deadline;
    }

    private static Task decodeToDoFromString(String isDone, String description) {
        Task todo = new ToDo(description);
        if (isDone.contains(CHECKMARK)) {
            todo.markAsDone();
        }

        return todo;
    }
}
