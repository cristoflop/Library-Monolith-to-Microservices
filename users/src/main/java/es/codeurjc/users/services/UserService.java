package es.codeurjc.users.services;

import es.codeurjc.users.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;

import java.util.Collection;

public interface UserService {

    Collection<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto findById(long userId);

    UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto);

    UserResponseDto delete(long userId);

}
