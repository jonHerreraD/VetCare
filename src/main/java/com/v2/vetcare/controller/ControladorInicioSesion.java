package com.v2.vetcare.controller;

import com.v2.vetcare.model.Usuario;
import com.v2.vetcare.request.SolicitudInicioSesion;
import com.v2.vetcare.service.usuario.IServicioUsuario;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/iniciar-sesion")
public class ControladorInicioSesion {

    private final IServicioUsuario servicioUsuario;

    @PostMapping("/entrar")
    public ResponseEntity<String> login(@RequestBody SolicitudInicioSesion solicitud, HttpSession session) {
        Usuario usu = servicioUsuario.findByUsernameAndPassword(solicitud.getNombreUsuario(), solicitud.getContrasenia());
        if (usu != null) {
            session.setAttribute("user", usu);
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
