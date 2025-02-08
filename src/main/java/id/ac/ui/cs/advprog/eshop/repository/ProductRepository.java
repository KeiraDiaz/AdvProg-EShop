package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepository {
    ///  To Do: If Allowed, try reimplementing productData as hash map for O(1) efficiency
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    /// After some thinking, decided that to make product objects immutable,
    /// essentially making it so that there is no risk of race conditions, and
    /// for predictability, it also works better with concurrency. Too soon to tell
    /// if needs to be changed yet or not...

    public Optional<Product> update(Product updatedProduct) {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("Updated product cannot be null");
        }
        String productId = updatedProduct.getProductId();
        for (int i = 0; i < productData.size(); i++) {

            Product existingProduct = productData.get(i);
            if (existingProduct.getProductId().equals(productId)) {

                Product newProduct = new Product(
                        existingProduct.getProductId(),
                        updatedProduct.getProductName(),
                        updatedProduct.getProductQuantity()
                );

                productData.set(i, newProduct);
                return Optional.of(newProduct);
            }
        }

        return Optional.empty();
    }

}
