package com.sparta.websocketpractice.repository;


import com.sparta.websocketpractice.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);

    Optional<User> findByUsername(String email);
    

}
