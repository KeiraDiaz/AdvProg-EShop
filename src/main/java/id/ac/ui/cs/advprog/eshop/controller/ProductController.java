package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        product.setProductId(UUID.randomUUID().toString());
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product == null) {
            return "redirect:/list";
        }
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product product) {
        service.update(product);
        return "redirect:list";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId, Model model) {
        Product deletedProduct = service.delete(productId);
        if (deletedProduct != null) {
            model.addAttribute("message", "Product deleted successfully: " + deletedProduct.getProductName());
        } else {
            model.addAttribute("message", "Product not found");
        }
        return "redirect:/product/list";
    }
}
