package com.inventorycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "product", schema = "inventory")
public class ProductModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    private String productName;

    private String weight;

    private boolean controlled;

    private Integer minimumQuantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel categoryModel;

    @ManyToOne
    @JoinColumn(name = "providerId")
    private ProviderModel providerModel;
}
