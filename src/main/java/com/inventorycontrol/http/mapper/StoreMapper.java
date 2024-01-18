package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.EntryRequest;
import com.inventorycontrol.http.dto.request.StoreRequest;
import com.inventorycontrol.http.dto.response.EntryResponse;
import com.inventorycontrol.http.dto.response.StoreResponse;
import com.inventorycontrol.model.CityModel;
import com.inventorycontrol.model.EntryModel;
import com.inventorycontrol.model.StoreModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public static StoreModel toModel(StoreRequest request) {
        return StoreModel.builder()
                .name(request.getName())
                .zipCode(request.getZipCode())
                .address(request.getAddress())
                .number(request.getNumber())
                .district(request.getDistrict())
                .telephone(request.getTelephone())
                .subscription(request.getSubscription())
                .cnpj(request.getCnpj())
                .cityModel(CityModel.builder().cityId(request.getCityId()).build())
                .build();
    }

    public static StoreResponse toResponse(StoreModel model) {
        return StoreResponse.builder()
                .storeId(model.getStoreId())
                .name(model.getName())
                .zipCode(model.getZipCode())
                .address(model.getAddress())
                .number(model.getNumber())
                .district(model.getDistrict())
                .telephone(model.getTelephone())
                .subscription(model.getSubscription())
                .cnpj(model.getCnpj())
                .build();
    }

    public static List<StoreResponse> storeResponseList(List<StoreModel> storeModelList) {
        if (Objects.isNull(storeModelList)) {
            return new ArrayList<>();
        } else {
            return storeModelList.stream().map(StoreMapper::toResponse).collect(Collectors.toList());
        }
    }
}
