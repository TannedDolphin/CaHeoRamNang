package cayxanh.GreencareTest.mapper;

import cayxanh.GreencareTest.dto.request.RoleRequest;
import cayxanh.GreencareTest.dto.response.RoleResponse;
import cayxanh.GreencareTest.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}