package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

///  interface, doesn't provide implementations, depends on ProductServiceImpl
public interface ProductService {

    /// available methods to be implemented
    public Product create(Product product);
    public List<Product> findAll();
    public Product update(Product product);

}


