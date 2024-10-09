package com.v2.vetcare.controller;

import com.v2.vetcare.model.User;
import com.v2.vetcare.request.LoginRequest;
import com.v2.vetcare.service.usuario.IUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/iniciar-sesion")
public class LoginController {

    private final IUserService userService;

    @CrossOrigin("http://localhost:63342/")
    @PostMapping("/entrar")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        User usu = userService.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (usu != null) {
            session.setAttribute("user", usu);
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
