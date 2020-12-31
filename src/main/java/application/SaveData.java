package application;

import java.util.List;

public class SaveData
{
    private List<Task> todos;

    public SaveData() {}

    public SaveData(List<Task> todos)
    {
        this.todos = todos;
    }

    public List<Task> getTodos()
    {
        return todos;
    }

    public void setTodos(List<Task> todos)
    {
        this.todos = todos;
    }
}
