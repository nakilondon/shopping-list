package net.lewes.shoppinglist.models;

public class ProductResponse {

    private String code;

    private  Product product;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", product=" + product +
                '}';
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
