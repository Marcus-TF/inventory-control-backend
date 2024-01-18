package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IEntryController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.EntryRequest;
import com.inventorycontrol.http.dto.response.EntryResponse;
import com.inventorycontrol.http.mapper.EntryMapper;
import com.inventorycontrol.service.impl.EntryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/entry")
public class EntryController implements IEntryController {

    private final EntryServiceImpl entryService;

    @PostMapping("/create")
    public ResponseEntity<EntryResponse> save(EntryRequest request) {
        return ResponseEntity.ok().body(EntryMapper.toResponse(entryService.save(EntryMapper.toModel(request))));
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<EntryResponse> update(UUID uuid, EntryRequest request) {
        return ResponseEntity.ok().body(EntryMapper.toResponse(entryService.update(uuid, EntryMapper.toModel(request))));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EntryResponse>> findAll() {
        return ResponseEntity.ok().body(EntryMapper.entryResponseList(entryService.findAll()));
    }

    @GetMapping("/findById/{uuid}")
    public ResponseEntity<EntryResponse> findById(UUID uuid) {
        return ResponseEntity.ok().body(EntryMapper.toResponse(entryService.findById(uuid)));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<MessageError> delete(UUID uuid) {
        entryService.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Entrada deletada com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
