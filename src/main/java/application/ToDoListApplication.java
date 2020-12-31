package main.java.application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ToDoListApplication
{
    private Stage stage;
    private ToDoList userToDoList = new ToDoList();
    private TextField enterTaskTextField = new TextField();
    private RadioButton timeSensitiveButton = new RadioButton("Time Sensitive");
    private RadioButton notTimeSensitiveButton = new RadioButton("Not Time Sensitive");
    private RadioButton lowButton = new RadioButton("Low");
    private RadioButton mediumButton = new RadioButton("Medium");
    private RadioButton highButton = new RadioButton("High");
    // private Button printButton = new Button("Print");
    private Text toDoListDisplayText = new Text();

    public ToDoListApplication(Stage _stage)
    {
        stage = _stage;
    }

    public void start()
    {
        // To-Do Top Description
        Text toDoText = new Text("To-Do List");  // No longer needed because background image states to-do list in similar spot
        toDoText.setFont(new Font("Verdana", 20));
        toDoText.setFill(Color.BLACK);

        // Labels & TextField controls
        Label enterTaskLabel = new Label("Enter a Task: ");

        // Enter Task controls
        HBox enterTaskHBox = new HBox(10, enterTaskLabel, enterTaskTextField);
        enterTaskHBox.setAlignment(Pos.CENTER);
        enterTaskHBox.setPadding(new Insets(10));

        // ts Label
        Label timeSensitiveLabel = new Label("Time Sensitive");

        // ts controls
        notTimeSensitiveButton.setSelected(true);      // Not Time Sensitive by default

        ToggleGroup timeSensitiveToggleGroup = new ToggleGroup();
        timeSensitiveButton.setToggleGroup(timeSensitiveToggleGroup);
        notTimeSensitiveButton.setToggleGroup(timeSensitiveToggleGroup);

        HBox timeSensitiveControlsHBox = new HBox(10, timeSensitiveButton, notTimeSensitiveButton);
        timeSensitiveControlsHBox.setAlignment(Pos.CENTER);
        timeSensitiveControlsHBox.setPadding(new Insets(10));

        // ts main view
        VBox timeSensitiveMainVBox = new VBox(5, timeSensitiveLabel, timeSensitiveControlsHBox);
        timeSensitiveMainVBox.setAlignment(Pos.CENTER);
        timeSensitiveMainVBox.setPadding(new Insets(10));

        // LOI Label
        Label levelOfImportanceLabel = new Label("Level of Importance");

        // LOI controls
        lowButton.setSelected(true);  // Low is default

        ToggleGroup levelOfImportanceToggleGroup = new ToggleGroup();
        lowButton.setToggleGroup(levelOfImportanceToggleGroup);
        mediumButton.setToggleGroup(levelOfImportanceToggleGroup);
        highButton.setToggleGroup(levelOfImportanceToggleGroup);

        // LOI controls
        HBox levelOfImportanceControlsHBox = new HBox(10, lowButton, mediumButton, highButton);
        levelOfImportanceControlsHBox.setAlignment(Pos.CENTER);
        levelOfImportanceControlsHBox.setPadding(new Insets(10));

        // LOI main view
        VBox levelOfImportanceMainVBox = new VBox(5, levelOfImportanceLabel, levelOfImportanceControlsHBox);
        levelOfImportanceMainVBox.setAlignment(Pos.CENTER);
        levelOfImportanceMainVBox.setPadding(new Insets(10));


        // Print button
        Button printButton = new Button("Print");
        printButton.setOnAction(e ->
        {
            try
            {
                this.OnPrintButtonClick();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> this.OnSubmitButtonClick());

        // Button Container
        HBox buttonHBox = new HBox(10, submitButton, printButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPadding(new Insets(10));

        // Main container
        VBox mainContainer = new VBox(10, enterTaskHBox, timeSensitiveMainVBox, levelOfImportanceMainVBox, buttonHBox, toDoListDisplayText);

        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(10));

        // Scene
        Scene scene = new Scene(mainContainer, 600, 500);

        // Stage
        stage.setScene(scene);
        stage.setTitle("Enter your Tasks for the day!");
        stage.show();
    }

    private void OnSubmitButtonClick()
    {
        // Store user input
        String userTaskDisc = enterTaskTextField.getText();

        // time sensitive control
        boolean isTimeSensitive;
        if (timeSensitiveButton.isSelected())
        {
            isTimeSensitive = true;
        }
        else
        {
            isTimeSensitive = false;
        }

        // level of importance controls
        Task.TaskImportance loi = Task.TaskImportance.LOW;
        if (mediumButton.isSelected())
        {
            loi = Task.TaskImportance.MEDIUM;
        }
        else if (highButton.isSelected())
        {
            loi = Task.TaskImportance.HIGH;
        }

        // Create Task
        Task newTask = new Task(userTaskDisc, isTimeSensitive, loi);

        userToDoList.addTask(newTask);

        // List for Tasks
        List<Task> theTaskList = userToDoList.getTasks();

        // Sort, reverse, display
        Collections.sort(theTaskList);
        Collections.reverse(theTaskList);

        Iterator<Task> itr = theTaskList.iterator();

        int counter = 1;
        String listSringAccumulator = "";

        while (itr.hasNext())
        {
            listSringAccumulator += counter + ": " + itr.next() + "\n";
            counter++;
        }

        // Display List values
        toDoListDisplayText.setText(listSringAccumulator);

        // Reset accumulator and controls
        enterTaskTextField.setText("");
        notTimeSensitiveButton.setSelected(true);
        lowButton.setSelected(true);
    }

    private void OnPrintButtonClick() throws IOException
    {
        String appDataPath = System.getenv("APPDATA");
        String filename = "todolist.txt";
        String filePath = appDataPath + "\\nicholai518\\JavaFXTodoListApp";

        Files.createDirectories(Paths.get(filePath));

        // Creating file ?
        File toDoFile = new File(filePath + "\\" + filename);

        // Used to append to a file if one already exists with this name
        FileWriter fileWriter = null;

        try
        {
            // File Writer and PrintWriter
            fileWriter = new FileWriter(toDoFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Writing to file
            Iterator<Task> itr = userToDoList.getTasks().iterator();

            int counter = 1;
            String listStringAccumulator = "";

            while (itr.hasNext())
            {
                listStringAccumulator += counter + ": " + itr.next() + "\n";
                counter++;
            }


            LocalDate localDate = LocalDate.now();
            printWriter.println("----------------------------");
            printWriter.println("to-do-list: " + localDate);
            printWriter.println(listStringAccumulator);
            printWriter.println("");
            printWriter.println("");

            // Close
            printWriter.close();
            fileWriter.close();

            // Update Text display
            toDoListDisplayText.setText("File Has been saved to " + filePath + " drive. Good Luck!");

        }
        // Throws IOException
        catch (IOException ex)
        {
            ex.printStackTrace();
        }


    }
}
