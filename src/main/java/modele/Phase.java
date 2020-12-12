package modele;

import java.util.ArrayList;
import java.util.List;

public class Phase {
    private final String name;
    private List<Object> myObject;

    public Phase(String name, List<Object> myObject) {
        this.name = name;
        this.myObject = myObject;
    }

    public Phase(String name) {
        this.name = name;
        this.myObject = myObject = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addObject(Object object)
    {
        this.myObject.add(object);
    }

    public List<Object> getMyObject() {
        return myObject;
    }

    public void setMyObject(List<Object> myObject) {
        this.myObject = myObject;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("name : ").append(this.getName()).append("\n");
        for(Object object : this.getMyObject())
        {
            res.append("-    ").append(object.toString()).append("\n");
        }
        return String.valueOf(res);
    }
}
