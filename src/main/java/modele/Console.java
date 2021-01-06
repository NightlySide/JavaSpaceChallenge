package modele;

import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Console {

    public int maxLines = 50;

    private ArrayList<String> lines;
    private TextArea textArea;

    private static Console INSTANCE;

    private Console() {
        lines = new ArrayList<>();
    }

    public static synchronized Console getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Console();
        }

        return INSTANCE;
    }

    public void attachTextarea(TextArea ta) {
        textArea = ta;
    }

    public void addLine(String message) {
        if (lines.size() > maxLines) {
            lines.remove(0);
        }
        lines.add(message);
        update();
    }

    public void addLines(String[] messages) {
        for (String msg : messages)
            addLine(msg);
    }

    public void addLines(ArrayList<String> messages) {
        for (String msg : messages)
            addLine(msg);
    }

    public void update() {
        if (textArea != null) {
            String content = "";
            for (String msg : lines)
                content += msg + "\n";
            textArea.setText(content);
            textArea.setScrollTop(Double.MAX_VALUE);
        }
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