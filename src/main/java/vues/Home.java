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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.FillingType;
import modele.InvalidJSONFileException;
import modele.Scenario;
import modele.ScenarioManagement;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class Home {
    @FXML
    public BorderPane monPane;

    @FXML
    public HBox linegraph;

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

    private void initChart()
    {
        NumberAxis xAxis = new NumberAxis(0, 20, 10);
        xAxis.setLabel("Hour");

        NumberAxis yAxis = new NumberAxis   (0, 100, 10);
        yAxis.setLabel("Cookies");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series<Integer,Integer> series = new XYChart.Series<>();
        series.setName("Number of cookies in my room");
        series.getData().add(new XYChart.Data<>(0, 100));
        series.getData().add(new XYChart.Data<>(6, 80));
        series.getData().add(new XYChart.Data<>(10, 50));
        series.getData().add(new XYChart.Data<>(12, 40));
        series.getData().add(new XYChart.Data<>(16, 20));
        series.getData().add(new XYChart.Data<>(20, 0));

        XYChart.Series<Integer,Integer> series2 = new XYChart.Series<>();
        series2.setName("My weight");
        series2.getData().add(new XYChart.Data<>(0, 60));
        series2.getData().add(new XYChart.Data<>(6, 62));
        series2.getData().add(new XYChart.Data<>(10, 68));
        series2.getData().add(new XYChart.Data<>(12, 75));
        series2.getData().add(new XYChart.Data<>(16, 79));
        series2.getData().add(new XYChart.Data<>(20, 80));

        linechart.getData().addAll(series,series2);

        linegraph.getChildren().add(linechart);
        linegraph.setAlignment(Pos.CENTER);
    }

    public void show() {
        stage.setTitle("JSC");
        stage.setScene(new Scene(monPane, 800,600));
        stage.show();
        this.initChart();
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
}
