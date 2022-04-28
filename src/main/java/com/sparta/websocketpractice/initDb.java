package com.sparta.websocketpractice;

import com.sparta.websocketpractice.domain.User;
import com.sparta.websocketpractice.repository.UserRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public void dbInit1() {
            User user1 = new User("user1", passwordEncoder.encode("test"), "nickname1");
            userRepository.save(user1);

            User user2 = new User("user2", passwordEncoder.encode("test"), "nickname2");
            userRepository.save(user2);
        }
    }
}