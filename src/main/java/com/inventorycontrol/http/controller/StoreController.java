package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IStoreController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.StoreRequest;
import com.inventorycontrol.http.dto.response.StoreResponse;
import com.inventorycontrol.http.mapper.StoreMapper;
import com.inventorycontrol.service.impl.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class StoreController implements IStoreController {

    private final StoreServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<StoreResponse> save(StoreRequest request) {
        return ResponseEntity.ok().body(StoreMapper.toResponse(service.save(StoreMapper.toModel(request))));
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<StoreResponse> update(UUID uuid, StoreRequest request) {
        return ResponseEntity.ok().body(StoreMapper.toResponse(service.update(uuid, StoreMapper.toModel(request))));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<StoreResponse>> findAll() {
        return ResponseEntity.ok().body(StoreMapper.storeResponseList(service.findAll()));
    }

    @GetMapping("/findById/{uuid}")
    public ResponseEntity<StoreResponse> findById(UUID uuid) {
        return ResponseEntity.ok().body(StoreMapper.toResponse(service.findById(uuid)));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<MessageError> delete(UUID uuid) {
        service.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Loja deletada com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
