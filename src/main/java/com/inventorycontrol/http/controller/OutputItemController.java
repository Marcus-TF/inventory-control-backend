package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IOutputItemController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.OutputItemRequest;
import com.inventorycontrol.http.dto.response.OutputItemResponse;
import com.inventorycontrol.http.mapper.OutputItemMapper;
import com.inventorycontrol.service.impl.OutputItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/outputItem")
public class OutputItemController implements IOutputItemController {

    private final OutputItemServiceImpl outputItemService;


    @PostMapping("/create")
    public ResponseEntity<OutputItemResponse> save(OutputItemRequest request) {
        var response = OutputItemMapper.toModel(request);
        return ResponseEntity.ok().body(OutputItemMapper.toResponse(outputItemService.save(response)));
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<OutputItemResponse> update(UUID uuid, OutputItemRequest request) {
        return ResponseEntity.ok().body(OutputItemMapper.toResponse(outputItemService.update(uuid, OutputItemMapper.toModel(request))));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OutputItemResponse>> findAll() {
        return ResponseEntity.ok().body(OutputItemMapper.toResponseList(outputItemService.findAll()));
    }

    @GetMapping("/findById/{uuid}")
    public ResponseEntity<OutputItemResponse> findById(UUID uuid) {
        return ResponseEntity.ok().body(OutputItemMapper.toResponse(outputItemService.findById(uuid)));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<MessageError> delete(UUID uuid) {
        outputItemService.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Item de saída deletado com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
