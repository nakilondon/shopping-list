package net.lewes.shoppinglist.services;

import net.lewes.shoppinglist.models.Product;

public interface ProductService {
    Product findProduct(String barcode);
    Product updateProductName(String barcode, String productName);
}
