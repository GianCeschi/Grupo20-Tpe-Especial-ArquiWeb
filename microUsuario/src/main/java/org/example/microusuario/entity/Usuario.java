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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<CuentaPago> cuentasPago = new ArrayList<CuentaPago>();

    public Usuario(RequestUsuarioDTO request) {
        this.nombre = request.getNombre();
        this.apellido = request.getApellido();
        this.email = request.getEmail();
        this.telefono = request.getTelefono();
        this.rol = request.getRol();
        this.password = request.getPassword();
        this.cuentasPago = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                ", cuentasPago=" + cuentasPago +
                '}';
    }
}