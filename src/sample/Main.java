package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // to-do list
        ToDoList userToDoList = new ToDoList();

        // To-Do Top Description
        Text toDoText = new Text("To-Do List");  // No longer needed because background image states to-do list in similar spot
        toDoText.setFont(new Font("Verdana", 20));
        toDoText.setFill(Color.BLACK);

        // Labels & TextField controls
        Label enterTaskLabel = new Label("Enter a Task: ");
        TextField enterTaskTextField = new TextField();

        // Enter Task controls
        HBox enterTaskHBox = new HBox(10, enterTaskLabel, enterTaskTextField);
        enterTaskHBox.setAlignment(Pos.CENTER);
        enterTaskHBox.setPadding(new Insets(10));

        // ts Label
        Label timeSensitiveLabel = new Label("Time Sensitive");

        // ts controls
        RadioButton timeSensitiveButton = new RadioButton("Time Sensitive");
        RadioButton notTimeSensitiveButton = new RadioButton("Not Time Sensitive");
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
        RadioButton lowButton = new RadioButton("Low");
        lowButton.setSelected(true);  // Low is default
        RadioButton mediumButton = new RadioButton("Medium");
        RadioButton highButton = new RadioButton("High");

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

        // Text used for To-Do display
        Text toDoDisplayText = new Text();

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e ->
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
            toDoDisplayText.setText(listSringAccumulator);

            // Reset accumulator and controls
            counter = 1;
            enterTaskTextField.setText("");
            notTimeSensitiveButton.setSelected(true);
            lowButton.setSelected(true);
        });

        // Button & Event Handling
        Button printButton = new Button("Print");
        printButton.setOnAction(e ->
        {
            // Creating file ?
            File toDoFile = new File("C:\\toDoList.txt");

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
                toDoDisplayText.setText("File Has been saved to C drive. Good Luck!");

            }
            // Throws IOException
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        });

        // Button Container
        HBox buttonHBox = new HBox(10, submitButton, printButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPadding(new Insets(10));

        // Background picture
        Image backgroundImageForApplication = new Image("https://www.designeatrepeat.com/wp-content/uploads/printable-to-do-list-680x803.png");

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(backgroundImageForApplication,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);


        // Main container
        VBox mainContainer = new VBox(10, enterTaskHBox, timeSensitiveMainVBox, levelOfImportanceMainVBox, buttonHBox, toDoDisplayText);
        // TODO: add background image (opacity is too bright)
        //        mainContainer.setBackground(background);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(10));

        // Scene
        Scene scene = new Scene(mainContainer, 500, 500);

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enter your Tasks for the day!");
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
