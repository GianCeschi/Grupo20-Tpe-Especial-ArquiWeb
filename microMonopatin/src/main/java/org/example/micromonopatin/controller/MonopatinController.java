package org.example.micromonopatin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.micromonopatin.DTO.MonopatinDTO;
import org.example.micromonopatin.service.MonopatinServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinServicio monopatinServicio;

    public MonopatinController(MonopatinServicio monopatinServicio) {
        this.monopatinServicio = monopatinServicio;
    }



    //        ******************* METODOS  PARA ABM DE MONOPATINES *******************

    @Operation(
            summary = "Dar de alta un monopatin",
            description = "Un usuario autenticado con rol ADMIN, puede dar de alta un monopatin en la aplicacion.",
            operationId = "createMonopatin",
            tags = {"Monopatin", "Crear"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Monopatín creado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PostMapping("")
    public MonopatinDTO createMonopatin(@RequestBody MonopatinDTO monopatinDTO) {
        return monopatinServicio.saveMonopatin(monopatinDTO);
    }

    @Operation(
            summary = "Eliminar un monopatin",
            description = "Un usuario autenticado con rol ADMIN, puede eliminar un monopatin de la aplicacion indicando el idMonopatin.",
            operationId = "deleteMonopatin",
            tags = {"Monopatin", "Eliminar"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Monopatín eliminado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonopatin(@PathVariable String id) {
        if (monopatinServicio.getMonopatinById(id).isPresent()) {
            monopatinServicio.deleteMonopatin(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    //        ******************* METODOS  PARA RECUPERAR MONOPATINES *******************

    @Operation(
            summary = "Obtener listado de monopatines.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener un listado de todos los monopatines de la aplicacion.",
            operationId = "getAllMonopatin",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("")
    public List<MonopatinDTO> getAllMonopatines() {
        return monopatinServicio.getAllMonopatines();
    }

    @Operation(
            summary = "Obtener detalle de un monopatin.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener la información detallada de un monopatin de la aplicacion.",
            operationId = "getOneMonopatin",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MonopatinDTO> getMonopatinById(@PathVariable String id) {
        return monopatinServicio.getMonopatinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    //        ******************* METODO PARA ASIGNARLE UNA PARADA A UN MONOPATIN *******************
    @Operation(
            summary = "Asignar parada a un monopatin.",
            description = "Un usuario autenticado con rol ADMIN, puede asignar una parada específica a un monopatin. Es decir, ubica el monopatín en la parada de referencia.",
            operationId = "ubicarMonopatinEnParada",
            tags = {"Monopatin", "Parada"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PutMapping("/{idMonopatin}/asignarParada/{idParada}")
    public ResponseEntity<Void> asignarParada(@PathVariable String idMonopatin, @PathVariable String idParada) {
        try {
            monopatinServicio.asignarParada(idMonopatin, idParada);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //        ******************* METODOS  PARA MANTENIMIENTO DE MONOPATINES *******************
    @Operation(
            summary = "Registrar mantenimiento",
            description = "Un usuario autenticado con rol MANTENIMIENTO, puede registrar que un monopatin ingresa en mantenimiento.",
            operationId = "registrarMantenimientoMonopatin",
            tags = {"Monopatin", "Mantenimiento"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PutMapping("/mantenimiento/{idMonopatin}/registrarMantenimiento")
    public ResponseEntity<Void> registrarMantenimiento(@PathVariable String idMonopatin) {
        try {
            monopatinServicio.registrarMantenimiento(idMonopatin);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Obtener listado de monopatines.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener un listado de todos los monopatines de la aplicacion.",
            operationId = "getAllMonopatin",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PutMapping("/mantenimiento/{idMonopatin}/finalizarMantenimiento")
    public ResponseEntity<Void> finalizarMantenimiento(@PathVariable String idMonopatin) {
        try {
            monopatinServicio.finalizarMantenimiento(idMonopatin);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //        ******************* METODOS  PARA REPORTES SOLICITADOS *******************
    @Operation(
            summary = "Obtener reporte de monopatines según su estado.",
            description = "Un usuario autenticado con rol MANTENIMIENTO, puede obtener unreporte que indique  la cantidad de monopatines operativos y en mantenimiento.",
            operationId = "getConteoPorEstado",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/mantenimiento/conteoPorEstado")
    public Map<String, Long> obtenerConteoPorEstado() {
        return monopatinServicio.obtenerConteoPorEstado();
    }

    @Operation(
            summary = "Obtener reporte de monopatines por kilómetros.",
            description = "Un usuario autenticado con rol MANTENIMIENTO, puede obtener unreporte de monopatines, ordenado por la cantidad de kms recorridos.",
            operationId = "getReportePorKms",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/reportePorKilometros")
    public ResponseEntity<List<MonopatinDTO>> reportePorKilometros() {
        try {
            List<MonopatinDTO> reporte = monopatinServicio.reportePorKilometros();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Obtener reporte de monopatines por tiempo.",
            description = "Un usuario autenticado con rol MANTENIMIENTO, puede obtener unreporte de monopatines, ordenado por su tiempo de uso, pudiendo o no, contemplar el tiempo en pausa de cada monopatín.",
            operationId = "getReportePorTiempo",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/mantenimiento/reportePorTiempo")
    public ResponseEntity<List<MonopatinDTO>> reportePorTiempo(@RequestParam boolean considerarTiempoEnPausa) {
        try {
            List<MonopatinDTO> reporte = monopatinServicio.reportePorTiempo(considerarTiempoEnPausa);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Obtener reporte de monopatines cercanos.",
            description = "Un usuario autenticado con rol USUARIO, puede obtener unreporte de los monopatines mas cercanos a su ubicación.",
            operationId = "getMonopatinesCercanos",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    // Endpoint para obtener los monopatines cercanos
    @GetMapping("/cercanos")
    public ResponseEntity<List<MonopatinDTO>> obtenerMonopatinesCercanos(
            @RequestParam double longitud,
            @RequestParam double latitud,
            @RequestParam double rango) {

        try {
            List<MonopatinDTO> monopatinesCercanos = monopatinServicio.obtenerMonopatinesCercanos(longitud, latitud, rango);
            return ResponseEntity.ok(monopatinesCercanos);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Obtener otro reporte de monopatines por kilómetros.",
            description = "Un usuario autenticado con rol MANTENIMIENTO, puede obtener unreporte de monopatines, ordenado por la cantidad de kms recorridos.",
            operationId = "getOtroReportePorKms",
            tags = {"Monopatin", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/mantenimiento/reporteKilometros")
    public ResponseEntity<List<MonopatinDTO>> generarReportePorKilometros(@RequestParam boolean incluirTiempoDePausa) {
        try {
            // Llamar al servicio para generar el reporte
            List<MonopatinDTO> reporte = monopatinServicio.generarReportePorKilometros(incluirTiempoDePausa);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Utilizar un monopatin.",
            description = "Un usuario autenticado con rol USUARIO, puede utilizar un monopatin, dando inicio al viaje.",
            operationId = "iniciarViajeMonopatin",
            tags = {"Monopatin", "Viaje"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PatchMapping("/{idMonopatin}/comenzar-viaje")
    public ResponseEntity<Void> comenzarViaje(@PathVariable String idMonopatin) {
        try {
            monopatinServicio.comenzarViaje(idMonopatin);
            return ResponseEntity.noContent().build(); // Responde con 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Responde con 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Responde con 500 Internal Server Error
        }
    }
    @Operation(
            summary = "Liberar un monopatin.",
            description = "Un usuario autenticado con rol USUARIO, puede liberar un monopatin que tiene en uso, dando fin al viaje.",
            operationId = "finalizarViajeMonopatin",
            tags = {"Monopatin", "Viaje"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PatchMapping("/{idMonopatin}/finalizar-viaje")
    public ResponseEntity<Void> finalizarViaje(
            @PathVariable String idMonopatin,
            @RequestParam String idParadaDestino,
            @RequestParam int kmRecorridos,
            @RequestParam int tiempoPausa,
            @RequestParam int tiempoUso) {
        try {
            // Llamar al servicio con los parámetros
            monopatinServicio.finalizarViaje(
                    idMonopatin,
                    idParadaDestino,
                    kmRecorridos,
                    tiempoPausa,
                    tiempoUso
            );
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }
}




