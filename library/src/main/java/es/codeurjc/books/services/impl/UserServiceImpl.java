package es.codeurjc.books.services.impl;

import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.services.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Value("${users.url}")
    private String usersMicroserviceUrl;

    private RestTemplate restTemplate;

    private Mapper mapper;

    public UserServiceImpl(Mapper mapper) {
        this.mapper = mapper;
        this.restTemplate = new RestTemplate();
    }

    public UserResponseDto findByNick(String nick) {
        String endpoint = "/api/v1/users/nick/" + nick;
        return this.restTemplate.getForObject(this.usersMicroserviceUrl + endpoint, UserResponseDto.class);
    }

}
