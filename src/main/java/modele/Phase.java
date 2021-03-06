package modele;

import java.util.ArrayList;
import java.util.List;

public class Phase {

    private final String name;
    private ArrayList<Object> objects;


    /*
        Constructeur : Phase(name, objets)
        ----------------------
        Classe représentant une phase de mission.

        name (String): nom de la phase
        objects (ArrayList<Object>): liste des objets à ajouter par défaut
    */
    public Phase(String name, ArrayList<Object> objects) {
        this.name = name;
        this.objects = objects;
    }

    /*
        Constructeur : Phase(name, objets)
        ----------------------
        Classe représentant une phase de mission.

        name (String): nom de la phase
    */
    public Phase(String name) {
        this.name = name;
        this.objects = objects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addObject(Object object)
    {
        this.objects.add(object);
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObject(ArrayList<Object> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("name : ").append(this.getName()).append("\n");
        for(Object object : this.getObjects())
        {
            res.append("-    ").append(object.toString()).append("\n");
        }
        return String.valueOf(res);
    }
}
