package wgu.stone.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wgu.stone.model.Inventory;
import wgu.stone.model.Part;
import wgu.stone.model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInvColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableView<Part> associatedTableView;
    @FXML
    private TableColumn<Part, Integer> associatedIdColumn;
    @FXML
    private TableColumn<Part, String> associatedNameColumn;
    @FXML
    private TableColumn<Part, Integer> associatedInvColumn;
    @FXML
    private TableColumn<Part, Double> associatedPriceColumn;



//associatedParts.add(part);

    @FXML
    public void addAssociatedPart() {
        //Grab a part
        Part part = partTableView.getSelectionModel().getSelectedItem();


        //add the part into the list via add associated part from product class

        //show list via get all

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
    }
}
