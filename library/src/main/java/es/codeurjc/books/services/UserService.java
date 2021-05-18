package es.codeurjc.books.services;

import es.codeurjc.books.dtos.responses.UserResponseDto;

public interface UserService {

    UserResponseDto findByNick(String nick);

}
