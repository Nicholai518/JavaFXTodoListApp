package application;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        ToDoListApplication application = new ToDoListApplication(primaryStage);

        application.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
