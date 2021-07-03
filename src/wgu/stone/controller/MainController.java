package wgu.stone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wgu.stone.model.InHousePart;
import wgu.stone.model.Part;
import wgu.stone.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    //Parts Tableview properties.
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

    //product Tableview properties
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInvColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;



    //When the "add" button is selected on the parts tableview, this goes to the scene AddPartInHouse.
    public void addPartButtonSelected (ActionEvent event) throws IOException {
        Parent addPartInHouse = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddPart.fxml"));
        Scene addPartInHouseScene = new Scene(addPartInHouse);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartInHouseScene);
        window.show();
    }
    //When the "Modify" button is selected on the parts tableview, this goes to the scene AddPartInHouse.
    public void modifyPartButtonSelected (ActionEvent event) throws IOException {
        Parent modifyPartInHouse = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddPart.fxml"));
        Scene modifyPartInHouseScene = new Scene(modifyPartInHouse);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartInHouseScene);
        window.show();
    }
    //When the "add" button is selected on the products tableview, this goes to the scene AddProductForm
    public void addProductButtonSelected (ActionEvent event) throws IOException {
        Parent addProduct = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddProduct.fxml"));
        Scene addProductScene = new Scene(addProduct);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    //When the "Modify" button is selected on the products tableview, this goes to the scene ModifyProductForm.
    public void modifyProductButtonSelected (ActionEvent event) throws IOException {
        Parent modifyProduct = FXMLLoader.load(getClass().getResource("/wgu/stone/view/ModifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProduct);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.show();
    }

    //Initializes the controller
    public void initialize(URL url, ResourceBundle rb) {

        //Columns initialized in Parts tableview.
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        //method that sets the items in the part tableview to be viewed.
        partTableView.setItems(getParts());

        //Columns initialized in Products tableview
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        //method that sets the items in the product table view to be viewed.
        productTableView.setItems(getProducts());
    }


    //Create dummy data here for parts
    public ObservableList<Part> getParts () {
        //Create list
        ObservableList<Part> parts = FXCollections.observableArrayList();
        //add dummy part data
        parts.add(new InHousePart(1, "Wrench", 6.78, 6, 3, 78, 786));

        return parts;
    }
    //Create dummy data here for products
    public ObservableList<Product> getProducts () {
        //Create list
        ObservableList<Product> products = FXCollections.observableArrayList();
        //add dummy part data
        products.add(new Product(1, "bestStuff", 50.35, 6, 3, 78));

        return products;
    }
}
