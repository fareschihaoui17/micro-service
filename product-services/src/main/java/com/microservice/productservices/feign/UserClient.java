package com.microservice.productservices.feign;

import com.microservice.productservices.dto.UserDTO;
import com.microservice.productservices.exception.ProjectException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    @CircuitBreaker(name = "userClient", fallbackMethod = "getUserByIdFallback")
    UserDTO getUserById(@PathVariable Long id) throws ProjectException;


    default UserDTO getUserByIdFallback(Long id, Throwable throwable) {
        return UserDTO.builder()
                .id(id)
                .firstName("fake firstName")
                .lastName("fake lastName")
                .email("fake@esprit.tn")
                .password("fake password")
                .username("fake username")
                .build();
    }

}
