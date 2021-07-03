package wgu.stone.model;

import javafx.collections.ObservableList;

public class Product {


    private ObservableList<Part> associatedParts;
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private int minProduct;
    private int maxProduct;

    public Product(int productId, String productName, double productPrice, int productStock, int minProduct, int maxProduct) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.minProduct = minProduct;
        this.maxProduct = maxProduct;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getMinProduct() {
        return minProduct;
    }

    public void setMinProduct(int minProduct) {
        this.minProduct = minProduct;
    }

    public int getMaxProduct() {
        return maxProduct;
    }

    public void setMaxProduct(int maxProduct) {
        this.maxProduct = maxProduct;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return true;
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}
