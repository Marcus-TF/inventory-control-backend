package com.inventorycontrol.service.impl;

import com.inventorycontrol.model.ProfileModel;
import com.inventorycontrol.repository.ProfileRepository;
import com.inventorycontrol.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<ProfileModel> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public ProfileModel findById(UUID uuid) {
        return profileRepository.findById(uuid).orElseThrow(() -> new NoResultException("Perfil não encontrado."));
    }

    @Override
    public ProfileModel save(ProfileModel profileModel) {
        return profileRepository.save(profileModel);
    }

    @Override
    public ProfileModel update(UUID uuid, ProfileModel profileModel) {
        profileRepository.findById(uuid).orElseThrow(() -> new NoResultException("Perfil não encontrado."));
        profileModel.setProfileId(uuid);
        profileRepository.save(profileModel);
        return profileModel;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = profileRepository.findById(uuid).orElseThrow(() -> new NoResultException("Perfil não encontrado."));
        profileRepository.delete(response);
        return uuid;
    }
}
