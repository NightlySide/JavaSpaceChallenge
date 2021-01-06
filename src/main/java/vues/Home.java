package vues;

import controlleurs.Controlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.*;
import modele.Object;
import modele.Phase;
import modele.Simulation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Home {
    @FXML
    public BorderPane monPane;

    @FXML
    public HBox linegraph;
    LineChart linechart;
    NumberAxis xAxis;
    NumberAxis yAxis;
    Scenario scenario;

    @FXML
    public ComboBox<String> matRepartButton;
    @FXML
    public ComboBox<String> matFuseeTypeButtonP1;
    @FXML
    public ComboBox<String> matFuseeTypeButtonP2;
    @FXML
    public ComboBox<String> crashDistribButton;
    @FXML
    public Button runSimButton;
    @FXML
    public ProgressBar loadingBar;
    @FXML
    public TextArea textConsole;
    @FXML
    public Spinner<Integer> nbIter;
    @FXML
    public Button clearScreenButton;
    @FXML
    public Button saveScenarioButton;

    private Stage stage;
    private Controlleur controlleur;

    public static Home creerInstance(Controlleur controlleur, Stage stage) {
        URL location = Home.class.getResource("/vues/Home.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Home vue = fxmlLoader.getController();
        vue.setStage(stage);
        vue.setControlleur(controlleur);
        vue.init();

        return vue;
    }

    public void init() {
        scenario = ScenarioManagement.getInstance().getScenario();

        xAxis = new NumberAxis(0, 20, 10);
        xAxis.setAutoRanging(false);

        yAxis = new NumberAxis(0, 100, 10);
        yAxis.setAutoRanging(false);

        linechart = new LineChart<>(xAxis, yAxis);
    }

    public void show() {
        stage.setTitle("JSC");
        stage.setScene(new Scene(monPane, 1024,600));
        stage.show();

        Console.getInstance().attachTextarea(textConsole);
        Console.getInstance().printHelloWorld();
        Console.getInstance().update();

        nbIter.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20000, 200, 100));

        linegraph.getChildren().add(linechart);
        linegraph.setAlignment(Pos.CENTER);

        saveScenarioButton.setDisable(true);
        clearScreenButton.setDisable(true);
        runSimButton.setDisable(true);
        crashDistribButton.setDisable(true);
        matRepartButton.setDisable(true);
        matFuseeTypeButtonP1.setDisable(true);
        matFuseeTypeButtonP2.setDisable(true);
    }

    private void setControlleur(Controlleur controlleur) {
        this.controlleur = controlleur;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public void editPhase(ActionEvent actionEvent) throws InvalidJSONFileException, IOException {
        controlleur.goToPhase();
    }

    public void matRepartOptionClick(ActionEvent actionEvent) {
        scenario.setAlgo_fill(FillingType.fromText(matRepartButton.getValue()));
        Console.getInstance().addLine("[+] Changed reparition algorithm to : " + scenario.getAlgo_fill());
    }

    public void crashDistribOption(ActionEvent actionEvent) {
        scenario.setCrashDistoType(DistributionType.fromText(crashDistribButton.getValue()));
        Console.getInstance().addLine("[+] Changed crash distrib to : " + scenario.getCrashDistoType());
    }

    public void matFuseeTypeOptionClickP1(ActionEvent actionEvent) {
        scenario.setRocketP1(matFuseeTypeButtonP1.getValue());
        Console.getInstance().addLine("[+] Changed rocket P1 to: " + scenario.getRocketP1());

    }
    public void matFuseeTypeOptionClickP2(ActionEvent actionEvent) {
        scenario.setRocketP2(matFuseeTypeButtonP2.getValue());
        Console.getInstance().addLine("[+] Changed rocket P2 to: " + scenario.getRocketP2());
    }

    public void runSimulationClick(ActionEvent actionEvent) {

        XYChart.Series<Integer,Integer> serieBudget = new XYChart.Series<>();
        serieBudget.setName(String.format("Simulation n°%d", linechart.getData().size() + 1));
        linechart.getData().add(serieBudget);

        Thread t = new Thread(() -> {
            double maxBudget = yAxis.getUpperBound();
            loadingBar.setVisible(true);
            runSimButton.setDisable(true);
            Console.getInstance().addLine(String.format("[  ] Démarrage de la simulation n°%d", linechart.getData().size()));

            Phase phase1 = new Phase("phase1");
            ObjectManagement om1 = new ObjectManagement(phase1);
            int nbItems = 0;
            for (Object o : om1.getObjects())
                nbItems += o.getQuantity();
            Console.getInstance().addLine(String.format("[  ] Phase 1 détectée: %d items", nbItems));
            Phase phase2 = new Phase("phase2");
            ObjectManagement om2 = new ObjectManagement(phase2);
            nbItems = 0;
            for (Object o : om2.getObjects())
                nbItems += o.getQuantity();
            Console.getInstance().addLine(String.format("[  ] Phase 2 détectée: %d items", nbItems));

            ArrayList<XYChart.Data<Integer, Integer>> points = new ArrayList<>();

            ArrayList<SimulationResults> results = new ArrayList<>();
            for (int k = 0; k < nbIter.getValue(); k++){
                try {
                    results.add(runOnce());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvalidJSONFileException e) {
                    e.printStackTrace();
                }

                points.add(new XYChart.Data<>(k, (int) results.get(results.size() - 1).budget));
                xAxis.setUpperBound(k);
                if ((double) results.get(results.size() - 1).budget > maxBudget) {
                    maxBudget = (double) results.get(results.size() - 1).budget;
                }
                yAxis.setUpperBound(maxBudget);
            }
            SimulationResults moyenne = SimulationResults.moyenne(results);
            //Console.getInstance().addLine("[+] Détail -> " + results.toString());
            Console.getInstance().addLine("[+] En moyenne -> " + moyenne.toString());
            serieBudget.getData().addAll(points);
            loadingBar.setVisible(false);
            runSimButton.setDisable(false);

            Console.getInstance().addLine("[+] Simulation terminée ...");
            Console.getInstance().update();
        });
        t.start();
    }
    public SimulationResults runOnce() throws ClassNotFoundException, InvalidJSONFileException, IllegalAccessException, InstantiationException {
        Simulation simu = new Simulation();
        SimulationResults results = new SimulationResults();
        Phase phase1 = new Phase("phase1");
        ObjectManagement om1 = new ObjectManagement(phase1);
        Phase phase2 = new Phase("phase2");
        ObjectManagement om2 = new ObjectManagement(phase2);
        ArrayList<? extends Rocket> rocketsP1 = null;

        if (scenario.getRocketP1().equals("U1"))
            rocketsP1 = simu.loadRocket(om1.getObjects(), U1.class);
        else
            rocketsP1 = simu.loadRocket(om1.getObjects(), U2.class);

        ArrayList<? extends Rocket> rocketsP2 = null;

        if (scenario.getRocketP2().equals("U1"))
            rocketsP2 = simu.loadRocket(om2.getObjects(), U1.class);
        else
            rocketsP2 = simu.loadRocket(om2.getObjects(), U2.class);

        ArrayList<Rocket> rockets = new ArrayList();
        rockets.addAll(rocketsP1);
        rockets.addAll(rocketsP2);
        return simu.runSimulation(rockets);
    }

    public void saveScenario(ActionEvent actionEvent) {
        Console.getInstance().addLine("[  ] Sauvegarde du scenario ...");
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregister le scenario");

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Scenario File", "*.json"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));

            File selectedFile = fileChooser.showSaveDialog(stage);

            if (selectedFile != null) {
                // sauvegarde du fichier
                ScenarioManagement.getInstance().saveScenario(selectedFile.getAbsolutePath());
                Console.getInstance().addLine("[+] Scenario sauvegardé !");
            }
            else {
                Console.getInstance().addLine("[-] Action annulée");
            }
        } catch (Exception e) {
            Console.getInstance().addLine("[-] Erreur dans la sauvegarde du scenario...");
        }


        try {
            ScenarioManagement.getInstance().editScenario(scenario);

        } catch (InvalidJSONFileException | IOException e) {
            e.printStackTrace();
            Console.getInstance().addLine("[-] Erreur dans la sauvegarde du scenario...");
        }
    }

    public void loadScenario(ActionEvent actionEvent) {
        Console.getInstance().addLine("[  ] Chargement de scenario ...");
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir un scenario");

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Scenario File", "*.json"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));

            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                try {
                    ScenarioManagement.getInstance().fromFile(selectedFile);

                    updateButtonsFromScenario();

                    saveScenarioButton.setDisable(false);
                    clearScreenButton.setDisable(false);
                    runSimButton.setDisable(false);
                    crashDistribButton.setDisable(false);
                    matRepartButton.setDisable(false);
                    matFuseeTypeButtonP1.setDisable(false);
                    matFuseeTypeButtonP2.setDisable(false);
                    Console.getInstance().addLine("[+] Scenario chargé !");
                }
                catch (Exception e) {
                    Console.getInstance().addLine("[-] Mauvais type de fichier");
                    Console.getInstance().addLine("(extension, type de fichier de config.)");
                }
            }
            else {
                Console.getInstance().addLine("[-] Aucun fichier chargé");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateButtonsFromScenario() {
        Console.getInstance().addLine("[  ] Updating buttons based on scenario...");
        // on param les boutons
        matRepartButton.setValue(scenario.getAlgo_fill().toText());
        matFuseeTypeButtonP1.setValue(scenario.getRocketP1());
        matFuseeTypeButtonP2.setValue(scenario.getRocketP2());
        crashDistribButton.setValue(scenario.getCrashDistoType().toText());
    }

    public void clearScreen(ActionEvent actionEvent) {
        linechart.getData().clear();
    }
}
