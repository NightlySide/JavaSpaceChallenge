package application;

import controlleurs.Controlleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Service;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Service service = new Service();
        Controlleur controlleur = new Controlleur(stage, service);
    }

    public static void main(String[] args) {
        launch();
    }
}
