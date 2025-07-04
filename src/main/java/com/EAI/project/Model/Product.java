package com.EAI.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    private String id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private Double priceOriginal;
    private Double priceActual;
    private String deliveryLocation;
    private String category;
    @Column(columnDefinition = "text")
    private String specification;
    private Double itemRating;
    private Integer totalRating;
    private String sellerName;
    private Integer totalSold;
    private Integer favoriteCount;
    @Column(columnDefinition = "text")
    private String productLink;
    private String imageLink;
    private String siteName;
    private LocalDate dateAdded;
    private LocalDateTime createdTimestamp;
    private String idElastic;
    private String idHash;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}