package com.ecommerce.WatchStore.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "watches")
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userGender", nullable = false)
    public String userGender;

    @Column(name = "machineType", nullable = false)
    private String machineType;

    @Column(name = "screen", nullable = false)
    private String screen;

    @Column(name = "caseMaterial", nullable = false)
    private String caseMaterial;

    @Column(name = "caseDiameter", nullable = false)
    private Float caseDiameter;

    @Column(name = "caseThickness", nullable = false)
    private String caseThickness;

    @Column(name = "strapMaterial", nullable = false)
    private String strapMaterial;

    @Column(name = "strapWidth", nullable = false)
    private Float strapWidth;

    @Column(name = "glassMaterial", nullable = false)
    private String glassMaterial;

    @Column(name = "waterResistance", nullable = false)
    private String waterResistance;

    @Column(name = "warrantyPeriod", nullable = false)
    private Float warrantyPeriod;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "discount", nullable = true)
    private Integer discount;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "newProduct")
    private Boolean newProduct;

    @Column(name = "productPrice", nullable = false)
    private Integer productPrice;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @PrePersist
    protected void onCreate() {
        if (newProduct == null) {
            newProduct = false;
        }
        if (isDeleted == null) {
            isDeleted = false;
        }
    }

}
