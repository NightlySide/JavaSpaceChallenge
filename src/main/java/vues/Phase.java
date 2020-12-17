package vues;

import controlleurs.Controlleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.InvalidJSONFileException;
import modele.Object;
import modele.ObjectManagement;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class Phase {
    @FXML
    public VBox monPane;
    @FXML
    public TableView<Object> tablePhase1;
    @FXML
    public TableView<Object> tablePhase2;

    private Stage stage;
    private Controlleur controlleur;

    public static Phase creerInstance(Controlleur controlleur, Stage stage) {
        URL location = Phase.class.getResource("/vues/Phase.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Phase vue = fxmlLoader.getController();
        vue.setStage(stage);
        vue.setControlleur(controlleur);

        return vue;
    }

    public void show() throws ParseException, InvalidJSONFileException, IOException {
        stage.setTitle("JSC");
        stage.setScene(new Scene(monPane, 800,600));
        stage.show();
        initObjectPhase1();
    }

    public void initObjectPhase1() throws IOException, ParseException, InvalidJSONFileException {
        modele.Phase phase = new modele.Phase("phase1");
        phase = new ObjectManagement(phase).getObjects();
        tablePhase1.getItems().addAll(phase.getMyObject());
        prepareTablePhase1();
    }

    private void prepareTablePhase1()
    {
        tablePhase1.setEditable(true);

        tablePhase1.setRowFactory(tv -> {
            TableRow<Object> row = new TableRow<>();


            return row;
        });

        TableColumn<Object, String> name = new TableColumn<>("name");
        TableColumn<Object, String> price = new TableColumn<>("price");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name")

        );


        tablePhase1.getColumns().set(0,name);
    }

    private void setControlleur(Controlleur controlleur) {
        this.controlleur = controlleur;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }
}
