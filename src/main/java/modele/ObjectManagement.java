package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;

public class ObjectManagement {
    enum PRIORITY {
        LOW,
        MEDIUM,
        HIGH
    }

    private final String objectsPath;
    private final Phase phase;
    private final JSONObject jsonObject;

    public ObjectManagement(Phase phase) throws IOException, ParseException {
        this.objectsPath = "res/Objects.json";
        this.phase = phase;

        File file = new File(this.objectsPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public ObjectManagement(String objectsPath, Phase phase) throws IOException, ParseException {
        this.objectsPath = objectsPath;
        this.phase = phase;

        File file = new File(this.objectsPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public String getObjectsPath() {
        return objectsPath;
    }

    public Phase getPhase() {
        return phase;
    }

    public Phase getObjects() throws IOException, ParseException, InvalidJSONFileException {

        String value = (String) jsonObject.get("type");
        if (value.compareTo("objects")==0) {
            JSONArray ph = (JSONArray) jsonObject.get(this.phase.getName());
            for(java.lang.Object val : ph)
            {
                if(val instanceof JSONObject)
                {
                    JSONObject data = (JSONObject) val;
                    this.phase.addObject(new Object(
                            (String) data.get("name"),
                            Math.toIntExact((long) data.get("price")),
                            Math.toIntExact((long)data.get("weight")),
                            Math.toIntExact((long)data.get("quantity")),
                            (String) data.get("priority")
                    ));
                }
            }
            return phase;
        }
        else
        {
            throw new InvalidJSONFileException("Invalid type !");
        }
    }

    public void addObjects(Object object) throws IOException, ParseException, InvalidJSONFileException {
        File file = new File(this.objectsPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        JSONObject jsonObject = (JSONObject) o;
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
            phase.add(newObject);
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            writer.write(jsonObject.toJSONString());
            writer.close();
        }
        else {
            throw new InvalidJSONFileException("Invalid type !");
        }
    }
}
