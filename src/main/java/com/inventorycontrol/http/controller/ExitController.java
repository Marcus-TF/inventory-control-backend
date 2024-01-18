package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IExitController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ExitRequest;
import com.inventorycontrol.http.dto.response.ExitResponse;
import com.inventorycontrol.http.mapper.ExitMapper;
import com.inventorycontrol.service.impl.ExitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/exit")
public class ExitController implements IExitController {

    private final ExitServiceImpl exitService;

    @PostMapping("/create")
    public ResponseEntity<ExitResponse> save(ExitRequest request) {
        return ResponseEntity.ok().body(ExitMapper.toResponse(exitService.save(ExitMapper.toModel(request))));
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<ExitResponse> update(UUID uuid, ExitRequest request) {
        return ResponseEntity.ok().body(ExitMapper.toResponse(exitService.update(uuid, ExitMapper.toModel(request))));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ExitResponse>> findAll() {
        return ResponseEntity.ok().body(ExitMapper.toResponseList(exitService.findAll()));
    }

    @GetMapping("/findById/{uuid}")
    public ResponseEntity<ExitResponse> findById(UUID uuid) {
        return ResponseEntity.ok().body(ExitMapper.toResponse(exitService.findById(uuid)));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<MessageError> delete(UUID uuid) {
        exitService.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Saída deletada com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
