package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.EntryItemRequest;
import com.inventorycontrol.http.dto.response.EntryItemResponse;
import com.inventorycontrol.model.EntryItemModel;
import com.inventorycontrol.model.EntryModel;
import com.inventorycontrol.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EntryItemMapper {

    public static EntryItemModel toModel(EntryItemRequest request) {
        return EntryItemModel.builder()
                .batch(request.getBatch())
                .amount(request.getAmount())
                .value(request.getValue())
                .entryModel(EntryModel.builder().entryId(request.getEntryId()).build())
                .productModel(ProductModel.builder().productId(request.getProductId()).build())
                .build();
    }

    public static EntryItemResponse toResponse(EntryItemModel model) {
        return EntryItemResponse.builder()
                .entryItemId(model.getEntryItemId())
                .batch(model.getBatch())
                .amount(model.getAmount())
                .value(model.getValue())
                .build();
    }

    public static List<EntryItemResponse> toResponseList(List<EntryItemModel> entryItemModels) {
        if (Objects.isNull(entryItemModels)) {
            return new ArrayList<>();
        } else {
            return entryItemModels.stream().map(EntryItemMapper::toResponse).collect(Collectors.toList());
        }
    }
}
