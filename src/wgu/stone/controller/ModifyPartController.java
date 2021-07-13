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
    @FXML
    private ToggleGroup addPartGroup;

    /**
     * Save button: Saves a completed part entry.
     * Cancel button: Cancels adding a part.
     */
    @FXML
    private Button cancelButton;
    @FXML private Button saveButton;

    /**
     * Label that changes the partMachineIdField label to "MachineID" if In-House or "Company" if Outsourced.
     */
    @FXML
    private Label labelChange;

    /**
     * Boolean value that is referenced in the buttonInHouse and buttonOutsource methods.
     * If true, the part is an In-House part, else the part is an Outsourced part.
     */
    boolean isInHouse;

    /**
     * Part object that will hold the data from a selected part that will be modified.
     */
    private Part selectedPart;

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
     * saveModifiedPart method checks data validity and then updates the part.
     * @throws IOException
     */
    @FXML
    public void saveModifiedPart () throws IOException{
        //grabs the ID that was auto-populated.
        int partId = Integer.parseInt(partIdField.getText());
        String partName = partNameField.getText();

        //checks for an empty name field.
        if(partName.isEmpty()) {
            UtilityClass.errorAlerts(4);
            return;
        }

        //checks for Integer type in the inventory field.
        int inv;
        try {
            inv = Integer.parseInt(partInvField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(6);
            return;
        }

        //checks for Double type in the price field.
        double partPrice;
        try {
            partPrice = Double.parseDouble(partPriceField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(5);
            return;
        }

        //checks for Integer type in the min field.
        int min;
        try {
            min = Integer.parseInt(partMinField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(7);
            return;
        }

        //checks for Integer type in tha max field.
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

        //checks if the inventory is between the min and the max.
        if(inv < min || inv > max) {
            UtilityClass.errorAlerts(2);
            return;
        }
            //checks if the part is an in-house part.
            if (isInHouse) {
                //checks for an Integer type in the machineId field.
                int machineId;
                try {
                    machineId = Integer.parseInt(partMachineIdField.getText());
                } catch (NumberFormatException e){
                    UtilityClass.errorAlerts(9);
                    return;
                }

                //updates the part in the inventory.
                Inventory.updatePart(new InHousePart(partId, partName, partPrice, inv, min, max, machineId));

                //takes the user back to the main screen after a successful save.
                UtilityClass.BackToMainScreen(saveButton);


            }
            //if isInHouse is false, the part is an outsourced part.
            else {
                //checks for an empty string field.
                String companyName = partMachineIdField.getText();
                if(companyName.isEmpty()) {
                    UtilityClass.errorAlerts(4);
                    return;
                }

                //updates the part in the inventory.
                Inventory.updatePart(new OutsourcedPart(partId, partName, partPrice, inv, min, max, companyName));

                //takes the user back to the main screen after a successful save.
                UtilityClass.BackToMainScreen(saveButton);

            }

    }

    /**
     * Initializes the selected part data sent from the Main Controller.
     * @param part
     */
    public void initData(Part part) {
        selectedPart = part;
        partIdField.setText(Integer.toString(selectedPart.getId()));
        partNameField.setText((selectedPart.getName()));
        partInvField.setText(Integer.toString(selectedPart.getStock()));
        partPriceField.setText(Double.toString(selectedPart.getPrice()));
        partMaxField.setText(Integer.toString(selectedPart.getMax()));
        partMinField.setText(Integer.toString(selectedPart.getMin()));

        //sets the in-house button to true.
        if(part instanceof InHousePart) {
            InHousePart inHouse = (InHousePart) part;
            partMachineIdField.setText(Integer.toString(inHouse.getMachineId()));
            labelChange.setText("Machine ID");
            inHousePartButton.setSelected(true);
            isInHouse = true;
        }
        //sets the outsourced button to true.
        else {
            OutsourcedPart outSource = (OutsourcedPart) part;
            partMachineIdField.setText((outSource.getCompanyName()));
            labelChange.setText("Company");
            outsourcePartButton.setSelected(true);
            isInHouse = false;
        }
    }

    /**
     * sends the user back to the main screen after clicking the cancel button.
     */
    @FXML
    public void cancelButton() throws IOException {
        UtilityClass.BackToMainScreen(cancelButton);
    }

    /**
     * initializes the radio buttons and their group.
     * disables the id field so that it cannot be modified.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartGroup = new ToggleGroup();
        inHousePartButton.setToggleGroup(addPartGroup);
        outsourcePartButton.setToggleGroup(addPartGroup);
        partIdField.setDisable(true);
    }
}



