package es.codeurjc.books.repositories;

import es.codeurjc.books.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByBookIdAndId(Long bookId, Long commentId);

    Collection<Comment> findByUserId(long userId);
}
