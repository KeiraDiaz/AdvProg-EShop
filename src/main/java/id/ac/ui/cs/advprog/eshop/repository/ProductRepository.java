package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/// Repository decorator, responsible for data storage and retrieval
@Repository
public class ProductRepository {
    ///  declares in memory list that stores products
    private List<Product> productData = new ArrayList<>();

    ///  adds new product to the list
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    ///  deletes product form the list

    /// prototype update function, not entirely sure that it works yet
    public Product update(Product product) {
        int index = productData.indexOf(product);
        productData.set(index, product);
        return product;
    }

    /// returns Iterator<Product> to allow iteration over stored products
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
