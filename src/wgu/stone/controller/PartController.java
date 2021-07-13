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
    //Part Text Fields
    @FXML private TextField partIdField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxField;
    @FXML private TextField partMinField;
    @FXML private TextField partMachineIdField;

    //Radio Buttons
    @FXML private RadioButton inHousePartButton;
    @FXML private RadioButton outsourcePartButton;


    //toggle group
    @FXML private ToggleGroup addPartGroup;

    @FXML private Label labelChange;

    boolean isInHouse;

    @FXML private Button saveButton;

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


        String partName = partNameField.getText();
        if(partName.isEmpty()) {
            UtilityClass.errorAlerts(4);
            return;
        }

        int inv;
        try {
            inv = Integer.parseInt(partInvField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(6);
            return;
        }
        double partPrice;
        try {
            partPrice = Double.parseDouble(partPriceField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(5);
            return;
        }

        int min;
        try {
            min = Integer.parseInt(partMinField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(7);
            return;
        }

        int max;
        try {
            max = Integer.parseInt(partMaxField.getText());
        } catch (NumberFormatException e){
            UtilityClass.errorAlerts(8);
            return;
        }

        if(min > max) {
            UtilityClass.errorAlerts(1);
            return;
        }

        if(inv < min || inv > max) {
            UtilityClass.errorAlerts(2);
            return;
        }


        int partId = 0;
        for(Part i : Inventory.getAllParts()) {
            if(i.getId() >= partId) {
                partId = i.getId() + 1;
            }
        }

        if(isInHouse){
            int machineId;
            try {
                machineId = Integer.parseInt(partMachineIdField.getText());
            } catch (NumberFormatException e){
                UtilityClass.errorAlerts(9);
                return;
            }
            Inventory.addPart(new InHousePart(partId, partName, partPrice, inv, min, max, machineId));

            UtilityClass.BackToMainScreen(saveButton);

        } else {
            String companyName = partMachineIdField.getText();
            if(companyName.isEmpty()) {
                UtilityClass.errorAlerts(4);
                return;
            }
            Inventory.updatePart(new OutsourcedPart(partId, partName, partPrice, inv, min, max, companyName));
            UtilityClass.BackToMainScreen(saveButton);
        }
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
        isInHouse=true;
        partIdField.setText("Automatically Generated");
        partIdField.setDisable(true);
    }
}
