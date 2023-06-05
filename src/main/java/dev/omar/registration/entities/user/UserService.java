package dev.omar.registration.entities.user;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final @Lazy PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("User with the email: " + username + " does not exist"));
    }

    public Pair<Integer, String> createUserAccount(User user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            return Pair.of(401, "User with the email: " + user.getUsername() + " already exists");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error occurred while attempting to save user: " + e);
            e.printStackTrace();
            return Pair.of(500, "Error occurred while trying to register user");
        }
        return Pair.of(200, "User was registered successfully");
    }
}
