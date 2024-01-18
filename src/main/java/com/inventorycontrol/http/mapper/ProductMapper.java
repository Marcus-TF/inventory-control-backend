package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.ProductRequest;
import com.inventorycontrol.http.dto.response.ProductResponse;
import com.inventorycontrol.model.CategoryModel;
import com.inventorycontrol.model.ProductModel;
import com.inventorycontrol.model.ProviderModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public static ProductModel toModel(ProductRequest productRequest) {
        return ProductModel.builder()
                .productName(productRequest.getProductName())
                .weight(productRequest.getWeight())
                .controlled(productRequest.isControlled())
                .minimumQuantity(productRequest.getMinimumQuantity())
                .categoryModel(CategoryModel.builder().categoryId(productRequest.getCategoryId()).build())
                .providerModel(ProviderModel.builder().providerId(productRequest.getProviderId()).build())
                .build();
    }

    public static ProductResponse toResponse(ProductModel productModel) {
        return ProductResponse.builder()
                .productId(productModel.getProductId())
                .productName(productModel.getProductName())
                .weight(productModel.getWeight())
                .controlled(productModel.isControlled())
                .minimumQuantity(productModel.getMinimumQuantity())
                .build();
    }

    public static List<ProductResponse> toResponseList(List<ProductModel> productModelList) {
        if (Objects.isNull(productModelList)) {
            return new ArrayList<>();
        } else {
            return productModelList.stream().map(ProductMapper::toResponse).collect(Collectors.toList());
        }
    }
}
