package com.inventorycontrol.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryResponse {

    private UUID entryId;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime requestDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime entryDate;

    private Double total;

    private Double shipping;

    private Integer invoiceNumber;

    private Double tax;
}
