package az.baku.card.repository;

import az.baku.card.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    @Query("FROM User u where u.username= :username")
    Optional<User> findByUsername(@Param(value = "username") String username);
}
