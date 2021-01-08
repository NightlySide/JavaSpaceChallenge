package modele;

import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Console {

    public int maxLines = 250;
    private ArrayList<String> lines;
    private TextArea textArea;
    private static Console INSTANCE;


    /*
        Constructeur : Console()
        ------------------------------------------
        Couche d'abstraction pour gérer les messages évènementiels
        et les afficher dans une un objet Textarea.
        Cette classe utilise le pattern de programmation Singleton
        pour n'avoir qu'une seule instance de Console dans tout
        le programme;
    */
    private Console() { // constructeur
        lines = new ArrayList<>();
    }

    /*
        Méthode : getInstance()
        ------------------------------------------
        Permet de récupérer l'instance de console en
        suivant les principes du pattern Singleton.

        returns: l'instance du singleton console
    */
    public static synchronized Console getInstance() {
        // si on a pas encore initialisé l'instance on le fait
        if (INSTANCE == null) {
            INSTANCE = new Console();
        }
        return INSTANCE;
    }

    /*
        Méthode : attachTextarea()
        ------------------------------------------
        Permet d'attancher une instance de textarea
        à l'instance actuelle de console.

        ta (TextArea) : l'object à attacher à l'instance
    */
    public void attachTextarea(TextArea ta) {  // attache la textArea javafx à la console
        textArea = ta;
    }

    /*
        Méthode : addLine(message)
        ------------------------------------------
        Ajoute une ligne à la console. Si le nombre
        maximal de lignes est atteint, on supprime
        les lignes les plus anciennes.

        message(String): ligne à ajouter à la console
    */
    public void addLine(String message) {   // ajout d'une ligne à l'invit de commande
        if (lines.size() > maxLines) {
            lines.remove(0);
        }
        lines.add(message);
        update();
    }

    /*
        Méthode : getLines()
        ------------------------------------------
        Retourne les lignes stockées dans la console.

        returns: les lignes stockées dans la console
    */
    public ArrayList<String> getLines() {
        return lines;
    }

    /*
        Méthode : addLines(messages)
        ------------------------------------------
        Ajoute une liste de ligne à la console..

        messages(String []): les lignes à ajouter
    */
    public void addLines(String[] messages) {
        for (String msg : messages)
            addLine(msg);
    }

    /*
        Méthode : addLines(messages)
        ------------------------------------------
        Ajoute une liste de ligne à la console..

        messages(ArrayList<String>): les lignes à ajouter
    */
    public void addLines(ArrayList<String> messages) {
        for (String msg : messages)
            addLine(msg);
    }

    /*
        Méthode : update()
        ------------------------------------------
        Met a jour la textarea avec les lignes si elle
        est attachée à l'instance. Dans ce cas on va
        scoller jusqu'en bas du container pour afficher
        les messages les plus récents.
    */
    public void update() {
        if (textArea != null) {
            // on crée le messgae à afficher
            String content = "";
            for (String msg : lines)
                content += msg + "\n";
            // on update la textarea
            textArea.setText(content);
            // on scrolle tout en bas
            textArea.setScrollTop(Double.MAX_VALUE);
        }
    }

    /*
        Méthode : getAttachedTextArea()
        ------------------------------------------
        Permet de récupérer la référence vers l'objet textarea
        rattaché à la console.

        returns: l'objet textarea attaché à l'instance
    */
    public TextArea getAttachedTextArea() {
        return textArea;
    }

    /*
        Méthode : printHelloWorld()
        ------------------------------------------
        Affiche le message d'accueil dans la console
        au lancement du programme.
    */
    public void printHelloWorld() {
        addLine("#=====================================#");
        addLine("Programme d'aide à la décision");
        addLine("Imaginé et développé par :");
        addLine("\t- Alexandre FROEHLICH");
        addLine("\t- Guillaume LEINEN");
        addLine("\t- Erwan AUBRY");
        addLine("#=====================================#");
        addLine("");
        addLine("[+] Programme prêt à fonctionner !");
    }
}