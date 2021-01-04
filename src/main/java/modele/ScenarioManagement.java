package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class ScenarioManagement {
    private String scenarioPath;
    private Scenario scenario;
    private JSONObject jsonObject;

    private static ScenarioManagement INSTANCE = null;

    private ScenarioManagement() {
        this.scenarioPath = "res/Scenario.json";

        File file = new File(this.scenarioPath);
        JSONParser parser = new JSONParser();
        try {
            java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
            this.jsonObject = (JSONObject) o;
            this.scenario = Scenario.fromJsonObject(this.jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ScenarioManagement getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScenarioManagement();
        }

        return INSTANCE;
    }

    public void fromPath(String filePath) throws IOException, ParseException {
        scenarioPath = filePath;

        File file = new File(this.scenarioPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        jsonObject = (JSONObject) o;
        this.scenario = Scenario.fromJsonObject(this.jsonObject);
    }

    public void fromScenario(Scenario scenario) throws IOException, ParseException {
        scenarioPath = "res/Scenario.json";
        this.scenario = scenario;

        File file = new File(scenarioPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public void editScenario(Scenario scenario) throws InvalidJSONFileException, IOException {
        String value = (String) jsonObject.get("type");
        if (value.compareTo("scenario")==0)
        {
            HashMap<String, java.lang.Object> nsc = new HashMap<>();
            nsc.put("percentage_u1", scenario.getPercentage_u1());
            nsc.put("percentage_fill", scenario.getPercentage_fill());
            nsc.put("algo_fill", scenario.getAlgo_fill());

            jsonObject.remove("params");
            jsonObject.put("params",nsc);
            FileWriter writer = new FileWriter(this.scenarioPath);
            writer.write(jsonObject.toJSONString());
            writer.close();
        }
        else
        {
            throw new InvalidJSONFileException("Invalid type !");
        }
    }
}
