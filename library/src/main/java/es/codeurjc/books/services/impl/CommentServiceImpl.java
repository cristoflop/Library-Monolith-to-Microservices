package es.codeurjc.books.services.impl;

import es.codeurjc.books.dtos.requests.CommentRequestDto;
import es.codeurjc.books.dtos.responses.CommentResponseDto;
import es.codeurjc.books.dtos.responses.UserCommentResponseDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.exceptions.BookNotFoundException;
import es.codeurjc.books.exceptions.CommentNotFoundException;
import es.codeurjc.books.exceptions.UserNotFoundException;
import es.codeurjc.books.models.Book;
import es.codeurjc.books.models.Comment;
import es.codeurjc.books.repositories.BookRepository;
import es.codeurjc.books.repositories.CommentRepository;
import es.codeurjc.books.services.CommentService;
import es.codeurjc.books.services.UserService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private Mapper mapper;
    private CommentRepository commentRepository;
    private BookRepository bookRepository;
    private UserService userService;

    public CommentServiceImpl(Mapper mapper,
                              CommentRepository commentRepository,
                              BookRepository bookRepository,
                              UserService userService) {
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        UserResponseDto user = this.userService.findByNick(commentRequestDto.getUserNick());
        if (user == null) new UserNotFoundException();

        Comment comment = this.mapper.map(commentRequestDto, Comment.class);
        comment.setBook(book);
        comment.setUserId(user.getId());
        comment = this.commentRepository.save(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public CommentResponseDto deleteComment(long bookId, long commentId) {
        Comment comment = this.commentRepository.findByBookIdAndId(bookId, commentId)
                .orElseThrow(CommentNotFoundException::new);
        this.commentRepository.delete(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public Collection<UserCommentResponseDto> getComments(long userId) {
        return this.commentRepository.findByUserId(userId).stream()
                .map(comment -> this.mapper.map(comment, UserCommentResponseDto.class))
                .collect(Collectors.toList());
    }

}
