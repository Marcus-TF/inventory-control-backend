package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.EntryRequest;
import com.inventorycontrol.http.dto.response.EntryResponse;
import com.inventorycontrol.model.EntryModel;
import com.inventorycontrol.model.ShippingCompanyModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EntryMapper {

    public static EntryModel toModel(EntryRequest request) {
        return EntryModel.builder()
                .requestDate(request.getRequestDate())
                .entryDate(request.getEntryDate())
                .total(request.getTotal())
                .shipping(request.getShipping())
                .invoiceNumber(request.getInvoiceNumber())
                .tax(request.getTax())
                .shippingCompanyModel(ShippingCompanyModel.builder().shippingCompanyId(request.getShippingCompanyId()).build())
                .build();
    }

    public static EntryResponse toResponse(EntryModel model) {
        return EntryResponse.builder()
                .entryId(model.getEntryId())
                .requestDate(model.getRequestDate())
                .entryDate(model.getEntryDate())
                .total(model.getTotal())
                .shipping(model.getShipping())
                .invoiceNumber(model.getInvoiceNumber())
                .tax(model.getTax())
                .build();
    }

    public static List<EntryResponse> entryResponseList(List<EntryModel> entryModelList) {
        if (Objects.isNull(entryModelList)) {
            return new ArrayList<>();
        } else {
            return entryModelList.stream().map(EntryMapper::toResponse).collect(Collectors.toList());
        }
    }
}
