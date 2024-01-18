package com.inventorycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entry_item", schema = "inventory")
public class EntryItemModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID entryItemId;

    private String batch;

    private Integer amount;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "entryId")
    private EntryModel entryModel;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductModel productModel;
}
