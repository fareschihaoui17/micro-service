package com.microservice.userservice.mapper;

import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.control.MappingControl.Use;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserDtoToUser(UserDTO userDTO);
    UserDTO fromUserToUserDto(User user);
}
