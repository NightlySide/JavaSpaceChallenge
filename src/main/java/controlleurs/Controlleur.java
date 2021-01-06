package controlleurs;

import javafx.stage.Stage;
import modele.InvalidJSONFileException;
import modele.Service;
import vues.Home;
import vues.Phase;

import java.io.IOException;

public class Controlleur {

    private Home home;
    private Phase phase;

    private Service service;

    public Controlleur(Stage primaryStage, Service service) {
        this.service = service;
        home = Home.creerInstance(this, primaryStage);
        home.show();
    }

    public Service getService() {
        return service;
    }

    public void goToPhase() throws InvalidJSONFileException, IOException {
        phase = Phase.creerInstance(this, new Stage());
        phase.show();
    }
}
