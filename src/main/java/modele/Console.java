package modele;

import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Console {
    /*
    class permettant l'affichage de messages de LOG sur un terminal de commande situé à droite de l'IHM
    */
    public int maxLines = 250;
    private ArrayList<String> lines;
    private TextArea textArea;
    private static Console INSTANCE;


    private Console() { // constructeur
        lines = new ArrayList<>();
    }
    public static synchronized Console getInstance() {  // récupère l'instance dans les variables globales
        if (INSTANCE == null) {
            INSTANCE = new Console();
        }
        return INSTANCE;
    }

    public void attachTextarea(TextArea ta) {  // attache la textArea javafx à la console
        textArea = ta;
    }
    public void addLine(String message) {   // ajout d'une ligne à l'invit de commande
        if (lines.size() > maxLines) {
            lines.remove(0);
        }
        lines.add(message);
        update();
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void addLines(String[] messages) {   // permet d'ajouter plusieurs lignes d'un coup
        for (String msg : messages)
            addLine(msg);
    }

    public void addLines(ArrayList<String> messages) {
        for (String msg : messages)
            addLine(msg);
    }

    public void update() {   // mise à jour de la console quand un message se rajoute à la pile
        if (textArea != null) {
            String content = "";
            for (String msg : lines)
                content += msg + "\n";
            textArea.setText(content);
            textArea.setScrollTop(Double.MAX_VALUE);
        }
    }

    public TextArea getAttachedTextArea() {
        return textArea;
    }

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