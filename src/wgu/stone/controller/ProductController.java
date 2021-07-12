package wgu.stone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wgu.stone.model.Inventory;
import wgu.stone.model.Part;
import wgu.stone.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    //Part tableview fields
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    @FXML private TextField partSearchField;

    @FXML private Button cancelButton;

    //Associated Parts tableview fields
    @FXML private TableView<Part> associatedTableView;
    @FXML private TableColumn<Part, Integer> associatedIdColumn;
    @FXML private TableColumn<Part, String> associatedNameColumn;
    @FXML private TableColumn<Part, Integer> associatedInvColumn;
    @FXML private TableColumn<Part, Double> associatedPriceColumn;
    @FXML private Label searchPartConfirmationLabel;

    //Product Text fields
    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField productPriceField;
    @FXML private TextField productStockField;
    @FXML private TextField minProductField;
    @FXML private TextField maxProductField;

    Product product = new Product();
    ObservableList<Part> holdParts = FXCollections.observableArrayList();

    @FXML
    public void addAssociatedPart() {
        //Grab a part
        Part part = partTableView.getSelectionModel().getSelectedItem();

        //add the to temporary observable list
        holdParts.add(part);
    }

    //removes part from temporary observable list
    @FXML
    public void removeAssociatedPart() {
        Part part = associatedTableView.getSelectionModel().getSelectedItem();
        holdParts.remove(part);
    }

    //Saves a product
    @FXML
    public void saveProduct(ActionEvent event) throws IOException {

        String productName = productNameField.getText();
        if(productName.isEmpty()) {
            UtilityClass.errorAlerts(4);
            return;
        }

        int productInv;
        try {
            productInv = Integer.parseInt(productStockField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(6);
            return;
        }
        double productPrice;
        try {
            productPrice = Double.parseDouble(productPriceField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(5);
            return;
        }

        int minProduct;
        try {
            minProduct = Integer.parseInt(minProductField.getText());
        } catch (NumberFormatException e) {
            UtilityClass.errorAlerts(7);
            return;
        }

        int maxProduct;
        try {
            maxProduct = Integer.parseInt(maxProductField.getText());
        } catch (NumberFormatException e){
            UtilityClass.errorAlerts(8);
            return;
        }

        if(minProduct > maxProduct) {
            UtilityClass.errorAlerts(1);
            return;
        }

        if(productInv < minProduct || productInv > maxProduct) {
            UtilityClass.errorAlerts(2);
            return;
        }


        int productId = 0;
        for(Product i : Inventory.getAllProducts()) {
            if(i.getProductId() >= productId) {
                productId = i.getProductId() + 1;
            }
        }


        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductStock(productInv);
        product.setMinProduct(minProduct);
        product.setMaxProduct(maxProduct);

        // adds parts from holdParts list to the product associatedParts list
        for(Part associated : holdParts) {
            product.addAssociatedPart(associated);
        }

        Inventory.addProduct(product);


        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }

    //cancel button that goes back to the main screen
    @FXML
    public void cancelButton() throws IOException {
        UtilityClass.cancelBackToMainScreen(cancelButton);
    }

    @FXML
    public void searchParts() {

        String q = partSearchField.getText();

        if(q.isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
            searchPartConfirmationLabel.setText("");
        } else {
            try {
                int id = Integer.parseInt(q);
                if(Inventory.lookupPartbyId(id) == null) {
                    searchPartConfirmationLabel.setText("No ID by the name");
                } else {
                    partTableView.getSelectionModel().select(Inventory.lookupPartbyId(id));
                    searchPartConfirmationLabel.setText("ID Found");
                }
            } catch (NumberFormatException e) {
                partTableView.setItems(Inventory.lookupPart(q));
                if(partTableView.getItems().isEmpty()) {
                    searchPartConfirmationLabel.setText("Could not find a match");
                } else {
                    searchPartConfirmationLabel.setText("Here is your part");
                }
            } finally {
                partSearchField.clear();
            }
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        associatedTableView.setItems(holdParts);
        associatedIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        associatedNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        productIdField.setDisable(true);
        productIdField.setText("Automatically Generated");
        searchPartConfirmationLabel.setText("");
    }
}
