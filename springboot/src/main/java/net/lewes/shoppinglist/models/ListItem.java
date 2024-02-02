package net.lewes.shoppinglist.models;

import javax.persistence.*;

@Entity
@Table(name = "list")
public class ListItem{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "barcode", unique = true)
    private String barcode;
    @Column(name = "name")
    private String itemName;

    public ListItem() {}

    @Column(name = "count")
    private int count;

    public ListItem(Product product, int count) {
        barcode = product.getBarcode();
        itemName =product.getProductName();
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", count=" + count +
                '}';
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
