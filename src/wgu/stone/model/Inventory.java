package wgu.stone.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;


public class Inventory {


    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts =FXCollections.observableArrayList();

    public Inventory() {
    }

    //appends part to list
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    //appends product to list
    public static void addProduct (Product newProduct) {
        allProducts.add(newProduct);
    }
    //looks up part by id
    public static Part lookupPartbyId(int id) {
        for(Part p : allParts) {
            if(p.getId() == id) {
               return p;
            }
        } return null;
    }
    //looks up part by name
    public static ObservableList<Part> lookupPart(String name) {
        ObservableList searchedPart = FXCollections.observableArrayList();
        for(Part n : allParts) {
            if(n.getName().toLowerCase().contains(name.toLowerCase())) {
                searchedPart.add(n);
            }
        } return searchedPart;
    }
    //looks up product by name
    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList searchedProductByName = FXCollections.observableArrayList();
        for(Product n : allProducts) {
            if(n.getProductName().toLowerCase().contains(name.toLowerCase())) {
                searchedProductByName.add(n);
            }
        } return searchedProductByName;
    }
    //looks up product by id
    public static Product lookupProductById(int id) {
        for(Product p : allProducts) {
            if(p.getProductId() == id) {
                return p;
            }
        } return null;
    }

    //updates the part
    public static void updatePart(Part newPart) {
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getId() == newPart.getId()) {
               allParts.set(i, newPart);
            }
        }
    }
    //updates the product
    public static void updateProduct(Product newProduct) {
        for(int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).getProductId() == newProduct.getProductId()) {
                allProducts.set(i, newProduct);
            }
        }
    }
    //deletes a part
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    //deletes a product
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
