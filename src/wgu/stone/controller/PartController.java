package wgu.stone.controller;

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

/**
 * PartController class controls the creation of parts and is linked to the AddPart FXML File.
 */
public class PartController implements Initializable {

    /**
     * TextFields that are defined for each property that defines a part.
     */
    @FXML private TextField partIdField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxField;
    @FXML private TextField partMinField;
    @FXML private TextField partMachineIdField;

    /**
     * Radio buttons that control whether a part is a In-House part of Outsourced part.
     */
    @FXML private RadioButton inHousePartButton;
    @FXML private RadioButton outsourcePartButton;

    /**
     * Toggle group contains the radio buttons.
     */
    @FXML private ToggleGroup addPartGroup;

    /**
     * Label that changes the partMachineIdField label to "MachineID" if In-House or "Company" if Outsourced.
     */
    @FXML private Label labelChange;

    /**
     * Boolean value that is referenced in the buttonInHouse and buttonOutsource methods.
     * If true, the part is an In-House part, else the part is an Outsourced part.
     */
    boolean isInHouse;

    /**
     * Save button: Saves a completed part entry.
     * Cancel button: Cancels adding a part.
     */
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    /**
     * Method linked to the InHouse radio button.
     * When pressed, isInHouse is true, and the partMachineIdField label is set to "Machine ID".
     */
    @FXML
    private void buttonInHouse(){
        labelChange.setText("Machine ID");
        isInHouse = true;
    }

    /**
     * Method linked to the Outsource radio button.
     * When pressed, isInHouse is false, and the partMachineIdField label is set to "Company".
     */
    @FXML
    private void buttonOutsource(){
        labelChange.setText("Company");
        isInHouse = false;

    }

    /**
     * Method that saves a part. It starts by checking all part fields for valid data.
     * If any invalid data is presented or exceptions are thrown, error alerts are generated.
     * @throws IOException
     */
    @FXML
    public void savePart() throws IOException{

        //checks for an empty name field.
        String partName = partNameField.getText();
        if(partName.isEmpty()) {
            UtilityClass.errorAlerts(4);
            return;
        }

        //checks for an integer type in the inventory field.
        int inv;
        try {
            inv = Integer.parseInt(partInvField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(6);
            return;
        }

        //checks for a double type in the price field.
        //if an integer is placed into the field, it is converted to a double.
        double partPrice;
        try {
            partPrice = Double.parseDouble(partPriceField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(5);
            return;
        }

        //checks for an integer type in the min field.
        int min;
        try {
            min = Integer.parseInt(partMinField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(7);
            return;
        }

        //checks for an integer type in the max field.
        int max;
        try {
            max = Integer.parseInt(partMaxField.getText());
        } catch (NumberFormatException e){
            UtilityClass.errorAlerts(8);
            return;
        }

        //checks if the min is greater than the max.
        if(min > max) {
            UtilityClass.errorAlerts(1);
            return;
        }

        //checks if the inventory level is between the min and the max.
        if(inv < min || inv > max) {
            UtilityClass.errorAlerts(2);
            return;
        }

        //auto-generates an ID for each part.
        int partId = 0;
        for(Part i : Inventory.getAllParts()) {
            if(i.getId() >= partId) {
                partId = i.getId() + 1;
            }
        }

        //checks if the part is an in-house part.

        if(isInHouse){
            //checks for an integer type for the machineId Field.
            int machineId;
            try {
                machineId = Integer.parseInt(partMachineIdField.getText());
            } catch (NumberFormatException e){
                UtilityClass.errorAlerts(9);
                return;
            }

            //adds the part to the inventory.
            Inventory.addPart(new InHousePart(partId, partName, partPrice, inv, min, max, machineId));

            //sends the user back to the main screen after a save has been successful.
            UtilityClass.BackToMainScreen(saveButton);

            //if isInHouse is false, part is outsourced.
        } else {
            //checks the partMachineIdField for an empty string.
            String companyName = partMachineIdField.getText();
            if(companyName.isEmpty()) {
                UtilityClass.errorAlerts(4);
                return;
            }

            //adds part to the inventory.
            Inventory.updatePart(new OutsourcedPart(partId, partName, partPrice, inv, min, max, companyName));

            //sends the user back to the main screen after a save has been successful.
            UtilityClass.BackToMainScreen(saveButton);
        }
    }

    /**
     * Sends user back to the main screen if the cancel button is pressed.
     * @throws IOException
     */
    @FXML
    public void cancelButton() throws IOException {
        UtilityClass.BackToMainScreen(cancelButton);
    }

    /**
     * Initializes the radio buttons inside their toggle group.
     * Disables the id field since the id is auto-generated.
     * starts with the in-house radio button selected.
     * @param location
     * @param resources
     */
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
