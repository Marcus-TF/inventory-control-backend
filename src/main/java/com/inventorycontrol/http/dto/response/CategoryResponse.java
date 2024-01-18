package com.inventorycontrol.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryResponse {

    private UUID categoryId;

    private String categoryName;

    private Links _links;
}
