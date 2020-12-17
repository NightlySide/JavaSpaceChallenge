package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;

public class ScenarioManagement {
    private final String scenarioPath;
    private Scenario scenario;
    private final JSONObject jsonObject;

    public ScenarioManagement(Scenario scenario) throws IOException, ParseException {
        this.scenarioPath = "res/Scenario.json";
        this.scenario = scenario;

        File file = new File(this.scenarioPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public ScenarioManagement() throws IOException, ParseException {
        this.scenarioPath = "res/Scenario.json";

        File file = new File(this.scenarioPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public ScenarioManagement(String scenarioPath, Scenario scenario) throws IOException, ParseException {
        this.scenarioPath = scenarioPath;
        this.scenario = scenario;

        File file = new File(this.scenarioPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public ScenarioManagement(String scenarioPath) throws IOException, ParseException {
        this.scenarioPath = scenarioPath;

        File file = new File(this.scenarioPath);
        JSONParser parser = new JSONParser();
        java.lang.Object o = parser.parse(new FileReader(file.getAbsoluteFile()));
        this.jsonObject = (JSONObject) o;
    }

    public Scenario getScenario() throws InvalidJSONFileException {
        String value = (String) jsonObject.get("type");
        if (value.compareTo("scenario")==0) {
            JSONObject sc = (JSONObject) jsonObject.get("params");
            this.scenario = new Scenario(
                    (Double) sc.get("percentage_u1"),
                    (Double) sc.get("percentage_fill"),
                    (String) sc.get("algo_fill"),
                    DistributionType.valueOf((String) sc.get("crash_distro_type"))
            );
            return this.scenario;
        }
        else
        {
            throw new InvalidJSONFileException("Invalid type !");
        }
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
