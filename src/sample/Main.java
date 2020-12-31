package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        // Text
        Text topDescription = new Text("Hello World");
        topDescription.setFont(new Font("Verdana", 20));
        topDescription.setFill(Color.BLACK);

        // Labels & TextField controls
        Label Label1 = new Label("");
        Label Label2 = new Label("");
        TextField textField = new TextField();
        Label resultsLabel = new Label();
        HBox searchForNameHBox = new HBox(10);
        searchForNameHBox.setAlignment(Pos.CENTER);
        searchForNameHBox.setPadding(new Insets(10));

        VBox nameLabelAndControlsVBox = new VBox(10);
        nameLabelAndControlsVBox.setAlignment(Pos.CENTER);
        nameLabelAndControlsVBox.setPadding(new Insets(10));

        // Button & Event Handling
        Button myButton = new Button("My Button");
        myButton.setOnAction(e ->
        {


        });

        // Main container
        VBox mainContainer = new VBox(10, topDescription, myButton);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(10));

        // Scene
        Scene scene = new Scene(mainContainer, 500, 500);

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Template");
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
