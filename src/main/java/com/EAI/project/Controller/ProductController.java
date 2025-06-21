package com.EAI.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;        
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EAI.project.Model.Product;
import com.EAI.project.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/Virtualshop")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}") 
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String keyword, @RequestParam(defaultValue = "8") Integer searchAmount) {
        return productService.searchProducts(keyword, searchAmount);
    }

    @GetMapping("/products/filter")
    public List<Product> filterProducts(@RequestParam String category, @RequestParam String sellerName, @RequestParam Double priceOriginal, @RequestParam Double priceActual, @RequestParam Double itemRating, @RequestParam Integer totalRating, @RequestParam Integer totalSold, @RequestParam Integer favoriteCount) {
        return productService.filterProducts(category, sellerName, priceOriginal, priceActual, itemRating, totalRating, totalSold, favoriteCount);
    }

    @GetMapping("/products/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/products/seller")
    public List<Product> getProductsBySellerName(@RequestParam String sellerName) {
        return productService.getProductsBySellerName(sellerName);
    }

    @GetMapping("/products/price-original")
    public List<Product> getProductsByPriceOriginal(@RequestParam Double priceOriginal) {
        return productService.getProductsByPriceOriginal(priceOriginal);
    }

    @GetMapping("/products/price-actual")
    public List<Product> getProductsByPriceActual(@RequestParam Double priceActual) {
        return productService.getProductsByPriceActual(priceActual);
    }

    @GetMapping("/products/item-rating")
    public List<Product> getProductsByItemRating(@RequestParam Double itemRating) {
        return productService.getProductsByItemRating(itemRating);
    }

    @GetMapping("/products/total-rating")
    public List<Product> getProductsByTotalRating(@RequestParam Integer totalRating) {
        return productService.getProductsByTotalRating(totalRating);
    }

    @GetMapping("/products/sold")
    public List<Product> getProductsByTotalSold(@RequestParam Integer totalSold) {
        return productService.getProductsByTotalSold(totalSold);
    }

    @GetMapping("/products/favorite")
    public List<Product> getProductsByFavoriteCount(@RequestParam Integer favoriteCount) {
        return productService.getProductsByFavoriteCount(favoriteCount);
    }
}
