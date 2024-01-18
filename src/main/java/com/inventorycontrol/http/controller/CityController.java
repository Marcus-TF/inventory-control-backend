package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.ICityController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.CityRequest;
import com.inventorycontrol.http.dto.response.CityResponse;
import com.inventorycontrol.http.mapper.CityMapper;
import com.inventorycontrol.service.impl.CityServiceImpl;
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
@RequestMapping("/city")
public class CityController implements ICityController {

    private final CityServiceImpl cityServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<CityResponse>> findAll() {
        return ResponseEntity.ok().body(CityMapper.toResponseList(cityServiceImpl.findAll()));
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityResponse> findById(@PathVariable String cityId) {
        return ResponseEntity.ok().body(CityMapper.toResponse(cityServiceImpl.findById(UUID.fromString(cityId))));
    }

    @PostMapping
    public ResponseEntity<CityResponse> save(@RequestBody @Valid CityRequest cityRequest) {
        return ResponseEntity.ok().body(CityMapper.toResponse(cityServiceImpl.save(CityMapper.toModel(cityRequest))));
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityResponse> update(@RequestBody @Valid CityRequest cityRequest,
                                               @PathVariable String cityId) {
        return ResponseEntity.ok().body(
                CityMapper.toResponse(cityServiceImpl.update(CityMapper.toModel(cityRequest), UUID.fromString(cityId))));
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<MessageError> delete(@PathVariable String clientId) {
        cityServiceImpl.delete(UUID.fromString(clientId));
        var messageError = new MessageError();
        messageError.setMessage("Cidade deletada com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
