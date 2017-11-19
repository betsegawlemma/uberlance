package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
