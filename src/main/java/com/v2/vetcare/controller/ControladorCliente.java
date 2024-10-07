package com.v2.vetcare.controller;

import com.v2.vetcare.exceptions.ResourceNotFoundException;
import com.v2.vetcare.model.Cliente;
import com.v2.vetcare.request.SolicitudActualizarCliente;
import com.v2.vetcare.request.SolicitudAgregarCliente;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.cliente.IServicioCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/clientes")
public class ControladorCliente {

    private final IServicioCliente servicioCliente;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> obtenerTodosLosClientes(){
        List<Cliente> clientes = servicioCliente.obtenerTodosLosClientes();
        return ResponseEntity.ok(new ApiResponse("Exito",clientes));
    }


    @GetMapping("/cliente/{id}/clientes")
    public ResponseEntity<ApiResponse> obtenerClientePorId(@PathVariable Long id){
        try {
            Cliente cliente = servicioCliente.obtenerClientePorId(id);
            return ResponseEntity.ok(new ApiResponse("Exito!",cliente));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/registrar")
    public  ResponseEntity<ApiResponse> registrarCliente(@RequestBody SolicitudAgregarCliente solicitud){
        try {
            Cliente cliente = servicioCliente.registrarCliente(solicitud);
            return  ResponseEntity.ok(new ApiResponse("Cliente Registrado!", cliente));
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse> actualizarCliente(@RequestBody SolicitudActualizarCliente solicitud){
        try {
            Cliente cliente = servicioCliente.actualizarCliente(solicitud);
            return  ResponseEntity.ok(new ApiResponse("Actualizacion exitosa!", cliente));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/eliminar/{idCliente}/clientes")
    public ResponseEntity<ApiResponse> eliminarCliente(@PathVariable Long idCliente){
        try {
            servicioCliente.eliminarCliente(idCliente);
            return  ResponseEntity.ok(new ApiResponse("Cliente Eliminado!", idCliente));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }




}
