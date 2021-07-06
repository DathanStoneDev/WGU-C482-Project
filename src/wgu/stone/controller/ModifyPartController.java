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

    private Part selectedPart;
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
    @FXML
    public void saveModifiedPart (ActionEvent event) throws IOException {
        int id = Integer.parseInt(partIdField.getText());
        String name = partNameField.getText();
        int inv = Integer.parseInt(partInvField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int min = Integer.parseInt(partMinField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        String machineId = (partMachineIdField.getText());


        if(isInHouse) {

            InHousePart modifiedInHouse = new InHousePart(id, name, price, inv, min, max, Integer.parseInt(machineId));
            modifiedInHouse.setId(id);
            modifiedInHouse.setName(name);
            modifiedInHouse.setPrice(price);
            modifiedInHouse.setStock(inv);
            modifiedInHouse.setMin(min);
            modifiedInHouse.setMax(max);
            modifiedInHouse.setMachineId(Integer.parseInt(machineId));

            //need the updatePart method from inventory to finalize.
            Inventory.updatePart(modifiedInHouse);
        } else {
            OutsourcedPart modifiedOutsource = new OutsourcedPart(id, name, price, inv, min, max, machineId);
            modifiedOutsource.setId(id);
            modifiedOutsource.setName(name);
            modifiedOutsource.setPrice(price);
            modifiedOutsource.setStock(inv);
            modifiedOutsource.setMin(min);
            modifiedOutsource.setMax(max);
            modifiedOutsource.setCompanyName(machineId);

            Inventory.updatePart(modifiedOutsource);
            System.out.println(modifiedOutsource.getCompanyName());
        }

        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }

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
            isInHouse = true;
            inHousePartButton.setSelected(true);
        }
        else if (part instanceof OutsourcedPart) {
            OutsourcedPart outSource = (OutsourcedPart) part;
            partMachineIdField.setText((outSource.getCompanyName()));
            labelChange.setText("Company");
            isInHouse = false;
            outsourcePartButton.setSelected(true);
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
    }
}
