package wgu.stone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wgu.stone.controller.UtilityClass;
import wgu.stone.model.InHousePart;
import wgu.stone.model.Inventory;
import wgu.stone.model.Part;
import wgu.stone.model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {


        Part part1 = new InHousePart(1, "radiator", 7.5, 5, 1, 8,555);
        Part part2 = new InHousePart(2, "battery", 8.5, 5, 1, 8,546);
        Part part3 = new InHousePart(3, "engine", 9.5, 5, 1, 8,577);
        Part part4 = new InHousePart(4, "alternator", 4.5, 5, 1, 8,577);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);


        Product product1 = new Product(1, "Yessir" , 76.89, 67, 2, 89);
        Product product2 = new Product(1, "Yessir" , 76.89, 67, 2, 89);
        Product product3 = new Product(1, "Yessir" , 76.89, 67, 2, 89);
        Product product4 = new Product(1, "Yessir" , 76.89, 67, 2, 89);
        Product product5 = new Product(1, "Yessir" , 76.89, 67, 2, 89);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);
        product1.addAssociatedPart(part1);

        launch(args);
    }
}
