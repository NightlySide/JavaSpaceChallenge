package controlleurs;

import javafx.stage.Stage;
import modele.Service;
import vues.Home;
import vues.Phase;

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

    public void goToPhase()
    {
        phase = Phase.creerInstance(this, new Stage());
        phase.show();
    }
}
