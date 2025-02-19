package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product("123", "Test Product", 50);
        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/create").flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testListProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product("123", "Test Product", 50));
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testEditProductPage_Found() throws Exception {
        Product product = new Product("123", "Test Product", 50);
        when(productService.findById("123")).thenReturn(product);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPage_NotFound() throws Exception {
        when(productService.findById("123")).thenReturn(null);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product("123", "Updated Product", 75);
        when(productService.update(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/edit").flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testDeleteProduct_Success() throws Exception {
        Product product = new Product("123", "Test Product", 50);
        when(productService.delete("123")).thenReturn(product);

        mockMvc.perform(post("/product/delete/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testDeleteProduct_NotFound() throws Exception {
        when(productService.delete("123")).thenReturn(null);

        mockMvc.perform(post("/product/delete/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }
}
