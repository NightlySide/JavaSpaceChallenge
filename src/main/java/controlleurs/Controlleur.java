package controlleurs;

import javafx.stage.Stage;
import modele.InvalidJSONFileException;
import vues.Home;
import vues.Phase;

import java.io.IOException;

public class Controlleur {

    private Home home;
    private Phase phase;

    public Controlleur(Stage primaryStage) {
        home = Home.creerInstance(this, primaryStage);
        home.show();
    }

    public void goToPhase() throws InvalidJSONFileException, IOException {
        phase = Phase.creerInstance(this, new Stage());
        phase.show();
    }
}
