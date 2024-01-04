package it.paolone.ecommerce.dto;

public class ProductDTO {
    private String productName;
    private String productBarcode;
    private int productQuantity;
    private String imageFileName;
    private float productPrice;

    // getters and setters

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getBarcode() {
        return this.productBarcode;
    }

    public int getQuantity() {
        return this.productQuantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBarcode(String barcode) {
        this.productBarcode = barcode;
    }

    public void setQuantity(short quantity) {
        this.productQuantity = quantity;
    }

    public String getImageFilename() {
        return this.imageFileName;
    }

    public void setImageFilename(String filename) {
        this.imageFileName = filename;
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj);
    }

}
