package es.codeurjc.users.services.impl;

import es.codeurjc.users.dtos.responses.UserCommentResponseDto;
import es.codeurjc.users.services.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {

    @Value("${books.url}")
    private String booksMicroserviceUrl;

    private RestTemplate restTemplate;

    public CommentServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Collection<UserCommentResponseDto> getComments(long userId) {
        String endpoint = "/api/v1/books/" + userId + "/comments";
        UserCommentResponseDto[] comments = this.restTemplate
                .getForObject(this.booksMicroserviceUrl + endpoint, UserCommentResponseDto[].class);
        return Arrays.asList(comments);
    }

}
