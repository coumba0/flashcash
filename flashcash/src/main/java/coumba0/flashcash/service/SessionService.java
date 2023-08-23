package coumba0.flashcash.service;

import coumba0.flashcash.model.User;
import coumba0.flashcash.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class SessionService {
    private final UserRepository userRepository;

    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User sessionUser() {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByMail(springUser.getUsername())
                .orElseThrow( () -> new RuntimeException("user with mail not found"));
    }

}
