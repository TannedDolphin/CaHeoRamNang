package cayxanh.GreencareTest.mapper;

import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
