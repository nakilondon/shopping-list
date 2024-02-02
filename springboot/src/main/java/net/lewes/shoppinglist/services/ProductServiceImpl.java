package net.lewes.shoppinglist.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.lewes.shoppinglist.models.Product;
import net.lewes.shoppinglist.models.ProductResponse;
import net.lewes.shoppinglist.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(String barcode)
    {
        Product product = productRepository.findProductByBarcode(barcode);

        if (product==null) {
            product = searchWebForProduct(barcode);
            if (product == null) {
                product = new Product(barcode, "Unknown barcode: " + barcode);
            }
        }

        return productRepository.save(product);
    }

    public Product updateProductName(String barcode, String productName)
    {
        Product product = productRepository.findProductByBarcode(barcode);
        if (product == null) {
            return null;
        }

        product.setProductName(productName);
        return productRepository.save(product);
    }

    private Product searchWebForProduct(String barcode)
    {
        String url = "https://world.openfoodfacts.org/api/v2/product/" + barcode;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        ProductResponse productResponse = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unknown product: " + barcode);
            return null;
        }

        // get the information
        String responseString = response.body();

        try {
            productResponse = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(responseString, ProductResponse.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (productResponse.getProduct() == null)
        {
            System.out.println("Unknown product: " + barcode);
            return null;
        }

        return productResponse.getProduct();
    }
}
