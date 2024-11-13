package org.example.microusuario.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microusuario.dto.RequestUsuarioDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    private List<CuentaPago> cuentasPago;

    public Usuario(RequestUsuarioDTO request) {
        this.nombre = request.getNombre();
        this.apellido = request.getApellido();
        this.email = request.getEmail();
        this.telefono = request.getTelefono();
        this.rol = request.getRol();
        this.password = request.getPassword();
        this.cuentasPago = new ArrayList<>();
    }
}