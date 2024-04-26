package com.inventorycontrol.http.controller;

import com.inventorycontrol.http.controller.interfaces.IProfileController;
import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ProfileRequest;
import com.inventorycontrol.http.dto.response.ProfileResponse;
import com.inventorycontrol.http.mapper.ProfileMapper;
import com.inventorycontrol.service.impl.ProfileServiceImpl;
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
@RequestMapping("/profile")
public class ProfileController implements IProfileController {

    private final ProfileServiceImpl profileService;

    @Override
    public ResponseEntity<ProfileResponse> save(ProfileRequest request) {
        var response = ProfileMapper.toModel(request);
        return ResponseEntity.ok().body(ProfileMapper.toResponse(profileService.save(response)));
    }

    @Override
    public ResponseEntity<ProfileResponse> update(UUID uuid, ProfileRequest request) {
        var response = ProfileMapper.toModel(request);
        return ResponseEntity.ok().body(ProfileMapper.toResponse(profileService.update(uuid, response)));
    }

    @Override
    public ResponseEntity<List<ProfileResponse>> findAll() {
        return ResponseEntity.ok().body(ProfileMapper.profileResponseList(profileService.findAll()));
    }

    @Override
    public ResponseEntity<ProfileResponse> findById(UUID uuid) {
        return ResponseEntity.ok().body(ProfileMapper.toResponse(profileService.findById(uuid)));
    }

    @Override
    public ResponseEntity<MessageError> delete(UUID uuid) {
        profileService.delete(uuid);
        var messageError = new MessageError();
        messageError.setMessage("Perfil deletado com sucesso!");
        messageError.setStatusCode(Integer.valueOf(200));
        return ResponseEntity.ok().body(messageError);
    }
}
