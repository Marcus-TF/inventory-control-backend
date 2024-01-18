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
@Table(name = "Exit", schema = "inventory")
public class ExitModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID exitId;

    private Double total;

    private Double shipping;

    private Double tax;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreModel storeModel;

    @ManyToOne
    @JoinColumn(name = "shippingCompanyId")
    private ShippingCompanyModel shippingCompanyModel;
}
