package com.inventorycontrol.http.mapper;

import com.inventorycontrol.http.dto.request.ProfileRequest;
import com.inventorycontrol.http.dto.response.ProfileResponse;
import com.inventorycontrol.model.ProfileModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {

    public static ProfileModel toModel(ProfileRequest request) {
        var response = new ProfileModel();
        BeanUtils.copyProperties(request, response);

        return response;
    }

    public static ProfileResponse toResponse(ProfileModel model) {
        var response = new ProfileResponse();
        BeanUtils.copyProperties(model, response);

        return response;
    }

    public static List<ProfileResponse> profileResponseList(List<ProfileModel> profileModelList) {
        var response = new ArrayList<ProfileResponse>();
        response.addAll(profileModelList.stream().map(ProfileMapper::toResponse).collect(Collectors.toList()));

        return response;
    }
}
