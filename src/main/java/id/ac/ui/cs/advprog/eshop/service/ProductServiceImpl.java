package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/// declares that this class is a Spring Service and provides actual implementation
/// of service methods related to the ProductService Class
@Service
public class ProductServiceImpl implements ProductService {

    /// automatically injects an instance of ProductRepository
    /// recall that productRepository is a reference to the repo layer
    @Autowired
    private ProductRepository productRepository;

    /// calls methods of ProductRepository which stores a an object of
    /// Product class
    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    /// calls find.All() which return Iterator
    /// converts Iterator<Product> into List<Product>
    /// Returns list of all products
    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> AllProduct = new ArrayList<>();
        productIterator.forEachRemaining(AllProduct::add);
        return AllProduct;
    }
}
