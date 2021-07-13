package wgu.stone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import wgu.stone.model.Inventory;
import wgu.stone.model.Part;
import wgu.stone.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField productPriceField;
    @FXML private TextField productStockField;
    @FXML private TextField minProductField;
    @FXML private TextField maxProductField;

    @FXML Button cancelButton;
    @FXML Button saveButton;

    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    @FXML private TextField partSearchField;
    @FXML private Label searchPartConfirmationLabel;

    @FXML private TableView<Part> associatedTableView;
    @FXML private TableColumn<Part, Integer> associatedIdColumn;
    @FXML private TableColumn<Part, String> associatedNameColumn;
    @FXML private TableColumn<Part, Integer> associatedInvColumn;
    @FXML private TableColumn<Part, Double> associatedPriceColumn;

    private Product selectedProduct;
    private ObservableList<Part> holdParts = FXCollections.observableArrayList();




    public void saveModifiedProduct() throws IOException {
        int productId = Integer.parseInt(productIdField.getText());
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

        Product product = new Product();
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



        Inventory.updateProduct(product);


        UtilityClass.BackToMainScreen(saveButton);
    }

    public void initData(Product product) {
        selectedProduct = product;
        productIdField.setText(Integer.toString(selectedProduct.getProductId()));
        productNameField.setText((selectedProduct.getProductName()));
        productStockField.setText(Integer.toString(selectedProduct.getProductStock()));
        productPriceField.setText(Double.toString(selectedProduct.getProductPrice()));
        minProductField.setText(Integer.toString(selectedProduct.getMinProduct()));
        maxProductField.setText(Integer.toString(selectedProduct.getMaxProduct()));
        //When the modify button is hit, this initializes the product info. We want to holdParts temp list to be populated
        //by the products associatedParts.
        holdParts = product.getAssociatedParts();

        //then show the parts on the bottom tableview.
        associatedTableView.setItems(holdParts);
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
        searchPartConfirmationLabel.setText("");


    }


    public void addAssociatedPart() {
        //Grab a part
        Part part = partTableView.getSelectionModel().getSelectedItem();

        //add the to observable list
        holdParts.add(part);
        //update the view.
        associatedTableView.setItems(holdParts);
    }



    public void removeAssociatedPart() {
        Part part = associatedTableView.getSelectionModel().getSelectedItem();
        holdParts.remove(part);
        selectedProduct.deleteAssociatedPart(part);

    }


    public void cancelButton() throws IOException {
        UtilityClass.BackToMainScreen(cancelButton);
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
}
