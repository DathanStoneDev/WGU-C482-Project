package wgu.stone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class
 */
public class Product {

    /**
     * Fields that make up a product.
     * associatedParts holds a product's parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private int minProduct;
    private int maxProduct;

    /**
     * Constructor that can be used to create a product object.
     * @param productId
     * @param productName
     * @param productPrice
     * @param productStock
     * @param minProduct
     * @param maxProduct
     */
    public Product(int productId, String productName, double productPrice, int productStock, int minProduct, int maxProduct) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.minProduct = minProduct;
        this.maxProduct = maxProduct;
    }

    /**
     * Default empty constructor.
     */
    public Product() {

    }

    /**
     * @return product ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * sets product id.
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return product's name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * sets the product name.
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return product's price.
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * sets product price.
     * @param productPrice
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return product's inventory.
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     *  sets product inventory.
     * @param productStock
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * @return product's minimum.
     */
    public int getMinProduct() {
        return minProduct;
    }

    /**
     * sets product minimum.
     * @param minProduct
     */
    public void setMinProduct(int minProduct) {
        this.minProduct = minProduct;
    }

    /**
     * @return product's maximum.
     */
    public int getMaxProduct() {
        return maxProduct;
    }

    /**
     * sets product maximum.
     * @param maxProduct
     */
    public void setMaxProduct(int maxProduct) {
        this.maxProduct = maxProduct;
    }

    /**
     * Adds parts to the associated parts list.
     * @param part
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * deletes parts from the associated parts list.
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * @return associated parts list.
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}
