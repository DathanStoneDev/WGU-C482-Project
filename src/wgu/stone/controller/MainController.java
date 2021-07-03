package wgu.stone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {




    public void addPartButtonSelected (ActionEvent event) throws IOException {
        Parent addPartInHouse = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddPartInHouse.fxml"));
        Scene addPartInHouseScene = new Scene(addPartInHouse);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartInHouseScene);
        window.show();
    }
    public void modifyPartButtonSelected (ActionEvent event) throws IOException {
        Parent modifyPartInHouse = FXMLLoader.load(getClass().getResource("/wgu/stone/view/AddPartInHouse.fxml"));
        Scene modifyPartInHouseScene = new Scene(modifyPartInHouse);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartInHouseScene);
        window.show();
    }
    public void addProductButtonSelected (ActionEvent event) throws IOException {
        Parent addProduct = FXMLLoader.load(getClass().getResource("/wgu/stone/view/ModifyProductForm.fxml"));
        Scene addProductScene = new Scene(addProduct);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    public void modifyProductButtonSelected (ActionEvent event) throws IOException {
        Parent modifyProduct = FXMLLoader.load(getClass().getResource("/wgu/stone/view/ModifyPartInHouse.fxml"));
        Scene modifyProductScene = new Scene(modifyProduct);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle rb) {

    }
}
