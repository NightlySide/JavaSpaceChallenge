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

import java.io.IOException;
import java.net.URL;

public class Phase {
    @FXML
    public VBox monPane;
    @FXML
    public TableView<Object> tablePhase1;
    @FXML
    public TableView<Object> tablePhase2;
    @FXML
    public javafx.scene.control.Button closeButton;

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

    public void show() throws InvalidJSONFileException, IOException {
        stage.setTitle("JSC");
        stage.setScene(new Scene(monPane, 800,600));
        stage.show();
        initObjectPhase1();
        initObjectPhase2();
    }

    public void initObjectPhase1() throws IOException, InvalidJSONFileException {
        modele.Phase phase = new modele.Phase("phase1");
        phase = new ObjectManagement(phase).getPhase();
        tablePhase1.getItems().addAll(phase.getObjects());
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
        TableColumn<Object, String> weight = new TableColumn<>("weight");
        TableColumn<Object, String> quantity = new TableColumn<>("quantity");
        TableColumn<Object, String> priority = new TableColumn<>("priority");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        weight.setCellValueFactory(
                new PropertyValueFactory<>("weight")
        );
        quantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        priority.setCellValueFactory(
                new PropertyValueFactory<>("priority")
        );


        tablePhase1.getColumns().set(0,name);
        tablePhase1.getColumns().set(1,price);
        tablePhase1.getColumns().set(2,weight);
        tablePhase1.getColumns().set(3,quantity);
        tablePhase1.getColumns().set(4,priority);
    }

    public void initObjectPhase2() throws IOException, InvalidJSONFileException {
        modele.Phase phase = new modele.Phase("phase2");
        phase = new ObjectManagement(phase).getPhase();
        tablePhase2.getItems().addAll(phase.getObjects());
        prepareTablePhase2();
    }

    private void prepareTablePhase2()
    {
        tablePhase2.setEditable(true);

        tablePhase2.setRowFactory(tv -> {
            TableRow<Object> row = new TableRow<>();


            return row;
        });

        TableColumn<Object, String> name = new TableColumn<>("name");
        TableColumn<Object, String> price = new TableColumn<>("price");
        TableColumn<Object, String> weight = new TableColumn<>("weight");
        TableColumn<Object, String> quantity = new TableColumn<>("quantity");
        TableColumn<Object, String> priority = new TableColumn<>("priority");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        weight.setCellValueFactory(
                new PropertyValueFactory<>("weight")
        );
        quantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        priority.setCellValueFactory(
                new PropertyValueFactory<>("priority")
        );


        tablePhase2.getColumns().set(0,name);
        tablePhase2.getColumns().set(1,price);
        tablePhase2.getColumns().set(2,weight);
        tablePhase2.getColumns().set(3,quantity);
        tablePhase2.getColumns().set(4,priority);
    }

    private void setControlleur(Controlleur controlleur) {
        this.controlleur = controlleur;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
