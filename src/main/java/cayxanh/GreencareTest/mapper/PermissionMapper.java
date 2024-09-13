package cayxanh.GreencareTest.mapper;

import cayxanh.GreencareTest.dto.request.PermissionRequest;
import cayxanh.GreencareTest.dto.response.PermissionResponse;
import cayxanh.GreencareTest.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}