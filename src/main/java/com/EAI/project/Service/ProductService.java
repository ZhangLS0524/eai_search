package com.EAI.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EAI.project.Model.Product;
import com.EAI.project.Repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPriceOriginal(product.getPriceOriginal());
        existingProduct.setPriceActual(product.getPriceActual());
        existingProduct.setDeliveryLocation(product.getDeliveryLocation());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setSpecification(product.getSpecification());
        existingProduct.setItemRating(product.getItemRating());
        existingProduct.setTotalRating(product.getTotalRating());
        existingProduct.setSellerName(product.getSellerName());
        existingProduct.setTotalSold(product.getTotalSold());
        existingProduct.setFavoriteCount(product.getFavoriteCount());
        existingProduct.setProductLink(product.getProductLink());
        existingProduct.setImageLink(product.getImageLink());
        existingProduct.setSiteName(product.getSiteName());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProducts();
        }
        
        // Trim and normalize the keyword
        String normalizedKeyword = keyword.trim().toLowerCase();
        
        // If keyword contains spaces, search for exact word matches
        if (normalizedKeyword.contains(" ")) {
            return productRepository.findByTitleContainingIgnoreCase(normalizedKeyword);
        } else {
            // For single words, try to find exact word matches first
            List<Product> exactMatches = productRepository.findByTitleContainingWord(normalizedKeyword);
            
            // If no exact matches found, fall back to partial matches
            if (exactMatches.isEmpty()) {
                return productRepository.findByTitleContainingIgnoreCase(normalizedKeyword);
            }
            
            return exactMatches;
        }
    }

    public List<Product> filterProducts(String category, String sellerName, Double priceOriginal, Double priceActual, Double itemRating, Integer totalRating, Integer totalSold, Integer favoriteCount) {
        return productRepository.findByCategory(category)
        .stream()
        .filter(product -> product.getSellerName().equals(sellerName))
        .filter(product -> product.getPriceOriginal().equals(priceOriginal))
        .filter(product -> product.getPriceActual().equals(priceActual))
        .filter(product -> product.getItemRating().equals(itemRating))
        .filter(product -> product.getTotalRating().equals(totalRating))
        .filter(product -> product.getTotalSold().equals(totalSold))
        .filter(product -> product.getFavoriteCount().equals(favoriteCount))
        .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsBySellerName(String sellerName) {
        return productRepository.findBySellerName(sellerName);
    }

    public List<Product> getProductsByPriceOriginal(Double priceOriginal) {
        return productRepository.findByPriceOriginal(priceOriginal);
    }

    public List<Product> getProductsByPriceActual(Double priceActual) {
        return productRepository.findByPriceActual(priceActual);
    }

    public List<Product> getProductsByItemRating(Double itemRating) {
        return productRepository.findByItemRating(itemRating);
    }

    public List<Product> getProductsByTotalRating(Integer totalRating) {
        return productRepository.findByTotalRating(totalRating);
    }

    public List<Product> getProductsByTotalSold(Integer totalSold) {
        return productRepository.findByTotalSold(totalSold);
    }

    public List<Product> getProductsByFavoriteCount(Integer favoriteCount) {
        return productRepository.findByFavoriteCount(favoriteCount);
    }
}
