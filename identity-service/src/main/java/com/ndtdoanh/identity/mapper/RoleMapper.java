package com.ndtdoanh.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ndtdoanh.identity.dto.request.RoleRequest;
import com.ndtdoanh.identity.dto.response.RoleResponse;
import com.ndtdoanh.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
