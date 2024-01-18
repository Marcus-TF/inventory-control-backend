package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.ProviderRequest;
import com.inventorycontrol.http.dto.response.ProviderResponse;
import com.inventorycontrol.model.ProviderModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProviderMapper {

    public static ProviderModel toModel(ProviderRequest providerRequest) {
        return ProviderModel.builder()
                .providerName(providerRequest.getProviderName())
                .address(providerRequest.getAddress())
                .num(providerRequest.getNum())
                .district(providerRequest.getDistrict())
                .cep(providerRequest.getCep())
                .contact(providerRequest.getContact())
                .cnpj(providerRequest.getCnpj())
                .insc(providerRequest.getInsc())
                .telephone(providerRequest.getTelephone())
                .cityModelList(CityMapper.toModelList(providerRequest.getCityListIds()))
                .build();
    }

    public static ProviderResponse toResponse(ProviderModel providerModel) {
        return ProviderResponse.builder()
                .providerId(providerModel.getProviderId())
                .providerName(providerModel.getProviderName())
                .address(providerModel.getAddress())
                .num(providerModel.getNum())
                .district(providerModel.getDistrict())
                .cep(providerModel.getCep())
                .contact(providerModel.getContact())
                .cnpj(providerModel.getCnpj())
                .insc(providerModel.getInsc())
                .telephone(providerModel.getTelephone())
                .cityResponseList(CityMapper.toResponseList(providerModel.getCityModelList()))
                .build();
    }

    public static List<ProviderResponse> toResponseList(List<ProviderModel> providerModelList) {
        if (Objects.isNull(providerModelList)) {
            return new ArrayList<>();
        } else {
            return providerModelList.stream().map(ProviderMapper::toResponse).collect(Collectors.toList());
        }

    }
}
