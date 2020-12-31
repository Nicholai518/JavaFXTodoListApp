package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class FileSystemToDoListSavedData implements ITodoListSavedData
{
    public void save(ToDoList todoList) throws IOException
    {
        String appDataPath = System.getenv("APPDATA");
        String filename = "todos.csv";
        String directoryPath = appDataPath + "\\nicholai518\\JavaFXTodoListApp";
        String filePath = directoryPath + "\\" + filename;

        Files.createDirectories(Paths.get(directoryPath));

        // Creating file ?
        File toDoFile = new File(filePath);

        // Used to append to a file if one already exists with this name
        FileWriter fileWriter = null;

        // File Writer and PrintWriter
        fileWriter = new FileWriter(toDoFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        // Writing to file
        Iterator<Task> itr = todoList.getTasks().iterator();

        int counter = 1;
        while (itr.hasNext())
        {
            Task task = itr.next();
            // id,text,date,LOI,timeSensitive
            String listStringAccumulator = counter +
                    "," +
                    task.getTaskDescription() +
                    "," +
                    task.getCreatedAt() +
                    "," +
                    task.getLevelOfImportance() +
                    "," +
                    task.getTimeSensitive();
            printWriter.println(listStringAccumulator);
            counter++;
        }

        // Close
        printWriter.close();
        fileWriter.close();

        System.out.println("File saved to disk: " + filePath);
    }

    public ToDoList load()
    {
        return new ToDoList();
    }
}
