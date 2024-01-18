package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.CategoryRequest;
import com.inventorycontrol.http.dto.response.CategoryResponse;
import com.inventorycontrol.model.CategoryModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public static CategoryModel toModel(CategoryRequest categoryRequest) {
        return CategoryModel.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();
    }

    public static CategoryResponse toResponse(CategoryModel categoryModel) {
        return CategoryResponse.builder()
                .categoryId(categoryModel.getCategoryId())
                .categoryName(categoryModel.getCategoryName())
                ._links(categoryModel.getLinks())
                .build();
    }

    public static List<CategoryResponse> toResponseList(List<CategoryModel> categoryModelList) {
        if (Objects.isNull(categoryModelList)) {
            return new ArrayList<>();
        } else {
            return categoryModelList.stream().map(CategoryMapper::toResponse).collect(Collectors.toList());
        }
    }
}
