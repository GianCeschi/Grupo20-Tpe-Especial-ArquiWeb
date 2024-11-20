package org.example.micromonopatin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.micromonopatin.DTO.ParadaDTO;
import org.example.micromonopatin.entity.Parada;
import org.example.micromonopatin.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatines")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    public ParadaController(ParadaService paradaService){
        this.paradaService = paradaService;
    }



    //        ******************* METODOS  PARA ABM DE PARADAS *******************

    @Operation(
            summary = "Crear una parada de monopatines.",
            description = "Un usuario autenticado con rol ADMIN, puede dar el alta a una parada de monopatines, especificando el valor de sus atributos ubicación y capacidad.",
            operationId = "saveParada",
            tags = {"Parada", "Crear"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
            }
    )
    // Crear una parada
    @PostMapping("/paradas")
    public ParadaDTO createParada(@RequestBody ParadaDTO paradaDTO) {
        return paradaService.saveParada(paradaDTO);
    }

    @Operation(
            summary = "Eliminar una parada de monopatines",
            description = "Un usuario autenticado con rol ADMIN, puede eliminar una parada de monopatines de la aplicacion, indicando el idParada.",
            operationId = "deleteParada",
            tags = {"Parada", "Eliminar"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Monopatín eliminado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    // Borrar una parada
    @DeleteMapping("/paradas/{id}")
    public ResponseEntity<Void> deleteParada(@PathVariable String id) {
        if (paradaService.getParadaById(id).isPresent()) {
            paradaService.deleteParada(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //        ******************* METODOS  PARA RECUPERAR PARADAS *******************

    @Operation(
            summary = "Obtener detalle listado de las paradas de monopatines.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener un listado de todas las paradas de monopatin de la aplicacion.",
            operationId = "getAllParadas",
            tags = {"Parada", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/paradas")
    public List<ParadaDTO> getAllParadas() {
        return paradaService.getAllParadas();
    }

    @Operation(
            summary = "Obtener detalle de una parada de monopatines.",
            description = "Un usuario autenticado con rol ADMIN, puede obtener la información detallada de una parada de monopatin de la aplicacion.",
            operationId = "getOneParada",
            tags = {"Parada", "Reporte"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @GetMapping("/paradas/{id}")
    public ResponseEntity<ParadaDTO> getParadaById(@PathVariable String id) {
        return paradaService.getParadaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
