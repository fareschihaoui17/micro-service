package com.microservice.productservices.resttemplate;

import com.microservice.productservices.dto.UserDTO;
import com.microservice.productservices.exception.ProjectException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    public UserDTO getUserById(Long id) throws ProjectException {
        var url = UriComponentsBuilder
                .fromUriString("http://localhost:8080/api/users/{id}")
                .buildAndExpand(id)
                .toUriString();

        try {
            return restTemplate.getForObject(url, UserDTO.class);
        } catch (Exception e) {
            throw new ProjectException(HttpStatus.NOT_FOUND,"Failed to fetch user data");
        }
    }
}
