package com.microservice.userservice.service;

import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.exception.ProjectException;
import com.microservice.userservice.mapper.UserMapper;
import com.microservice.userservice.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final String USER_NOT_FOUND ="User not found";
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        var userJpa = userRepository.save(userMapper.fromUserDtoToUser(userDTO));
        return userMapper.fromUserToUserDto(userJpa);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::fromUserToUserDto).toList();
    }

    @Override
    public UserDTO getUserById(String id) {
        return userMapper.fromUserToUserDto(getUserJpa(id));
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        var userToBeUpdatedJpa =getUserJpa(id);
        userToBeUpdatedJpa.setUsername(userDTO.getUsername());
        userToBeUpdatedJpa.setEmail(userDTO.getEmail());
        userToBeUpdatedJpa.setFirstName(userDTO.getFirstName());
        userToBeUpdatedJpa.setPassword(userDTO.getPassword());
        userToBeUpdatedJpa.setLastName(userDTO.getLastName());
        return userMapper.fromUserToUserDto(userRepository.save(userToBeUpdatedJpa));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(getUserJpa(id));
    }

    private User getUserJpa(String id) {
        return userRepository.findById(id).orElseThrow(()->new ProjectException(HttpStatus.NOT_FOUND,USER_NOT_FOUND));
    }
}
