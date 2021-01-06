package modele;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ObjectManagement {
    enum PRIORITY {
        LOW,
        MEDIUM,
        HIGH
    }

    private final String objectsPath;
    private final Phase phase;
    private JSONObject jsonObject;

    public ObjectManagement(Phase phase) {
        this.objectsPath = "res/Objects.json";
        this.phase = phase;

        try {
            this.jsonObject = new JSONObject(Files.readString(Paths.get(this.objectsPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectManagement(String objectsPath, Phase phase) {
        this.objectsPath = objectsPath;
        this.phase = phase;

        try {
            this.jsonObject = new JSONObject(Files.readString(Paths.get(this.objectsPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getObjectsPath() {
        return objectsPath;
    }

    public Phase getPhase() {
        return phase;
    }

    public ArrayList<Object> getObjects() throws InvalidJSONFileException {

        String value = (String) jsonObject.get("type");
        if (value.compareTo("objects")==0) {
            JSONArray ph = (JSONArray) jsonObject.get(this.phase.getName());
            for(java.lang.Object val : ph)
            {
                if(val instanceof JSONObject)
                {
                    JSONObject data = (JSONObject) val;
                    this.phase.addObject(new Object(
                            data.getString("name"),
                            Math.toIntExact(data.getLong("price")),
                            Math.toIntExact(data.getLong("weight")),
                            Math.toIntExact(data.getLong("quantity")),
                            data.getString("priority")
                    ));
                }
            }
            return phase.getObjects();
        }
        else
        {
            throw new InvalidJSONFileException("Wrong object type!");
        }
    }

    public void addObjects(Object object) throws IOException, InvalidJSONFileException {
        String value = (String) jsonObject.get("type");
        if (value.compareTo("objects")==0)
        {
            HashMap<String, java.lang.Object> newObject = new HashMap<>();
            newObject.put("name", object.getName());
            newObject.put("price", object.getPrice());
            newObject.put("weight", object.getWeight());
            newObject.put("quantity", object.getQuantity());
            newObject.put("priority", object.getPriority());
            JSONArray phase = (JSONArray) jsonObject.get(this.phase.getName());
            phase.put(newObject);
            FileWriter writer = new FileWriter(this.objectsPath);
            writer.write(jsonObject.toString(4));
            writer.close();
        }
        else {
            throw new InvalidJSONFileException("Invalid type !");
        }
    }
}
