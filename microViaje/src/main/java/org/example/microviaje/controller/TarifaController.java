package org.example.microviaje.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.microviaje.dto.TarifaDTO;
import org.example.microviaje.entity.Tarifa;
import org.example.microviaje.service.TarifaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/viajes")
public class TarifaController {
    @Autowired
    private TarifaServicio tarifaServicio;

    public TarifaController (TarifaServicio tarifaServicio) {
        this.tarifaServicio = tarifaServicio;
    }

    @Operation(
            summary = "Obtener tarifa",
            description = "Este endpoint permite obtener una tarifa segun su ID",
            operationId = "getTarifa",
            tags = {"Tarifa", "ObtenerTarifa"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada")
            }
    )
    @GetMapping("/tarifas/{id}")
    public ResponseEntity<TarifaDTO> getById(@PathVariable Long id) {
        return tarifaServicio.getById(id);
    }


    @Operation(
            summary = "Obtener tarifas",
            description = "Este endpoint permite obtener tarifas",
            operationId = "getTarifas",
            tags = {"Tarifa", "ObtenerTodasTarifas"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada")
            }
    )
    @GetMapping("/tarifas")
    public Iterable<TarifaDTO> getAll(){
        return tarifaServicio.getAll();
    }

    @Operation(
            summary = "Dar de alta una tarifa",
            description = "Este endpoint permite crear una tarifa",
            operationId = "saveTarifa",
            tags = {"Tarifa", "CrearTarifa"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarifa creada con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PostMapping("/tarifas")
    public ResponseEntity<TarifaDTO> save(@RequestBody TarifaDTO request) {
        final var result =  this.tarifaServicio.save(request);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
            summary = "Eliminar una tarifa",
            description = "Este endpoint permite eliminar una tarifa segun su ID",
            operationId = "deleteTarifa",
            tags = {"Tarifa", "EliminarTarifa"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tarifa eliminada con éxito"),
                    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @DeleteMapping("/tarifas/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return tarifaServicio.delete(id);
    }

    @Operation(
            summary = "Actualizar una tarifa",
            description = "Este endpoint actualiza los datos de una tarifa existente especificando su ID.",
            operationId = "updateTarifa",
            tags = {"Tarifa", "ActualizarTarifa"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Trifa actualizada con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
                    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada")
            }
    )
    @PutMapping("/tarifas/{id}")
    public ResponseEntity<TarifaDTO> update(@PathVariable Long id, @RequestBody TarifaDTO tarifaNueva) throws Exception {
        var resultado = tarifaServicio.update(id, tarifaNueva);
        return ResponseEntity.ok().body(resultado);
    }

    @Operation(
            summary = "Actualizar valor tarifa",
            description = "Este endpoint actualiza los datos de una tarifa existente especificando su ID.Se realiza un ajuste de precio, a partir de cierta fecha el sistema habilita los nuevos precios.",
            operationId = "updateTarifaPrecio",
            tags = {"Tarifa", "ActualizarTarifa"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Trifa actualizada con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
                    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada")
            }
    )
    @PutMapping("/tarifas/ajustar/{id}")
    public ResponseEntity<Tarifa> ajustarTarifa(@PathVariable Long id, @RequestParam double nuevoValor,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nuevaFechaVigencia) {

        var resultado = tarifaServicio.ajustarTarifa(id,nuevoValor, nuevaFechaVigencia);
        return ResponseEntity.ok().body(resultado);
    }

}
