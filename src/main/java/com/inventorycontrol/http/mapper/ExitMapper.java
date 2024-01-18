package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.ExitRequest;
import com.inventorycontrol.http.dto.response.ExitResponse;
import com.inventorycontrol.model.ExitModel;
import com.inventorycontrol.model.ShippingCompanyModel;
import com.inventorycontrol.model.StoreModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ExitMapper {

    public static ExitModel toModel(ExitRequest request) {
        return ExitModel.builder()
                .total(request.getTotal())
                .shipping(request.getShipping())
                .tax(request.getTax())
                .storeModel(StoreModel.builder().storeId(request.getStoreId()).build())
                .shippingCompanyModel(ShippingCompanyModel.builder().shippingCompanyId(request.getShippingCompanyId()).build())
                .build();
    }

    public static ExitResponse toResponse(ExitModel model) {
        return ExitResponse.builder()
                .exitId(model.getExitId())
                .total(model.getTotal())
                .shipping(model.getShipping())
                .tax(model.getTax())
                .build();
    }

    public static List<ExitResponse> toResponseList(List<ExitModel> exitModelList) {
        if (Objects.isNull(exitModelList)) {
            return new ArrayList<>();
        } else {
            return exitModelList.stream().map(ExitMapper::toResponse).collect(Collectors.toList());
        }
    }
}
