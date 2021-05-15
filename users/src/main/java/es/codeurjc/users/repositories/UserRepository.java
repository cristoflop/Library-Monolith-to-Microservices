package es.codeurjc.users.repositories;

import es.codeurjc.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNick(String nick);

    Optional<User> findByNick(String nick);

}
