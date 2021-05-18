package es.codeurjc.users.services;

import es.codeurjc.users.dtos.responses.UserCommentResponseDto;

import java.util.Collection;

public interface CommentService {

    Collection<UserCommentResponseDto> getComments(long userId);

}
