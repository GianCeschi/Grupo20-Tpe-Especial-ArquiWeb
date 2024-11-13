package org.example.microusuario.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microusuario.dto.CuentaPagoDTO;
import org.example.microusuario.dto.RequestCuentaPagoDTO;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class CuentaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCuentaPago;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private LocalDate fechaAlta;

    @Column( nullable = false)
    private double saldo;

    @ManyToMany(mappedBy = "cuentaPago")
    private List<Usuario> usuarios;

    public CuentaPago(RequestCuentaPagoDTO requestCuentaPagoDTO) {
        this.nombre = requestCuentaPagoDTO.getNombre();
        this.saldo = requestCuentaPagoDTO.getSaldo();
        this.fechaAlta = LocalDate.now();
    }

}