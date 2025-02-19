package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("123", createdProduct.getProductId());
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");

        Iterator<Product> mockIterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(mockIterator);

        List<Product> productList = productService.findAll();

        assertEquals(2, productList.size());
        assertEquals("Product 1", productList.get(0).getProductName());
        assertEquals("Product 2", productList.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Updated Product");

        when(productRepository.update(product)).thenReturn(Optional.of(product));

        Product updatedProduct = productService.update(product);

        assertNotNull(updatedProduct);
        assertEquals("123", updatedProduct.getProductId());
        assertEquals("Updated Product", updatedProduct.getProductName());
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.findById("123")).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById("123");

        assertNotNull(foundProduct);
        assertEquals("123", foundProduct.getProductId());
        verify(productRepository, times(1)).findById("123");
    }

    @Test
    void testFindProductById_NotFound() {
        when(productRepository.findById("999")).thenReturn(Optional.empty());

        Product foundProduct = productService.findById("999");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("999");
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.delete("123")).thenReturn(product);

        Product deletedProduct = productService.delete("123");

        assertNotNull(deletedProduct);
        assertEquals("123", deletedProduct.getProductId());
        verify(productRepository, times(1)).delete("123");
    }
}
