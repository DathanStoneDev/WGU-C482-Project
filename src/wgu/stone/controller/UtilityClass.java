package wgu.stone.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public final class UtilityClass {

    //prevents instantiation of this class. This class is strictly for providing static methods that can be used across all classes.
    private UtilityClass () {
        throw new UnsupportedOperationException("Cannot instantiate the Utility Class");
    }

    public static void cancelBackToMainScreen(Button button) throws IOException {
        Parent returnHome = FXMLLoader.load(UtilityClass.class.getResource("/wgu/stone/view/MainWindow.fxml"));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
    }
}
