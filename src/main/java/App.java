//package main.java;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    public void start(Stage stage) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SoundVerse");
        stage.setResizable(false);
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
