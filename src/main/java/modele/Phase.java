package modele;

import java.util.List;

public class Phase {
    private final String name;
    private List<Object> myObject;

    public Phase(String name, List<Object> myObject) {
        this.name = name;
        this.myObject = myObject;
    }

    public String getName() {
        return name;
    }

    public List<Object> getMyObject() {
        return myObject;
    }

    public void setMyObject(List<Object> myObject) {
        this.myObject = myObject;
    }
}
