package net.lewes.shoppinglist.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "barcode", unique = true)
    private String barcode;
    @Column(name = "name")
    private String productName;

    public Product() {
    }

    public Product(String barcode, String productName) {
        this.barcode = barcode;
        this.productName = productName;
    }

    @JsonProperty("name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("barcode")
    public String getBarcode() {
        return barcode;
    }

    @JsonProperty("_id")
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
