package com.project.banque.controllers;

import com.project.banque.dto.AuthRequest;
import com.project.banque.dto.AuthResponse;
import com.project.banque.entities.User;
import com.project.banque.security.JwtService;
import com.project.banque.services.interf.UserService;
import com.project.banque.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final UserService us;
    private final JwtService jwtSvc;
    private final AuthenticationManager authManager;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody AuthRequest r) {
        User u = new User();
        u.setUsername(r.getUsername());
        u.setPassword(r.getPassword());
        us.save(u);
        String token = jwtSvc.generateToken(u);
        return ResponseEntity.ok(ApiResponse.success(token, "Utilisateur créé avec succès"));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest r) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(r.getUsername(), r.getPassword()));
        UserDetails ud = us.loadUserByUsername(r.getUsername());
        return ResponseEntity.ok(ApiResponse.success(new AuthResponse(jwtSvc.generateToken(ud)), "Connexion réussie avec succès"));
    }
}

