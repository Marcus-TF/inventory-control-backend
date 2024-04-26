package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IUserController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.UserInsertRequest;
import com.inventorycontrol.http.dto.response.UserInsertResponse;
import com.inventorycontrol.http.dto.response.UserResponse;
import com.inventorycontrol.http.mapper.UserMapper;
import com.inventorycontrol.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController implements IUserController {

    private final UserServiceImpl userService;

    @Override
    public ResponseEntity<UserInsertResponse> save(UserInsertRequest request) {
        var response = UserMapper.insertRequestToModel(request);
        return ResponseEntity.ok().body(UserMapper.modelToInsertResponse(userService.save(response)));
    }

    @Override
    public ResponseEntity<UserInsertResponse> update(UUID uuid, UserInsertRequest request) {
        return ResponseEntity.ok().body(UserMapper.modelToInsertResponse(userService.update(uuid, UserMapper.insertRequestToModel(request))));
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(UserMapper.userResponseList(userService.findAll()));
    }

    @Override
    public ResponseEntity<UserResponse> findById(UUID uuid) {
        return ResponseEntity.ok().body(UserMapper.modelToResponse(userService.findById(uuid)));
    }

    @Override
    public ResponseEntity<MessageError> delete(UUID uuid) {
        userService.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Usuário deletado com sucesso!");
        messageError.setStatusCode(Integer.valueOf(200));
        return ResponseEntity.ok().body(messageError);
    }
}
