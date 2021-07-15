package wgu.stone.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * UtilityClass provides static methods that are commonly seen throughout the application.
 */
public final class UtilityClass {

    /**
     * Private constructor prevents instantiation of this class. This class is strictly for providing static methods that can be used across all classes.
     */
    private UtilityClass () {
        throw new UnsupportedOperationException("Cannot instantiate the Utility Class");
    }

    /**
     * General method for classes to get back to the main screen, through a cancel button or a save button when adding
     * or modifying parts and products.
     * @param button
     * @throws IOException
     */
    public static void BackToMainScreen(Button button) throws IOException {
        Parent returnHome = FXMLLoader.load(UtilityClass.class.getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }

    /**
     * Switch that contains all the possible alerts that can be thrown if errors occur during validation of data.
     * @param errorSelection
     */
    public static void errorAlerts(int errorSelection) {

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
            case 10: alert.setTitle("Alert");
                alert.setContentText("Selector is empty. Please make a selection before modifying");
                alert.showAndWait();
                break;
            case 11: alert.setTitle("Alert");
                alert.setContentText("No Product has been selected.");
                alert.showAndWait();
                break;
            case 12: alert.setTitle("Alert");
                alert.setContentText("You have not selected a part to add");
                alert.showAndWait();
                break;
            default: alert.setTitle("Alert");
                     alert.setContentText("Error!");
                     alert.showAndWait();
                     break;
        }
    }
}
