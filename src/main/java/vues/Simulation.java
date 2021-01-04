package vues;

import controlleurs.Controlleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class Simulation {
    @FXML
    public VBox monPane;

    private Stage stage;
    private Controlleur controlleur;

    public static Simulation creerInstance(Controlleur controlleur, Stage stage) {
        URL location = Simulation.class.getResource("/vues/Simulation.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Simulation vue = fxmlLoader.getController();
        vue.setStage(stage);
        vue.setControlleur(controlleur);

        return vue;
    }

    public void show() {
        stage.setTitle("JSC");
        stage.setScene(new Scene(monPane, 800,600));
        stage.show();
    }

    private void setControlleur(Controlleur controlleur) {
        this.controlleur = controlleur;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }
}
