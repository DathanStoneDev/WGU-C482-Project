package wgu.stone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory Class.
 */
public class Inventory {

    /**
     * allParts and allProduct observable lists.
     * These list represent the collection of parts and products that are shown on the main screen.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts =FXCollections.observableArrayList();

    /**
     * Default empty inventory constructor.
     */
    public Inventory() {
    }

    /**
     * Adds a new part to the allParts list.
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a product to the allProducts list.
     * @param newProduct
     */
    public static void addProduct (Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up parts by the part id
     * @param id
     * @return the part from the id.
     */
    public static Part lookupPartById(int id) {
        for(Part p : allParts) {
            if(p.getId() == id) {
               return p;
            }
        } return null;
    }


    /**
     * looks for parts based on the characters in the name.
     * allows for partial string lookup.
     * @param name
     * @return part.
     */
    public static ObservableList<Part> lookupPart(String name) {
        ObservableList searchedPart = FXCollections.observableArrayList();
        for(Part n : allParts) {
            if(n.getName().toLowerCase().contains(name.toLowerCase())) {
                searchedPart.add(n);
            }
        } return searchedPart;
    }

    /**
     * looks for products based on the characters in the name field.
     * @param name
     * @return product.
     */
    public static ObservableList<Product> lookupProductByName (String name) {
        ObservableList searchedProductByName = FXCollections.observableArrayList();
        for(Product n : allProducts) {
            if(n.getProductName().toLowerCase().contains(name.toLowerCase())) {
                searchedProductByName.add(n);
            }
        } return searchedProductByName;
    }

    /**
     * looks for parts based on the ID.
     * @param id
     * @return product.
     */
    public static Product lookupProductById(int id) {
        for(Product p : allProducts) {
            if(p.getProductId() == id) {
                return p;
            }
        } return null;
    }

    /**
     * Updates the selected part by the id.
     * the "int i" is the INDEX parameter used to search through the allParts list.
     * @param newPart
     */
    public static void updatePart(Part newPart) {
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getId() == newPart.getId()) {
               allParts.set(i, newPart);
            }
        }
    }

    /**
     * Updates the selected product by the id.
     * the "int i" is the INDEX parameter used to search through the allProducts list.
     * @param newProduct
     */
    public static void updateProduct(Product newProduct) {
        for(int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).getProductId() == newProduct.getProductId()) {
                allProducts.set(i, newProduct);
            }
        }
    }

    /**
     * deletes a selected part.
     * @param selectedPart
     * @return deleted part.
     */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /**
     * deletes a selected product.
     * @param selectedProduct
     * @return deleted product.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * gets all parts in this allParts list.
     * @return allParts list.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * gets all products in the allProducts list.
     * @return allProducts list.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
