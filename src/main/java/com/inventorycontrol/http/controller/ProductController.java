package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IProductController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ProductRequest;
import com.inventorycontrol.http.dto.response.ProductResponse;
import com.inventorycontrol.http.mapper.ProductMapper;
import com.inventorycontrol.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController implements IProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok().body(ProductMapper.toResponseList(productService.findAll()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String productId) {
        return ResponseEntity.ok().body(ProductMapper.toResponse(productService.findById(UUID.fromString(productId))));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok()
                .body(ProductMapper.toResponse(productService.save(ProductMapper.toModel(productRequest))));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequest productRequest,
                                                  @PathVariable String productId) {
        return ResponseEntity.ok().body(ProductMapper
                .toResponse(productService.update(UUID.fromString(productId), ProductMapper.toModel(productRequest))));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<MessageError> delete(@PathVariable String productId) {
        productService.delete(UUID.fromString(productId));
        var messageError = new MessageError();
        messageError.setMessage("Produto deletado com sucesso!");
        messageError.setStatusCode(Integer.valueOf(200));
        return ResponseEntity.ok().body(messageError);
    }
}
