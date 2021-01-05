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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.*;
import modele.Phase;
import modele.Simulation;
import org.json.simple.parser.ParseException;

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

    @FXML
    public SplitMenuButton matRepartButton;
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
        stage.setScene(new Scene(monPane, 800,600));
        stage.show();


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
        for (MenuItem item : matRepartButton.getItems()) {
            if (actionEvent.getSource().equals(item)) {
                matRepartButton.setText(item.getText());
                ScenarioManagement.getInstance().getScenario().setAlgo_fill(FillingType.fromText(item.getText()));
                System.out.println(ScenarioManagement.getInstance().getScenario().getAlgo_fill());
            }
        }
    }

    public void matRiskOptionClick(ActionEvent actionEvent) {
        for (MenuItem item : matRiskButton.getItems()) {
            if (actionEvent.getSource().equals(item)) {
                matRiskButton.setText(item.getText());
            }
        }
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
        serieBudget.setName("Simulation");
        linechart.getData().add(serieBudget);

        Thread t = new Thread(() -> {
            double maxBudget = yAxis.getUpperBound();
            loadingBar.setVisible(true);
            runSimButton.setDisable(true);

            ArrayList<XYChart.Data<Integer, Integer>> points = new ArrayList<>();

            for (int k = 0; k < 150; k++){
                Simulation simu = new Simulation();
                Phase phase1 = new Phase("phase1");
                ObjectManagement om = new ObjectManagement(phase1);
                ArrayList<U1> rockets = simu.loadU1(om.getObjects());
                int budget = simu.runSimulation(rockets, DistributionType.EXPONENTIAL);
                System.out.printf("Budget : %d k€%n", budget);
                points.add(new XYChart.Data<>(k, budget));
                xAxis.setUpperBound(k);
                if ((double) budget > maxBudget) {
                    maxBudget = (double) budget;
                }
                yAxis.setUpperBound(maxBudget);
            }
            serieBudget.getData().addAll(points);
            loadingBar.setVisible(false);
            runSimButton.setDisable(false);
        });
        t.start();
    }
}
