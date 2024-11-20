package org.example.microviaje;

import org.example.microviaje.entity.Viaje;
import org.example.microviaje.repository.ViajeRepository;
import org.example.microviaje.service.ViajeServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViajeServicioTest {

    @Mock
    private ViajeRepository viajeRepository;

    @InjectMocks
    private ViajeServicio viajeServicio;

    @Test
    void testGetAllViajes() {
        // Datos de prueba
        Viaje viaje1 = new Viaje();
        viaje1.setFechaViaje(LocalDate.of(2024, 10, 1));
        viaje1.setTiempoPausa(15);
        viaje1.setTiempoViaje(120);
        viaje1.setKmRecorridos(10);
        viaje1.setMontoTotal(100.0);
        viaje1.setParadaDestino("Parada A");

        Viaje viaje2 = new Viaje();
        viaje2.setFechaViaje(LocalDate.of(2024, 10, 2));
        viaje2.setTiempoPausa(5);
        viaje2.setTiempoViaje(60);
        viaje2.setKmRecorridos(5);
        viaje2.setMontoTotal(50.0);
        viaje2.setParadaDestino("Parada B");

        List<Viaje> mockViajes = List.of(viaje1, viaje2);

        // Configurar el mock
        when(viajeRepository.findAll()).thenReturn(mockViajes);

        // Llamar al método
        List<Viaje> result = viajeServicio.getAll();

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockViajes, result);
        verify(viajeRepository, times(1)).findAll();
    }
}

