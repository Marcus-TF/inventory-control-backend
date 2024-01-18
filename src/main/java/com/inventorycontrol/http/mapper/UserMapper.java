package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.UserInsertRequest;
import com.inventorycontrol.http.dto.response.UserInsertResponse;
import com.inventorycontrol.http.dto.response.UserResponse;
import com.inventorycontrol.model.ProfileModel;
import com.inventorycontrol.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserModel insertRequestToModel(UserInsertRequest request) {
        var response = new UserModel();
        BeanUtils.copyProperties(request, response);
        response.setProfileList(UserMapper.toModelList(request.getProfileIds()));

        return response;
    }


    public static UserInsertResponse modelToInsertResponse(UserModel model) {
        var response = new UserInsertResponse();
        BeanUtils.copyProperties(model, response);
        response.setProfileResponseList(ProfileMapper.profileResponseList(model.getProfileList()));

        return response;
    }

    public static UserResponse modelToResponse(UserModel model) {
        var response = new UserResponse();
        BeanUtils.copyProperties(model, response);
        response.setProfileResponseList(ProfileMapper.profileResponseList(model.getProfileList()));

        return response;
    }

    public static List<UserResponse> userResponseList(List<UserModel> perfilList) {
        var perfilResponse = new ArrayList<UserResponse>();
        perfilResponse.addAll(perfilList.stream().map(UserMapper::modelToResponse).collect(Collectors.toList()));
        return perfilResponse;
    }

    //Many to many
    public static List<ProfileModel> toModelList(List<UUID> uuidList) {
        return uuidList.stream().map(UserMapper::createProfile).collect(Collectors.toList());
    }

    public static ProfileModel createProfile(UUID uuid) {
        return ProfileModel.builder()
                .profileId(uuid)
                .build();
    }
}
