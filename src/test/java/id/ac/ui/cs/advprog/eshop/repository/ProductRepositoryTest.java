package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @Mock
    ProductRepository mockProductRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateProduct_Success() {
        Product oldProduct = new Product("123", "Old Product", 50);
        productRepository.create(oldProduct);
        Product updatedData = new Product("123", "Updated Product", 100);
        Product result = productRepository.update(oldProduct.getProductId(), updatedData);
        assertNotNull(result);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(100, result.getProductQuantity());
        assertEquals("123", result.getProductId());
    }
    
    @Test
    void testUpdateProduct_NotFound() {
        Product updatedData = new Product("999", "Nonexistent Product", 50);
        Product result = productRepository.update("999", updatedData);
        assertNull(result);
    }
    
    @Test
    void testUpdateProduct_NullProduct() {
        assertThrows(IllegalArgumentException.class, () -> 
            productRepository.update("123", null));
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product("123", "Test Product", 50);
        productRepository.create(product);
        Product deletedProduct = productRepository.delete("123");
        assertNotNull(deletedProduct);
        assertEquals("123", deletedProduct.getProductId());
        assertFalse(productRepository.findById("123").isPresent());
    }

    @Test
    void testDeleteProduct_NotFound() {
        Product deletedProduct = productRepository.delete("999");
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProduct_NullId() {
        assertThrows(IllegalArgumentException.class, () -> productRepository.delete(null));
    }

    @Test
    void testFindById_ProductFound() {
        Product product = new Product("123", "Test Product", 50);
        productRepository.create(product);
        Optional<Product> foundProduct = productRepository.findById("123");
        assertTrue(foundProduct.isPresent());
        assertEquals("123", foundProduct.get().getProductId());
    }

    @Test
    void testFindById_ProductNotFound() {
        Optional<Product> foundProduct = productRepository.findById("123");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testCreateProduct() {
        Product product = new Product("123", "Test Product", 50);
        Product createdProduct = productRepository.create(product);
        assertEquals(product, createdProduct);
        Optional<Product> foundProduct = productRepository.findById("123");
        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
    }

    @Test
    void testUpdateProduct_EmptyId() {
        Product product = new Product("", "Invalid Product", 50);
        assertThrows(IllegalArgumentException.class, () -> productRepository.update(product.getProductId(), product));
    }

    @Test
    void testUpdateProduct_NoChange() {
        Product product = new Product("123", "Same Product", 50);
        productRepository.create(product);
        Product updateData = new Product("123", "Same Product", 50);
        Product updatedProduct = productRepository.update(updateData.getProductId(), updateData);
        assertNotNull(updatedProduct);
        assertEquals("Same Product", updatedProduct.getProductName());
    }

    @Test
    void testDeleteProduct_EmptyId() {
        assertThrows(IllegalArgumentException.class, () -> productRepository.delete(""));
    }

    @Test
    void testUpdateProduct_DifferentId() {
        Product product1 = new Product("123", "Product A", 50);
        Product product2 = new Product("456", "Product B", 75);
        productRepository.create(product1);
        productRepository.create(product2);

        Product updateRequest = new Product("789", "Non-Matching Product", 100);
        Product updatedProduct = productRepository.update(updateRequest.getProductId(), updateRequest);
        // Verify product was not updated
        Iterator<Product> productIterator = productRepository.findAll();
        boolean found = false;
        while (productIterator.hasNext()) {
            Product p = productIterator.next();
            if (p.getProductId().equals(updateRequest.getProductId())) {
                found = true;
                break;
            }
        }
        assertFalse(found);
        assertNull(updatedProduct);
    }

    @Test
    void testDeleteProduct_DifferentId() {
        Product product1 = new Product("123", "Product A", 50);
        Product product2 = new Product("456", "Product B", 75);
        productRepository.create(product1);
        productRepository.create(product2);

        Product deletedProduct = productRepository.delete("789");
        assertNull(deletedProduct);
    }

    @Test
    void testUpdateProduct_NullProductId() {
        Product product = new Product(null, "Test Product", 50);
        assertThrows(IllegalArgumentException.class, () -> productRepository.update(product.getProductId(), product));
    }

    @Test
    void testUpdateProduct_EmptyProductId() {
        Product product = new Product("  ", "Test Product", 50);
        assertThrows(IllegalArgumentException.class, () -> productRepository.update(product.getProductId(), product));
    }

}