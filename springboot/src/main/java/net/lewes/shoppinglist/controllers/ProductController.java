package net.lewes.shoppinglist.controllers;

import net.lewes.shoppinglist.models.Product;
import net.lewes.shoppinglist.repositories.ProductRepository;
import net.lewes.shoppinglist.services.ProductServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductServiceImpl productResponseService;

    public ProductController(ProductRepository productRepository, ProductServiceImpl productResponseService) {
        this.productRepository = productRepository;
        this.productResponseService = productResponseService;
    }

    @PostMapping(value="/product")
    public Product newItem(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

//    @PostMapping(value="/barcode/{barcode}")
//    public Product newBarcode(@PathVariable String barcode) {
//        Product product = productResponseService.findProduct(barcode);
//       return product;
//    }

    @CrossOrigin
    @GetMapping("/products")
    List<Product> getItems(Model model) {
        return (List<Product>) productRepository.findAll();
    }

//    @CrossOrigin
//    @PostMapping("/clearList")
//    List<Product> clearList(Model model) {
//        productRepository.deleteAll();
//        return (List<Product>) productRepository.findAll();
//    }
}
