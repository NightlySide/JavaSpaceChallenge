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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.*;
import modele.Phase;
import modele.Simulation;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public SplitMenuButton matRiskButton;
    @FXML
    public SplitMenuButton matFuseeTypeButton;
    @FXML
    public SplitMenuButton humanRiskButton;
    @FXML
    public SplitMenuButton humanFuseeTypeButton;
    @FXML
    public Button runSimButton;
    @FXML
    public ProgressBar loadingBar;
    @FXML
    public TextArea textConsole;

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

        return vue;
    }

    public void show() {
        stage.setTitle("JSC");
        stage.setScene(new Scene(monPane, 1024,600));
        stage.show();

        scenario = ScenarioManagement.getInstance().getScenario();

        Console.getInstance().attachTextarea(textConsole);
        Console.getInstance().printHelloWorld();
        Console.getInstance().update();

        xAxis = new NumberAxis(0, 20, 10);
        xAxis.setAutoRanging(false);

        yAxis = new NumberAxis(0, 100, 10);
        yAxis.setAutoRanging(false);

        linechart = new LineChart(xAxis, yAxis);

        linegraph.getChildren().add(linechart);
        linegraph.setAlignment(Pos.CENTER);
    }

    private void setControlleur(Controlleur controlleur) {
        this.controlleur = controlleur;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public void editPhase(ActionEvent actionEvent) throws ParseException, InvalidJSONFileException, IOException {
        controlleur.goToPhase();
    }

    public void matRepartOptionClick(ActionEvent actionEvent) {
        scenario.setAlgo_fill(FillingType.fromText(matRepartButton.getValue()));
        Console.getInstance().addLine("[+] Changed reparition algorithm to : " + scenario.getAlgo_fill());
    }

    public void matRiskOptionClick(ActionEvent actionEvent) {
        scenario.setAlgo_fill(FillingType.fromText(matRepartButton.getValue()));
        Console.getInstance().addLine("[+] Changed reparition algorithm to : " + scenario.getAlgo_fill());
    }

    public void matFuseeTypeOptionClick(ActionEvent actionEvent) {
        for (MenuItem item : matFuseeTypeButton.getItems()) {
            if (actionEvent.getSource().equals(item)) {
                matFuseeTypeButton.setText(item.getText());
            }
        }
    }

    public void humanRiskOptionClick(ActionEvent actionEvent) {
        for (MenuItem item : humanRiskButton.getItems()) {
            if (actionEvent.getSource().equals(item)) {
                humanRiskButton.setText(item.getText());
            }
        }
    }

    public void humanFuseeTypeOptionClick(ActionEvent actionEvent) {
        for (MenuItem item : humanFuseeTypeButton.getItems()) {
            if (actionEvent.getSource().equals(item)) {
                humanFuseeTypeButton.setText(item.getText());
            }
        }
    }

    public void runSimulationClick(ActionEvent actionEvent) {


        XYChart.Series<Integer,Integer> serieBudget = new XYChart.Series<>();
        serieBudget.setName(String.format("Simulation n°%d", linechart.getData().size() + 1));
        linechart.getData().add(serieBudget);

        Thread t = new Thread(() -> {
            double maxBudget = yAxis.getUpperBound();
            double moyenne = 0;
            loadingBar.setVisible(true);
            runSimButton.setDisable(true);
            Console.getInstance().addLine(String.format("[  ] Démarrage de la simulation n°%d", linechart.getData().size()));

            ArrayList<XYChart.Data<Integer, Integer>> points = new ArrayList<>();

            int nbIter = 2000;
            for (int k = 0; k < nbIter; k++){
                Simulation simu = new Simulation();
                Phase phase1 = new Phase("phase1");
                ObjectManagement om = new ObjectManagement(phase1);
                ArrayList<U1> rockets = null;
                try {
                    rockets = simu.loadU1(om.getObjects());
                } catch (InvalidJSONFileException e) {
                    e.printStackTrace();
                }
                double budget = simu.runSimulation(rockets, DistributionType.EXPONENTIAL);
                points.add(new XYChart.Data<>(k, (int) budget));
                xAxis.setUpperBound(k);
                if ((double) budget > maxBudget) {
                    maxBudget = (double) budget;
                }
                yAxis.setUpperBound(maxBudget);
                moyenne += budget;
            }
            moyenne = moyenne / nbIter;
            Console.getInstance().addLine(String.format("Budget moyen : %.2f k€", moyenne));
            serieBudget.getData().addAll(points);
            loadingBar.setVisible(false);
            runSimButton.setDisable(false);

            Console.getInstance().addLine("[+] Simulation terminée ...");
            Console.getInstance().update();
        });
        t.start();
    }

    public void saveScenario(ActionEvent actionEvent) {
        try {
            ScenarioManagement.getInstance().editScenario(scenario);
            Console.getInstance().addLine("[+] Scenario sauvegardé !");
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

                    // on param les boutons
                    matRepartButton.setValue(scenario.getAlgo_fill().toText());

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

    public void clearScreen(ActionEvent actionEvent) {
        linechart.getData().clear();
    }

    public void getNbIterations(ActionEvent actionEvent) {
        Console.getInstance().addLine("NbChanged");
    }
}
