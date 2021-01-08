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

    /*
        Constructeur : ScenarioManagement
        ---------------------------------
        Classe permettant de gérer le scenario en utilisant
        le pattern de programmation Singleton pour être sûr
        d'avoir une unique instance du scénario dans le projet
    */
    private ScenarioManagement() {
        this.scenarioPath = "res/Scenario.json";

        try {
            this.jsonObject = new JSONObject(Files.readString(Paths.get(scenarioPath)));
            this.scenario = Scenario.fromJsonObject(this.jsonObject);
        } catch (IOException | InvalidJSONFileException e) {
            e.printStackTrace();
        }
    }

    /*
        Méthode : getInstance()
        ------------------------------------------
        Permet de récupérer l'instance de manageur de scenario en
        suivant les principes du pattern Singleton.

        returns: l'instance du singleton ScenarioManagement
    */
    public static synchronized ScenarioManagement getInstance() {
        // si on a pas encore initialisé l'instance on le fait
        if (INSTANCE == null) {
            INSTANCE = new ScenarioManagement();
        }

        return INSTANCE;
    }

    public JSONObject getJSONObject() {
        return jsonObject;
    }

    /*
        Méthode : fromPath(filePath)
        ------------------------------------------
        Permet de charger un scenario dans le singleton depuis
        un chemin de fichier.
    */
    public void fromPath(String filePath) throws IOException, ParseException, InvalidJSONFileException {
        scenarioPath = filePath;

        File file = new File(this.scenarioPath);
        fromFile(file);
    }

    /*
        Méthode : fromFile(file)
        ------------------------------------------
        Permet de charger un scenario dans le singleton depuis
        un fichier.
    */
    public void fromFile(File file) throws IOException, ParseException, InvalidJSONFileException {
        this.jsonObject = new JSONObject(Files.readString(Paths.get(scenarioPath)));
        this.scenario = Scenario.fromJsonObject(this.jsonObject);
    }

    /*
        Méthode : fromScenario(scenario)
        ------------------------------------------
        Permet de charger un scenario dans le singleton depuis
        un scenario.
    */
    public void fromScenario(Scenario scenario) throws IOException, ParseException {
        scenarioPath = "res/Scenario.json";
        this.scenario = scenario;
        this.jsonObject = new JSONObject(Files.readString(Paths.get(scenarioPath)));
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    /*
        Méthode : editScenario(scenario)
        ------------------------------------------
        Permet de sauvegarder un scenario dans des données JSON.
    */
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

    /*
        Méthode : saveScenario(filepath)
        ------------------------------------------
        Permet de sauvegarder un scenario dans un fichier.
    */
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
