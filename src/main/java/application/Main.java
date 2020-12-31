package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        ToDoListApplication application = new ToDoListApplication(primaryStage);

        application.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
