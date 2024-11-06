package com.v2.vetcare.controller;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.VetUser;
import com.v2.vetcare.request.LoginRequest;
import com.v2.vetcare.service.cliente.IClientService;
import com.v2.vetcare.service.usuario.IVetUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/login")
public class LoginController {

    private final IVetUserService vetUserService;

    @CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
    @PostMapping("/log")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        VetUser usu = vetUserService.findVetUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (usu != null) {
            session.setAttribute("vetUser", usu);
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
    @GetMapping("/current-user")
    public ResponseEntity<VetUser> getCurrentUser(HttpSession session){
        VetUser vetUser = (VetUser) session.getAttribute("vetUser");
        return (vetUser != null)
                ? ResponseEntity.ok(vetUser)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @CrossOrigin("http://localhost:63342/")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
