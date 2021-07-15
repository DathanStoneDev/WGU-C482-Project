package wgu.stone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wgu.stone.model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Main Controller class that shows the initial startup screen.
 * Controls the flow to the scenes.
 */
public class MainController implements Initializable {


    /**
     * Fields of the Parts Table.
     * TableView is the table that holds the data.
     * TableColumns for id, name, inventory and price of parts.
     * TextField for the searchbar.
     */
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    @FXML private TextField partSearchField;

    /**
     * Fields of the Products Table
     * TableView is the table that holds the data.
     * TableColumns for id, name, inventory and price of products
     * TextField for the searchbar.
     */
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productIdColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInvColumn;
    @FXML private TableColumn<Product, Double> productPriceColumn;
    @FXML private TextField productSearchField;

    /**
     * Labels that changes to show an error or confirmation message when searching.
     */
    @FXML private Label searchProductConfirmationLabel;
    @FXML private Label searchPartConfirmationLabel;

    /**
     * Field for the exit button that allows a user to exit the application.
     */
    @FXML private Button exitAppButton;


    /**
     * Button labeled "Add" on the part side used to add a part to the Parts tableview.
     * @param event
     * @throws IOException
     */
    public void addPartButtonSelected(ActionEvent event) throws IOException {
        Parent addPartInHouse = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddPart.fxml"));
        Scene addPartInHouseScene = new Scene(addPartInHouse);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartInHouseScene);
        window.show();
    }

    /**
     * Button labeled "Modify" on the part side used to go to the modify scene of a selected part.
     * @param event
     * @throws IOException
     */
    public void modifyPartButtonSelected(ActionEvent event) throws IOException {
        //loads the ModifyPart FXML File
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/wgu/stone/view/ModifyPart.fxml"));
        Parent modifyPart = loader.load();


        Scene modifyPartScene = new Scene(modifyPart);

        //Loads a selected part's information to the Modify Part Controller.
        //If nothing is selected and the modify button is clicked, An alert pops up.
        ModifyPartController controller = loader.getController();
        try {
            controller.initData(partTableView.getSelectionModel().getSelectedItem());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(modifyPartScene);
            window.show();
        } catch (NullPointerException e) {
            UtilityClass.errorAlerts(10);
        }
    }

    /**
     * Button labeled "Add" on the product side and routes to the AddProduct scene.
     * @param event
     * @throws IOException
     */
    public void addProductButtonSelected(ActionEvent event) throws IOException {
        Parent addProduct = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddProduct.fxml"));
        Scene addProductScene = new Scene(addProduct);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    /**
     * Button labeled "Modify" on the product side used to go to the modify scene of a selected product.
     * @param event
     * @throws IOException
     */
    public void modifyProductButtonSelected(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/wgu/stone/view/ModifyProduct.fxml"));
        Parent modifyProduct = loader.load();


        Scene modifyProductScene = new Scene(modifyProduct);

        ModifyProductController controller = loader.getController();
        try {
            controller.initData(productTableView.getSelectionModel().getSelectedItem());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        } catch (NullPointerException e) {
            UtilityClass.errorAlerts(10);
        }
    }

    /**
     * Linked to the exit button on the page. Closes the application.
     */
    @FXML
    public void exitApplication() {
        Stage window = (Stage) exitAppButton.getScene().getWindow();
        window.close();
    }

    /**
     * Linked to delete button for parts. Confirmation alert pops up for a selected part.
     * User must confirm if they want to delete the part.
     */
    @FXML
    public void deleteButtonPressed() {
        Part part = partTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to delete the part?");
        Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(part);
            }
    }

    /**
     * Linked to delete button for products. Confirmation alert pops up for a selected product if no associated
     * parts present. If associated parts are with the product, an error alert pops up preventing the delete action.
     */
    @FXML
    public void deleteButtonPressedProduct() {
        Product product = productTableView.getSelectionModel().getSelectedItem();
        try {
            if(product.getAssociatedParts().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Are you sure you want to delete the product?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(product);
                }
            } else {
                UtilityClass.errorAlerts(3);
            }
        } catch (NullPointerException e) {
            UtilityClass.errorAlerts(11);
        }
    }

    /**
     * Method for confirmation of finding parts through the search function.
     * Changes a label in the UI to tell the user if a part was found or not based on ID or Name lookup.
     * The finally block clears the search field. Click the search button when empty to get a list of all parts.
     * Search button must be pressed when search field is empty to refresh the list.
     */
    @FXML
    public void searchParts() {

        String q = partSearchField.getText();

        if(q.isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
            searchPartConfirmationLabel.setText("");
        } else {
            try {
                int id = Integer.parseInt(q);
                if(Inventory.lookupPartById(id) == null) {
                    searchPartConfirmationLabel.setText("No ID by the name");
                } else {
                    partTableView.getSelectionModel().select(Inventory.lookupPartById(id));
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

    /**
     * Method for confirmation of finding parts through the search function.
     * Changes a label in the UI to tell the user if a product was found or not based on ID or Name lookup.
     * The finally block clears the search field. Click the search button when empty to get a list of all products.
     * Search button must be pressed when search field is empty to refresh the list.
     */
    @FXML
    public void searchProducts() {

        String q = productSearchField.getText();
        try {
            int id = Integer.parseInt(q);
            productTableView.getSelectionModel().select((Inventory.lookupProductById(id)));
            if(Inventory.lookupProductById(id) == null) {
                searchProductConfirmationLabel.setText("No ID by the name");
            } else {
                productTableView.getSelectionModel().select(Inventory.lookupProductById(id));
                searchProductConfirmationLabel.setText("ID Found");
            }
        } catch (NumberFormatException n) {
            productTableView.setItems((Inventory.lookupProductByName(q)));
            if(productTableView.getItems().isEmpty()) {
                searchProductConfirmationLabel.setText("Could not find a match");
            } else {
                searchProductConfirmationLabel.setText("Here is your part");
            }
        } finally {
            productSearchField.clear();
        }
    }


    /**
     * Initializes the tables in the main scene.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Columns initialized in Parts tableview.
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));



        //Columns initialized in Products tableview.
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        //labels initialized to be empty. Changed selection mode to multiple.
        searchPartConfirmationLabel.setText("");
        partTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
