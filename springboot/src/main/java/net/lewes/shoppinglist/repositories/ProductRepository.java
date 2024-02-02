package net.lewes.shoppinglist.repositories;

import net.lewes.shoppinglist.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends JpaRepository<Product, Long > {
    Product findProductByBarcode(String barcode);
}
