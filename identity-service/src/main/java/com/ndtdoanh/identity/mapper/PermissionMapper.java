package com.ndtdoanh.identity.mapper;

import org.mapstruct.Mapper;

import com.ndtdoanh.identity.dto.request.PermissionRequest;
import com.ndtdoanh.identity.dto.response.PermissionResponse;
import com.ndtdoanh.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
