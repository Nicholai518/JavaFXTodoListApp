package application;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemToDoListSavedData implements ITodoListSavedData
{
    private final String filename = "savedata.json";
    private final String directoryPath = "\\nicholai518\\JavaFXTodoListApp";

    public void save(ToDoList todoList) throws IOException
    {
        String filePath = getSaveFilePath();

        Files.createDirectories(Paths.get(getSaveDirectoryPath()));

        SaveData saveData = new SaveData(todoList.getTasks());

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(Paths.get(filePath).toFile(), saveData);

        System.out.println("File saved to disk: " + filePath);
    }

    public SaveData load() throws IOException
    {
        String filePath = getSaveFilePath();

        Files.createDirectories(Paths.get(getSaveDirectoryPath()));

        ObjectMapper mapper = new ObjectMapper();

        SaveData data = new SaveData();
        if (Files.exists(Paths.get(filePath)))
        {
            FileReader fileReader= new FileReader(filePath);
            data = mapper.readValue(fileReader, SaveData.class);
        }
        return data;
    }

    private String getSaveDirectoryPath()
    {
        String appDataPath = System.getenv("APPDATA");
        return appDataPath + directoryPath;
    }

    private String getSaveFilePath()
    {
        return getSaveDirectoryPath() + "\\" + filename;
    }
}
