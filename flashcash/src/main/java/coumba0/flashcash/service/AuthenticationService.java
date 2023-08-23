package coumba0.flashcash.service;

import java.util.ArrayList;
import java.util.Optional;

import coumba0.flashcash.model.User;
import coumba0.flashcash.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository
                .findUserByMail(s);


        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getMail(), user.get().getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException(s);
    }

}
