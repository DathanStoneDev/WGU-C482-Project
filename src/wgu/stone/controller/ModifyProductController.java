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

public class ModifyProductController implements Initializable {

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productStockField;
    @FXML
    private TextField minProductField;
    @FXML
    private TextField maxProductField;

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
    private TextField partSearchField;

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

    private Product selectedProduct;
    private ObservableList<Part> holdParts = FXCollections.observableArrayList();



    public void saveModifiedProduct(ActionEvent event) throws IOException {

        int productId = Integer.parseInt(productIdField.getText());
        String productName = productNameField.getText();
        int productInv = Integer.parseInt(productStockField.getText());
        double productPrice = Double.parseDouble(productPriceField.getText());
        int minProduct = Integer.parseInt(minProductField.getText());
        int maxProduct = Integer.parseInt(maxProductField.getText());

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductStock(productInv);
        product.setMinProduct(minProduct);
        product.setMaxProduct(maxProduct);

        for(Part associated : holdParts) {
            product.addAssociatedPart(associated);
        }
        Inventory.updateProduct(product);
        System.out.println(product.getAssociatedParts());

        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
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


    }


    public void addAssociatedPart() {
        //Grab a part
        Part part = partTableView.getSelectionModel().getSelectedItem();

        //add the to observable list
        holdParts.add(part);
        //update the view.
        associatedTableView.setItems(holdParts);
    }


    //bug where button has to be clicked twice to remove
    public void removeAssociatedPart() {
        Part part = associatedTableView.getSelectionModel().getSelectedItem();
        holdParts.remove(part);



    }


    public void cancelButton(ActionEvent event) throws IOException {
        Parent returnHome = FXMLLoader.load(getClass().getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();

    }


    public void searchParts() {

        String q = partSearchField.getText();
        try {
            int id = Integer.parseInt(q);
            partTableView.getSelectionModel().select(Inventory.lookupPartbyId(id));
        } catch (NumberFormatException e) {
            partTableView.getSelectionModel().select(Inventory.lookupPart(q));
        }
    }
}
