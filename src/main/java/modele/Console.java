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

    public void attachTextfield(TextArea ta) {
        ta = textArea;
    }

    public void addLine(String message) {
        if (lines.size() > maxLines) {
            lines.remove(0);
        }
        lines.add(message);
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
        }
    }
}