package com.EAI.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.EAI.project.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContaining(String title);
    
    // Search by exact word match (case insensitive)
    @Query("SELECT p FROM Product p WHERE LOWER(p.title) LIKE LOWER(CONCAT('% ', :keyword, ' %')) OR LOWER(p.title) LIKE LOWER(CONCAT(:keyword, ' %')) OR LOWER(p.title) LIKE LOWER(CONCAT('% ', :keyword)) OR LOWER(p.title) = LOWER(:keyword)")
    List<Product> findByTitleContainingWord(@Param("keyword") String keyword);
    
    // Search by multiple keywords (space-separated)
    @Query("SELECT p FROM Product p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByTitleContainingIgnoreCase(@Param("keyword") String keyword);
    
    List<Product> findByCategory(String category);
    List<Product> findBySellerName(String sellerName);
    List<Product> findByPriceOriginal(Double priceOriginal);
    List<Product> findByPriceActual(Double priceActual);
    List<Product> findByItemRating(Double itemRating);
    List<Product> findByTotalRating(Integer totalRating);
    List<Product> findByTotalSold(Integer totalSold);
    List<Product> findByFavoriteCount(Integer favoriteCount);
}
