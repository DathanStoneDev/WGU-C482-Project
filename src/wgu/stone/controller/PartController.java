package wgu.stone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import wgu.stone.model.InHousePart;
import wgu.stone.model.Inventory;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    //form fields
    @FXML
    private TextField partIdField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField partInvField;
    @FXML
    private TextField partPriceField;
    @FXML
    private TextField partMaxField;
    @FXML
    private TextField partMinField;
    @FXML
    private TextField partMachineIdField;

    //buttons
    @FXML
    private RadioButton inHousePartButton;
    @FXML
    private RadioButton outsourcePartButton;
    @FXML
    private Button savePartButton;

    //toggle group
    @FXML
    private ToggleGroup addPartGroup;


    @FXML
    public void savePart(ActionEvent event) throws IOException{

        int id = Integer.parseInt(partIdField.getText());
        String name = partNameField.getText();
        int inv = Integer.parseInt(partInvField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int min = Integer.parseInt(partMinField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        int machineId = Integer.parseInt(partMachineIdField.getText());

        Inventory.addPart(new InHousePart(id, name, price, inv, min, max, machineId));

        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }


    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartGroup = new ToggleGroup();
        inHousePartButton.setToggleGroup(addPartGroup);
        outsourcePartButton.setToggleGroup(addPartGroup);
    }
}
