package com.project.banque.services.interf;

import com.project.banque.entities.User;
import com.project.banque.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override public UserDetails loadUserByUsername(String u) {
        return repo.findByUsername(u)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User save(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public boolean validate(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}
