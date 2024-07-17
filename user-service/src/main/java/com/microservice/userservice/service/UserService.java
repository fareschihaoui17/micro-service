package com.microservice.userservice.service;

import com.microservice.userservice.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO addUser (UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
