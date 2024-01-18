package com.inventorycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entry", schema = "inventory")
public class EntryModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID entryId;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime requestDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime entryDate;

    private Double total;

    private Double shipping;

    private Integer invoiceNumber;

    private Double tax;

    @ManyToOne
    @JoinColumn(name = "shippingCompanyId")
    private ShippingCompanyModel shippingCompanyModel;
}
