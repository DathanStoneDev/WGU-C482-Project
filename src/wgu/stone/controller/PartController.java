package wgu.stone.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartGroup = new ToggleGroup();
        inHousePartButton.setToggleGroup(addPartGroup);
        outsourcePartButton.setToggleGroup(addPartGroup);
    }
}
