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

        int inv = 0;
        try {
            inv = Integer.parseInt(partInvField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(5);
            return;
        }
        double partPrice = 0.0;
        try {
            partPrice = Double.parseDouble(partPriceField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(4);
            return;
        }

        int min = 0;
        try {
            min = Integer.parseInt(partMinField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(4);
            return;
        }

        int max = 0;
        try {
            max = Integer.parseInt(partMaxField.getText());
        } catch (NumberFormatException e){
            UtilityClass.errorAlerts(4);
            return;
        }





            if (isInHouse) {
                int machineId = 0;
                try {
                    machineId = Integer.parseInt(partMachineIdField.getText());
                } catch (NumberFormatException e){
                    UtilityClass.errorAlerts(5);
                    return;
                }
                Inventory.updatePart(new InHousePart(partId, partName, partPrice, inv, min, max, machineId));


                Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
                Scene returnHomeScene = new Scene(returnHome);
                Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
                window.setScene(returnHomeScene);
                window.show();
            }


        else {
                String companyName = "";
                try {
                    companyName = partMachineIdField.getText();
                } catch (NumberFormatException e) {
                    UtilityClass.errorAlerts(5);
                    return;
                }
                Inventory.updatePart(new OutsourcedPart(partId, partName, partPrice, inv, min, max, companyName));

                Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
                Scene returnHomeScene = new Scene(returnHome);
                Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
                window.setScene(returnHomeScene);
                window.show();
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
        UtilityClass.cancelBackToMainScreen(cancelButton);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartGroup = new ToggleGroup();
        inHousePartButton.setToggleGroup(addPartGroup);
        outsourcePartButton.setToggleGroup(addPartGroup);
        partIdField.setDisable(true);
    }
}



