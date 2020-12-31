package sample;

import java.util.LinkedList;
import java.util.List;

public class ToDoList
{
    // Field
    private List<Task> tasks;

    // Constructor
    public ToDoList()
    {
        this.tasks = new LinkedList<Task>();
    }

    // Methods
    // Setters and getters
    public List<Task> getTasks()
    {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString()
    {
        return "ToDoList{" +
                "items=" + tasks +
                '}';
    }
}
