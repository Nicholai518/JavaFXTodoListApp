package application;

import java.io.IOException;

public interface ITodoListSavedData
{
    public void save(ToDoList todoList) throws IOException;
    public SaveData load() throws IOException;
}
