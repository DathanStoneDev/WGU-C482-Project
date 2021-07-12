package wgu.stone.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public final class UtilityClass {

    //prevents instantiation of this class. This class is strictly for providing static methods that can be used across all classes.
    private UtilityClass () {
        throw new UnsupportedOperationException("Cannot instantiate the Utility Class");
    }

    //edit name to just backToMainScreen - this can be used by the cancel button and when the save buttons are hit
    public static void cancelBackToMainScreen(Button button) throws IOException {
        Parent returnHome = FXMLLoader.load(UtilityClass.class.getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }

    public static void errorAlerts(int errorSelection) {
        //need for min < max and Inv needs to be between min and max
        //alert for not deleting product with part associated
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch(errorSelection) {
            case 1: alert.setTitle("Alert");
                    alert.setContentText("The min must be less than the max.");
                    alert.showAndWait();
                    break;
            case 2: alert.setTitle("Alert");
                    alert.setContentText("The inventory must be between the min and the max.");
                    alert.showAndWait();
                    break;
            case 3: alert.setTitle("Alert");
                    alert.setContentText("You cannot delete a product with associated parts." +
                            " Please delete the associated parts first.");
                    alert.showAndWait();
                    break;
            case 4: alert.setTitle("Alert");
                    alert.setContentText("Field cannot be empty! Please enter a valid value.");
                    alert.showAndWait();
                break;
            case 5: alert.setTitle("Alert");
                    alert.setContentText("Price must be in decimal format");
                    alert.showAndWait();
                    break;
            case 6: alert.setTitle("Alert");
                alert.setContentText("Inventory must be in integer format");
                alert.showAndWait();
                break;
            case 7: alert.setTitle("Alert");
                alert.setContentText("Minimum must be in integer format");
                alert.showAndWait();
                break;
            case 8: alert.setTitle("Alert");
                alert.setContentText("Maximum must be in integer format");
                alert.showAndWait();
                break;
            case 9: alert.setTitle("Alert");
                alert.setContentText("Machine ID must be in integer format");
                alert.showAndWait();
                break;
            default: alert.setTitle("Alert");
                     alert.setContentText("Error!");
                     alert.showAndWait();
                     break;
        }
    }
}
