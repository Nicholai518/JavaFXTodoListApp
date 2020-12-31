package application;

import java.util.Date;

public class Task implements Comparable<Task>
{
    public enum TaskImportance
    {
        LOW,
        MEDIUM,
        HIGH
    }

    // Fields
    private String taskDescription;
    private boolean timeSensitive;
    private TaskImportance levelOfImportance; // High, Medium, Low

    private Date createdAt;
    private int priorityLevel;

    public Task(String taskDescription, boolean timeSensitive, TaskImportance levelOfImportance)
    {
        this.taskDescription = taskDescription;
        this.timeSensitive = timeSensitive;
        this.levelOfImportance = levelOfImportance;
        this.createdAt = new Date();
        setPriorityLevel();
    }

    // Methods

    public boolean isTimeSensitive()
    {
        return timeSensitive;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getTaskDescription()
    {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription)
    {
        this.taskDescription = taskDescription;
    }

    public boolean getTimeSensitive()
    {
        return timeSensitive;
    }

    public void setTimeSensitive(boolean timeSensitive)
    {
        this.timeSensitive = timeSensitive;
    }

    public TaskImportance getLevelOfImportance()
    {
        return levelOfImportance;
    }

    public void setLevelOfImportance(TaskImportance levelOfImportance)
    {
        this.levelOfImportance = levelOfImportance;
    }

    // setPriorityLevel
    private void setPriorityLevel()
    {
        // Low to High
        // ts false && LOI low          1
        if (timeSensitive == false && levelOfImportance == TaskImportance.LOW)
        {
            priorityLevel = 1;
        }
        // ts false && LOI medium       2
        else if (timeSensitive == false && levelOfImportance == TaskImportance.MEDIUM)
        {
            priorityLevel = 2;
        }
        // ts false && LOI high         3
        else if (timeSensitive == false && levelOfImportance == TaskImportance.HIGH)
        {
            priorityLevel = 3;
        }
        // ts true && LOI low           4
        else if (timeSensitive == true && levelOfImportance == TaskImportance.LOW)
        {
            priorityLevel = 4;
        }
        // ts true && LOI medium        5
        else if (timeSensitive == true && levelOfImportance == TaskImportance.MEDIUM)
        {
            priorityLevel = 5;
        }
        // ts true && LOI high          6
        else if (timeSensitive == true && levelOfImportance == TaskImportance.HIGH)
        {
            priorityLevel = 6;
        }
    }

    // compareTo method
    public int compareTo(Task t)
    {
        return Integer.compare(priorityLevel, t.priorityLevel);
    }

    // toString
    public String toString()
    {
        return taskDescription;
    }

    public int getPriorityLevel()
    {
        return priorityLevel;
    }
}
