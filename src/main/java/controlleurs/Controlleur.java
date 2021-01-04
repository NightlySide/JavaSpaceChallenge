package controlleurs;

import javafx.stage.Stage;
import modele.InvalidJSONFileException;
import modele.Service;
import org.json.simple.parser.ParseException;
import vues.Home;
import vues.Phase;
import vues.Simulation;

import java.io.IOException;

public class Controlleur {

    private Home home;
    private Phase phase;
    private Simulation simulation;

    private Service service;

    public Controlleur(Stage primaryStage, Service service) {
        this.service = service;
        home = Home.creerInstance(this, primaryStage);
        home.show();
    }

    public Service getService() {
        return service;
    }

    public void goToPhase() throws ParseException, InvalidJSONFileException, IOException {
        phase = Phase.creerInstance(this, new Stage());
        phase.show();
    }

    public void goToSimulation()
    {
        simulation = Simulation.creerInstance(this, new Stage());
        simulation.show();
    }
}
