package com.inventorycontrol.http.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageError {

    private String message;

    private Integer statusCode;
}
