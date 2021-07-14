package wgu.stone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wgu.stone.model.InHousePart;
import wgu.stone.model.Inventory;
import wgu.stone.model.Part;
import wgu.stone.model.Product;

/**FUTURE ENHANCEMENTS: Add a database to persist and collect data so that running the application
 * every time would not reset all of the data.
 * Main class application
 */
public class Main extends Application {

    /**
     * Main window that is shown when application is started.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));
        primaryStage.setTitle("Main Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //The Javadocs are found in the javadocs folder in the base directory of this project.
    /**
     * Populates the test data and starts the application.
     * @param args
     */
    public static void main(String[] args) {


        Part part1 = new InHousePart(1, "radiator", 7.5, 5, 1, 8,555);
        Part part2 = new InHousePart(2, "battery", 8.5, 5, 1, 8,546);
        Part part3 = new InHousePart(3, "engine", 9.5, 5, 1, 8,577);
        Part part4 = new InHousePart(4, "alternator", 4.5, 5, 1, 8,570);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);


        Product product1 = new Product(1, "product 1" , 70.00, 67, 2, 89);
        Product product2 = new Product(2, "Product 2" , 71.89, 67, 2, 89);
        Product product3 = new Product(3, "product 3" , 72.89, 67, 2, 89);
        Product product4 = new Product(4, "product 4" , 73.89, 67, 2, 89);
        Product product5 = new Product(5, "product 5" , 74.89, 67, 2, 89);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);
        product1.addAssociatedPart(part1);

        launch(args);
    }
}
