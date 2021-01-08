package controlleurs;

import javafx.stage.Stage;
import modele.InvalidJSONFileException;
import vues.Home;
import vues.Phase;

import java.io.IOException;

public class Controlleur {

    private Home home;
    private Phase phase;

    /*
        Constructeur : Controlleur(primaryStage)
        ------------------------------------------
        Créer une instance de controlleur qui va lancer
        l'interface utilisateur en suivant le schéma de conception
        MVC.

        primaryStage (Stage): la scene à exécuter
     */
    public Controlleur(Stage primaryStage) {
        home = Home.creerInstance(this, primaryStage);
        home.show();
    }

    /*
        Méthode : goToPhase()
        ------------------------------------------
        Permet d'ouvrir la deuxième fenetre correspondant à l'édition
        des objets.
     */
    public void goToPhase() throws InvalidJSONFileException, IOException {
        phase = Phase.creerInstance(this, new Stage());
        phase.show();
    }
}
