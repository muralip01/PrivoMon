package PrivoMon.Users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(String username, String password) {
        // Check if the username  is already taken
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Create a new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // Save the user in the database
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        // Check if the user exists
        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Check if the password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return user;
    }

    // Add methods for updating user profile, changing password, etc.
}