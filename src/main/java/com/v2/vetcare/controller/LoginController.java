package com.v2.vetcare.controller;

import com.v2.vetcare.model.Client;
import com.v2.vetcare.model.VetUser;
import com.v2.vetcare.request.LoginRequest;
import com.v2.vetcare.service.cliente.IClientService;
import com.v2.vetcare.service.usuario.IVetUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/login")
public class LoginController {

    private final IVetUserService vetUserService;


    /*
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

     */

    @CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
    @PostMapping("/log")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        // Find VetUser by username and password
        VetUser vetUser = vetUserService.findVetUserByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (vetUser != null) {
            // Determine user type based on associated client or employee
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful!");

            if (vetUser.getClient() != null) {
                session.setAttribute("userType", "CLIENT");
                session.setAttribute("userId", vetUser.getClient().getId());
                response.put("userType", "CLIENT");
                response.put("userDetails", vetUser.getClient());
            } else if (vetUser.getEmployee() != null) {
                session.setAttribute("userType", "EMPLOYEE");
                session.setAttribute("userId", vetUser.getEmployee().getId());
                response.put("userType", "EMPLOYEE");
                response.put("userDetails", vetUser.getEmployee());
            }

            // Store VetUser in session for additional authentication checks
            session.setAttribute("vetUser", vetUser);

            return ResponseEntity.ok(response);
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

    /*
    @CrossOrigin("http://localhost:63342/")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

     */

    @CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logout successful");
    }
}
