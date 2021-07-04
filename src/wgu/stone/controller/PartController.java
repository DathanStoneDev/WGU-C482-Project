package wgu.stone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import wgu.stone.model.InHousePart;
import wgu.stone.model.Inventory;
import wgu.stone.model.OutsourcedPart;
import wgu.stone.model.Part;

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


    //toggle group
    @FXML
    private ToggleGroup addPartGroup;

    @FXML
    private Label labelChange;

    boolean isInHouse;

    @FXML
    private void buttonInHouse(){
        labelChange.setText("Machine ID");
        isInHouse = true;
    }

    @FXML
    private void buttonOutsource(){
        labelChange.setText("Company");
        isInHouse = false;

    }

    //change this name to saveNew Part
    @FXML
    public void savePart(ActionEvent event) throws IOException{

        int id = Integer.parseInt(partIdField.getText());
        String name = partNameField.getText();
        int inv = Integer.parseInt(partInvField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int min = Integer.parseInt(partMinField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        String machineId = (partMachineIdField.getText());

        if(isInHouse){
            Inventory.addPart(new InHousePart(id, name, price, inv, min, max, Integer.parseInt(machineId)));
        } else {
            Inventory.addPart(new OutsourcedPart(id, name, price, inv, min, max, machineId));
        }

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
        inHousePartButton.setSelected(true);
    }
    //maybe delete this and try again.
    public void setPartInModifyPage(Part part) {
        partIdField.setText(Integer.toString(part.getId()));
        partNameField.setText(part.getName());
        partInvField.setText(Integer.toString(part.getStock()));
        partPriceField.setText(Double.toString(part.getPrice()));
        partMaxField.setText(Integer.toString(part.getMax()));
        partMinField.setText(Integer.toString(part.getMin()));

        if(part instanceof InHousePart)  {
            InHousePart inHousePart = (InHousePart) part;
            partMachineIdField.setText(Integer.toString(inHousePart.getMachineId()));
        }
    }
}
