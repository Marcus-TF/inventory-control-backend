package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IEntryItemController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.EntryItemRequest;
import com.inventorycontrol.http.dto.response.EntryItemResponse;
import com.inventorycontrol.http.mapper.EntryItemMapper;
import com.inventorycontrol.service.impl.EntryItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/entryItem")
public class EntryItemController implements IEntryItemController {

    private final EntryItemServiceImpl itemService;

    @GetMapping("/findAll")
    public ResponseEntity<List<EntryItemResponse>> findAll() {
        return ResponseEntity.ok().body(EntryItemMapper.toResponseList(itemService.findAll()));
    }

    @GetMapping("/findById/{uuid}")
    public ResponseEntity<EntryItemResponse> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(EntryItemMapper.toResponse(itemService.findById(uuid)));
    }

    @PostMapping("/create")
    public ResponseEntity<EntryItemResponse> save(@RequestBody EntryItemRequest request) {
        var response = EntryItemMapper.toModel(request);
        return ResponseEntity.ok().body(EntryItemMapper.toResponse(itemService.save(response)));
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<EntryItemResponse> update(@PathVariable UUID uuid, @RequestBody EntryItemRequest request) {
        return ResponseEntity.ok().body(EntryItemMapper.toResponse(itemService.update(uuid, EntryItemMapper.toModel(request))));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<MessageError> delete(@PathVariable UUID uuid) {
        itemService.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Item de entrada deletado com sucesso!");
        messageError.setStatusCode(200);
        return ResponseEntity.ok().body(messageError);
    }
}
