package org.example.microusuario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.microusuario.dto.CuentaPagoDTO;
import org.example.microusuario.dto.RequestCuentaPagoDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.service.CuentaPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class CuentaPagoController {

    @Autowired
    private CuentaPagoServicio cuentaPagoService;

    public CuentaPagoController(CuentaPagoServicio cuentaPagoService) {
        this.cuentaPagoService = cuentaPagoService;
    }

    @Operation(
            summary = "Obtener cuentaPagos",
            description = "Este endpoint permite obtener cuentaPagos",
            operationId = "getCuentaPagos",
            tags = {"Cuentapago", "ObtenerTodas"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cuentapago encontrada"),
                    @ApiResponse(responseCode = "404", description = "Cuentapago no encontrada")
            }
    )
    @GetMapping("/cuentapagos")
    public Iterable<CuentaPagoDTO> getAll() {
        return cuentaPagoService.getAll();
    }

    @Operation(
            summary = "Obtener cuentaPago",
            description = "Este endpoint permite obtener una cuentapago segun su ID",
            operationId = "getCuentaPago",
            tags = {"Cuentapago", "ObtenerCP"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cuentapago encontrada"),
                    @ApiResponse(responseCode = "404", description = "Cuentapago no encontrada")
            }
    )
    @GetMapping("/cuentapagos/{id}")
    public ResponseEntity<CuentaPagoDTO> getById(@PathVariable Long id) {
        return cuentaPagoService.getById(id);
    }

    @Operation(
            summary = "Dar de alta una cuentaPago",
            description = "Este endpoint permite crear una cuentaPago",
            operationId = "saveCuentaPago",
            tags = {"Cuentapago", "CrearCP"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cuentapago creada con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PostMapping("/cuentapagos/{idUsuario}")
    public ResponseEntity<UsuarioDTO> save(@RequestBody RequestCuentaPagoDTO request, @PathVariable Long idUsuario) {
        final var result =  this.cuentaPagoService.save(request, idUsuario);
        return ResponseEntity.ok().body(result);
    }


    @Operation(
            summary = "Eliminar una cuentapago",
            description = "Este endpoint permite eliminar una cuentapago segun su ID",
            operationId = "deleteCuentapago",
            tags = {"Usuario", "EliminarCP"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cuentapago eliminada con éxito"),
                    @ApiResponse(responseCode = "404", description = "Cuentapago no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @DeleteMapping("/cuentapagos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return cuentaPagoService.delete(id);
    }

    @Operation(
            summary = "Actualizar una cuentapago",
            description = "Este endpoint actualiza los datos de una cuentapago existente especificando su ID.",
            operationId = "updateCuentapago",
            tags = {"Cuentapago", "ActualizarCP"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cuentapago actualizada con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
                    @ApiResponse(responseCode = "404", description = "CuentaPago no encontrada")
            }
    )
    @PutMapping("/cuentapagos/{id}")
    public ResponseEntity<CuentaPagoDTO> update(@PathVariable Long id, @RequestBody RequestCuentaPagoDTO request) throws Exception {
        var resultado =  cuentaPagoService.update(id,request);
        return ResponseEntity.ok().body(resultado);
    }

    @Operation(
            summary = "Actualizar una cuentapago segun monto",
            description = "Este endpoint actualiza los datos de una cuentapago existente especificando su ID.Descuenta monto del saldo y se habilito que quede saldo negativo",
            operationId = "updateSaldoCuentapago",
            tags = {"Cuentapago", "ActualizarSaldoCP"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cuentapago actualizada con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
                    @ApiResponse(responseCode = "404", description = "CuentaPago no encontrada")
            }
    )
    @PutMapping("/cuentapagos/pagarViaje/{id}")
    public void pagarViaje(@PathVariable Long id,@RequestParam Double montoPagar) {
        cuentaPagoService.pagarViaje(id, montoPagar);
    }
}