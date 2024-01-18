package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IShippingCompanyController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ShippingCompanyRequest;
import com.inventorycontrol.http.dto.response.ShippingCompanyResponse;
import com.inventorycontrol.http.mapper.ShippingCompanyMapper;
import com.inventorycontrol.service.impl.ShippingCompanyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/shippingCompany")
public class ShippingCompanyController implements IShippingCompanyController {

    private final ShippingCompanyServiceImpl service;

    @GetMapping("/findAll")
    public ResponseEntity<List<ShippingCompanyResponse>> findAll() {
        return ResponseEntity.ok().body(ShippingCompanyMapper.toResponseList(service.findAll()));
    }

    @GetMapping("/findById/{uuid}")
    public ResponseEntity<ShippingCompanyResponse> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(ShippingCompanyMapper.toResponse(service.findById(uuid)));
    }

    @PostMapping("/create")
    public ResponseEntity<ShippingCompanyResponse> save(@RequestBody ShippingCompanyRequest request) {
        var response = ShippingCompanyMapper.toModel(request);
        return ResponseEntity.ok().body(ShippingCompanyMapper.toResponse(service.save(response)));
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<ShippingCompanyResponse> update(@PathVariable UUID uuid, @RequestBody ShippingCompanyRequest request) {
        return ResponseEntity.ok().body(ShippingCompanyMapper
                .toResponse(service.update(uuid, ShippingCompanyMapper.toModel(request))));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<MessageError> delete(@PathVariable String uuid) {
        service.delete(UUID.fromString(uuid));
        var messageError = new MessageError();
        messageError.setMessage("Transportadora deletada com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
