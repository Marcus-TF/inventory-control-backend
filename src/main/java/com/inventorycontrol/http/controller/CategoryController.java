package com.inventorycontrol.http.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.inventorycontrol.http.controller.interfaces.ICategoryController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.CategoryRequest;
import com.inventorycontrol.http.dto.response.CategoryResponse;
import com.inventorycontrol.http.mapper.CategoryMapper;
import com.inventorycontrol.model.CategoryModel;
import com.inventorycontrol.service.impl.CategorySeviceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/category")
public class CategoryController implements ICategoryController {

    private final CategorySeviceImpl categoryServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<CategoryModel>> findAll() {
        var categoryList = categoryServiceImpl.findAll();

        for (CategoryModel category : categoryList) {
            UUID categoryId  = category.getCategoryId();
            Link selfLink = linkTo(CategoryController.class).slash(categoryId).withSelfRel();
            category.add(selfLink);
        }
        Link link = linkTo(CategoryController.class).withSelfRel();
        CollectionModel<CategoryModel> categoryModelCollection = CollectionModel.of(categoryList, link);

        return new ResponseEntity<>(categoryModelCollection, HttpStatus.OK);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable UUID categoryId) {
        var model = categoryServiceImpl.findById(categoryId);
        model.add(linkTo(methodOn(CategoryController.class).findById(categoryId)).withSelfRel());
        var response = CategoryMapper.toResponse(model);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryRequest categoryRequest) {
        var model = categoryServiceImpl.save(CategoryMapper.toModel(categoryRequest));
        model.add(linkTo(methodOn(CategoryController.class).save(categoryRequest)).withSelfRel());
        var response = CategoryMapper.toResponse(model);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@RequestBody @Valid CategoryRequest categoryRequest,
                                                   @PathVariable UUID categoryId) {
        var model = categoryServiceImpl.update(CategoryMapper.toModel(categoryRequest), categoryId);
        model.add(linkTo(methodOn(CategoryController.class).update(categoryRequest, categoryId)).withSelfRel());
        var response = CategoryMapper.toResponse(model);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<MessageError> delete(@PathVariable UUID categoryId) {
        categoryServiceImpl.delete(categoryId);
        var messageError = new MessageError();
        messageError.setMessage("Categoria deletada com sucesso!");
        messageError.setStatusCode(Integer.valueOf(200));
        return new ResponseEntity<>(messageError, HttpStatus.OK);
    }
}
