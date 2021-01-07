package modele;

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;

public class ScenarioManagement {
    private String scenarioPath;
    private Scenario scenario;
    private JSONObject jsonObject;

    private static ScenarioManagement INSTANCE = null;

    private ScenarioManagement() {
        this.scenarioPath = "res/Scenario.json";

        try {
            this.jsonObject = new JSONObject(Files.readString(Paths.get(scenarioPath)));
            this.scenario = Scenario.fromJsonObject(this.jsonObject);
        } catch (IOException | InvalidJSONFileException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ScenarioManagement getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScenarioManagement();
        }

        return INSTANCE;
    }

    public JSONObject getJSONObject() {
        return jsonObject;
    }

    public void fromPath(String filePath) throws IOException, ParseException, InvalidJSONFileException {
        scenarioPath = filePath;

        File file = new File(this.scenarioPath);
        fromFile(file);
    }

    public void fromFile(File file) throws IOException, ParseException, InvalidJSONFileException {
        this.jsonObject = new JSONObject(Files.readString(Paths.get(scenarioPath)));
        this.scenario = Scenario.fromJsonObject(this.jsonObject);
    }
    public void fromScenario(Scenario scenario) throws IOException, ParseException {
        scenarioPath = "res/Scenario.json";
        this.scenario = scenario;
        this.jsonObject = new JSONObject(Files.readString(Paths.get(scenarioPath)));
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public int editScenario(Scenario scenario) throws InvalidJSONFileException, IOException {
        String value = (String) jsonObject.get("type");
        if (value.compareTo("scenario")==0)
        {
            HashMap<String, java.lang.Object> nsc = new HashMap<>();
            nsc.put("percentage_u1", scenario.getPercentage_u1());
            nsc.put("percentage_fill", scenario.getPercentage_fill());
            nsc.put("algo_fill",scenario.getAlgo_fill().toString());
            nsc.put("crash_distro_type", scenario.getCrashDistoType().toString());
            nsc.put("rocket_P1", scenario.getRocketP1());
            nsc.put("rocket_P2", scenario.getRocketP2());

            jsonObject.remove("params");
            jsonObject.put("params",nsc);
            FileWriter writer = new FileWriter(this.scenarioPath);
            writer.write(jsonObject.toString(4));
            writer.close();
        }
        else
        {
            throw new InvalidJSONFileException("Invalid type !");
        }
        return 1;
    }

    public void saveScenario(String filepath) throws IOException {
        HashMap<String, java.lang.Object> nsc = new HashMap<>();
        nsc.put("percentage_u1", scenario.getPercentage_u1());
        nsc.put("percentage_fill", scenario.getPercentage_fill());
        nsc.put("algo_fill",scenario.getAlgo_fill().toString());
        nsc.put("crash_distro_type", scenario.getCrashDistoType().toString());

        JSONObject jsonData = new JSONObject();
        jsonData.put("params", nsc);
        FileWriter writer = new FileWriter(filepath);
        writer.write(jsonData.toString(4));
        writer.close();
    }
}
