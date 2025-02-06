package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// declares that this class is a Spring MVC Controller
/// handles web requests related to products
@Controller
@RequestMapping("/product")
public class ProductController {

    /// automatically injects an instance of ProductService
    /// service is a reference to the business logic layer
    @Autowired
    private ProductService service;

    /// handles GET requests to "/product/create"
    /// prepares a new Product object and adds it to the model
    /// returns the "createProduct" view
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product",product);
        return "createProduct";
    }

    /// handles POST requests to "/product/create"
    /// processes form submission and saves the new product
    /// redirects to the product list page
    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    /// handles GET requests to "/product"
    /// fetches all products and adds them to the model
    @GetMapping("/list")
    public String listProduct(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }
}
