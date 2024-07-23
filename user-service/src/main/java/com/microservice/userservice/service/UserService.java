package com.microservice.userservice.service;

import com.microservice.userservice.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO addUser (UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
    UserDTO updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);
}
