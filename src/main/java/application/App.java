package application;

import controlleurs.Controlleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Démarrage du controlleur de l'application
        Controlleur controlleur = new Controlleur(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
