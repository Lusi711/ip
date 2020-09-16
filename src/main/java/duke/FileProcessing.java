package duke;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessing extends Duke {
    String filePath = "data/duke.txt";

    public FileProcessing() {

    }

    public void createFile() {
        File folder = new File(filePath.substring(0, 4));
        folder.mkdirs();
        File f = new File(filePath);
        try {
            folder.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

    public int readFileContents(Task[] tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        int index = 0;
        while (s.hasNext()) {
            String[] sentence = s.nextLine().split("] ",2);
            if (sentence[0].startsWith("[T]")) {
                Duke.addToDo(sentence[1]);
            } else if (sentence[0].startsWith("[D]")) {
                Duke.addDeadline(sentence[1], "\\| ");
            } else if (sentence[0].startsWith("[E]")) {
                Duke.addEvent(sentence[1],"| ");
            }
            //Mark task as done if indicated in the text
            if (sentence[0].contains("\u2713")) {
                tasks[index].markAsDone();
            }
            //index++;
        }
        s.close();
        return index = 1;
    }

    public void writeToFile(Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toFile()+"\n");
        }
        fw.close();
    }
}
