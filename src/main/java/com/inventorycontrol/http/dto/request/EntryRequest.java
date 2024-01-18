package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EntryRequest {

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime requestDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime entryDate;

    @NotNull(message = "Informe o valor total.")
    private Double total;

    @NotBlank(message = "Informe o valor do Envio.")
    private Double shipping;

    @NotBlank(message = "Informe o número da fatura.")
    private Integer invoiceNumber;

    @NotBlank(message = "Informe o imposto.")
    private Double tax;

    @NotBlank(message = "Informe o ID da transportadora.")
    private UUID shippingCompanyId;
}
