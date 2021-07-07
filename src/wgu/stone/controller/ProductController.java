package wgu.stone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    //Associated Parts tableview fields
    @FXML private TableView<Part> associatedTableView;
    @FXML private TableColumn<Part, Integer> associatedIdColumn;
    @FXML private TableColumn<Part, String> associatedNameColumn;
    @FXML private TableColumn<Part, Integer> associatedInvColumn;
    @FXML private TableColumn<Part, Double> associatedPriceColumn;

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
        int productInv = Integer.parseInt(productStockField.getText());
        double productPrice = Double.parseDouble(productPriceField.getText());
        int minProduct = Integer.parseInt(minProductField.getText());
        int maxProduct = Integer.parseInt(maxProductField.getText());

        //Automatic id generator
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
    public void cancelButton(ActionEvent event) throws IOException {
        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();

    }

    @FXML
    public void searchParts() {

        String q = partSearchField.getText();
        try {
            int id = Integer.parseInt(q);
            partTableView.getSelectionModel().select((Inventory.lookupPartbyId(id)));
        } catch (NumberFormatException e) {
            partTableView.setItems(Inventory.lookupPart(q));
        }
        partSearchField.clear();
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
    }
}
