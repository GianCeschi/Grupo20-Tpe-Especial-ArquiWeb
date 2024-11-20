package org.example.microviaje.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.microviaje.dto.ReporteViajeMonopatinDTO;
import org.example.microviaje.dto.RequestViajeDTO;
import org.example.microviaje.dto.ViajeDTO;
import org.example.microviaje.entity.Viaje;
import org.example.microviaje.service.ViajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    @Autowired
    private ViajeServicio viajeServicio;

    public ViajeController(ViajeServicio viajeServicio) {
        this.viajeServicio = viajeServicio;
    }
    @Operation(
            summary = "Registrar el inicio de un viaje.",
            description = "Un usuario autenticado con rol USUARIO, comienza a utilizar un monopatín dando inicio a un viaje, el cual se crea desde este endpoint.",
            operationId = "saveViaje",
            tags = {"Viaje", "Crear"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Viaje creado correctamente")
            }
    )
    @PostMapping("")
    public ResponseEntity<ViajeDTO> save(@RequestBody RequestViajeDTO request) {
        final var result =  this.viajeServicio.save(request);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
            summary = "Modificar datos de un viaje.",
            description = "Un usuario autenticado con rol USUARIO, actualizar la información de su viaje.",
            operationId = "updateViaje",
            tags = {"Viaje"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa")
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ViajeDTO> update(@PathVariable Long id, @RequestBody RequestViajeDTO nuevoViaje) throws Exception {
        var resultado = viajeServicio.update(id, nuevoViaje);
        return ResponseEntity.ok().body(resultado);
    }

    @Operation(
            summary = "Obtener listado de viajes.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener un listado de todos los viajes generados en la aplicacion.",
            operationId = "getAllViajes",
            tags = {"Viaje", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("")
    public Iterable<Viaje> getAll() {
        return viajeServicio.getAll();
    }

    @Operation(
            summary = "Obtener detalle de un viaje.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener la información detallada de un viaje de la aplicacion, a partir de un idViaje.",
            operationId = "getOneViaje",
            tags = {"Viaje", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Viaje> get(@PathVariable Long id) {
        return viajeServicio.getById(id);
    }

    @Operation(
            summary = "Eliminar un viaje",
            description = "Un usuario autenticado con rol ADMIN, puede eliminar un viaje de la aplicacion indicando el idViaje.",
            operationId = "deleteViaje",
            tags = {"Viaje", "Eliminar"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Viaje eliminado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){return viajeServicio.delete(id);}


    @Operation(
            summary = "Obtener reporte  de monopatines con mas de x viajes en un determinado año.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener un reporte de monopatines con mas de una cantidad dada de viajes durante un año dado.",
            operationId = "getReportePorViajesEnUnAnio",
            tags = {"Viaje", "Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/viajesPorMonopatin/{anio}/{cant}")
    public Iterable<ReporteViajeMonopatinDTO> getViajesPorMonopatin(@PathVariable int anio, @PathVariable int cant) throws Exception{
        return viajeServicio.getViajesPorMonopatin(anio, cant);
    }

    @Operation(
            summary = "Obtener reporte  de facturacion.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener un reporte del total facturado en un rango de meses de cierto año.",
            operationId = "getReportePorViajesEnUnAnio",
            tags = {"Facturacion", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/facturacion/{mes1}/{anio1}/{mes2}/{anio2}")
    public Double getTotalFacturacion(@PathVariable int mes1, @PathVariable int anio1,
                                      @PathVariable int mes2, @PathVariable int anio2) throws Exception {
        return viajeServicio.getTotalFacturacion(mes1,anio1,mes2,anio2);
    }
}
