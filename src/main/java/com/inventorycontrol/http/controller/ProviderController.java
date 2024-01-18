package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IProviderController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ProviderRequest;
import com.inventorycontrol.http.dto.response.ProviderResponse;
import com.inventorycontrol.http.mapper.ProviderMapper;
import com.inventorycontrol.service.impl.ProviderServiceImpl;
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
@RequestMapping("/provider")
public class ProviderController implements IProviderController {

    private final ProviderServiceImpl providerService;

    @GetMapping("/all")
    public ResponseEntity<List<ProviderResponse>> findAll() {
        return ResponseEntity.ok().body(ProviderMapper.toResponseList(providerService.findAll()));
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderResponse> findById(@PathVariable String providerId) {
        return ResponseEntity.ok()
                .body(ProviderMapper.toResponse(providerService.findById(UUID.fromString(providerId))));
    }

    @PostMapping
    public ResponseEntity<ProviderResponse> save(@RequestBody @Valid ProviderRequest providerRequest) {
        return ResponseEntity.ok()
                .body(ProviderMapper.toResponse(providerService.save(ProviderMapper.toModel(providerRequest))));
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<ProviderResponse> update(@RequestBody @Valid ProviderRequest providerRequest,
                                                   @PathVariable String providerId) {
        return ResponseEntity.ok().body(ProviderMapper.toResponse(
                providerService.update(UUID.fromString(providerId), ProviderMapper.toModel(providerRequest))));
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<MessageError> delete(@PathVariable String providerId) {
        providerService.delete(UUID.fromString(providerId));
        var messageError = new MessageError();
        messageError.setMessage("Fornecedor deletado com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
