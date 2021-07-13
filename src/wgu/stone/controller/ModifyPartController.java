package wgu.stone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import wgu.stone.model.InHousePart;
import wgu.stone.model.Inventory;
import wgu.stone.model.OutsourcedPart;
import wgu.stone.model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModifyPartController implements Initializable {

    //Part Text Fields
    @FXML private TextField partIdField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxField;
    @FXML private TextField partMinField;
    @FXML private TextField partMachineIdField;

    //Radio buttons
    @FXML private RadioButton inHousePartButton;
    @FXML private RadioButton outsourcePartButton;

    //Toggle Group that the radio buttons belong to
    @FXML
    private ToggleGroup addPartGroup;

    @FXML
    private Button cancelButton;
    @FXML private Button saveButton;

    //label that changes from "Machine ID" to "Company Name" based on radio button selection.
    @FXML
    private Label labelChange;


    boolean isInHouse;

    private Part selectedPart;






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




    @FXML
    public void saveModifiedPart (ActionEvent event) throws IOException{
        int partId = Integer.parseInt(partIdField.getText());
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

            if (isInHouse) {
                int machineId;
                try {
                    machineId = Integer.parseInt(partMachineIdField.getText());
                } catch (NumberFormatException e){
                    UtilityClass.errorAlerts(9);
                    return;
                }
                Inventory.updatePart(new InHousePart(partId, partName, partPrice, inv, min, max, machineId));
                UtilityClass.BackToMainScreen(saveButton);


            }
            else {
                String companyName = partMachineIdField.getText();
                if(companyName.isEmpty()) {
                    UtilityClass.errorAlerts(4);
                    return;
                }
                Inventory.updatePart(new OutsourcedPart(partId, partName, partPrice, inv, min, max, companyName));
                UtilityClass.BackToMainScreen(saveButton);

            }

    }

    //Data sent from the Main Controller when the Modify Button for parts is selected.
    public void initData(Part part) {
        selectedPart = part;
        partIdField.setText(Integer.toString(selectedPart.getId()));
        partNameField.setText((selectedPart.getName()));
        partInvField.setText(Integer.toString(selectedPart.getStock()));
        partPriceField.setText(Double.toString(selectedPart.getPrice()));
        partMaxField.setText(Integer.toString(selectedPart.getMax()));
        partMinField.setText(Integer.toString(selectedPart.getMin()));

        if(part instanceof InHousePart) {
            InHousePart inHouse = (InHousePart) part;
            partMachineIdField.setText(Integer.toString(inHouse.getMachineId()));
            labelChange.setText("Machine ID");
            inHousePartButton.setSelected(true);
            isInHouse = true;
        }
        else {
            OutsourcedPart outSource = (OutsourcedPart) part;
            partMachineIdField.setText((outSource.getCompanyName()));
            labelChange.setText("Company");
            outsourcePartButton.setSelected(true);
            isInHouse = false;
        }
    }

    @FXML
    public void cancelButton() throws IOException {
        UtilityClass.BackToMainScreen(cancelButton);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartGroup = new ToggleGroup();
        inHousePartButton.setToggleGroup(addPartGroup);
        outsourcePartButton.setToggleGroup(addPartGroup);
        partIdField.setDisable(true);
    }
}



