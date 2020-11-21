package controlleurs;

import javafx.stage.Stage;
import modele.Service;
import vues.Home;

public class Controlleur {

    private Home home;
    private Service service;

    public Controlleur(Stage primaryStage, Service service) {
        this.service = service;
        home = Home.creerInstance(this, primaryStage);
        home.show();
    }

    public Service getService() {
        return service;
    }
}
