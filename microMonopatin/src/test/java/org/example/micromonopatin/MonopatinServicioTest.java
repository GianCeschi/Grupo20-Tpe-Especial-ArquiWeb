package org.example.micromonopatin;

import org.example.micromonopatin.DTO.MonopatinDTO;
import org.example.micromonopatin.entity.Monopatin;
import org.example.micromonopatin.repository.MonopatinRepository;
import org.example.micromonopatin.repository.ParadaRepository;
import org.example.micromonopatin.service.MonopatinServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MonopatinServicioTest {

    @Mock
    private MonopatinRepository monopatinRepository;

    @Mock
    private ParadaRepository paradaRepository;

    @InjectMocks
    private MonopatinServicio monopatinServicio;

    // Test para getAllMonopatines
    @Test
    void testGetAllMonopatines() {
        // Arrange
        Monopatin monopatin1 = new Monopatin();
        monopatin1.setIdMonopatin("1");
        monopatin1.setEstado("disponible");

        Monopatin monopatin2 = new Monopatin();
        monopatin2.setIdMonopatin("2");
        monopatin2.setEstado("en uso");

        Mockito.when(monopatinRepository.findAll()).thenReturn(Arrays.asList(monopatin1, monopatin2));

        // Act
        List<MonopatinDTO> result = monopatinServicio.getAllMonopatines();

        // Assert
        assertEquals(2, result.size());
        assertEquals("disponible", result.get(0).getEstado());
        Mockito.verify(monopatinRepository, times(1)).findAll();
    }

    // Test para saveMonopatin
    @Test
    void testSaveMonopatin_ParadaNoExiste() {
        // Arrange
        MonopatinDTO dto = new MonopatinDTO();
        dto.setId("1");
        dto.setIdParada("123");

        Mockito.when(paradaRepository.existsById("123")).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            monopatinServicio.saveMonopatin(dto);
        });
        assertEquals("La parada con el id 123 no existe.", exception.getMessage());
        Mockito.verify(paradaRepository, times(1)).existsById("123");
    }

    @Test
    void testSaveMonopatin_ParadaExiste() {
        // Arrange
        MonopatinDTO dto = new MonopatinDTO();
        dto.setId("1");
        dto.setEstado("disponible");
        dto.setIdParada("123");

        Monopatin savedMonopatin = new Monopatin();
        savedMonopatin.setIdMonopatin("1");
        savedMonopatin.setEstado("disponible");

        Mockito.when(paradaRepository.existsById("123")).thenReturn(true);
        Mockito.when(monopatinRepository.save(Mockito.any(Monopatin.class))).thenReturn(savedMonopatin);

        // Act
        MonopatinDTO result = monopatinServicio.saveMonopatin(dto);

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId());
        Mockito.verify(paradaRepository, times(1)).existsById("123");
        Mockito.verify(monopatinRepository, times(1)).save(Mockito.any(Monopatin.class));
    }

    // Test para obtenerConteoPorEstado
    @Test
    void testObtenerConteoPorEstado() {
        // Arrange
        Mockito.when(monopatinRepository.countByEstado("disponible")).thenReturn(10L);
        Mockito.when(monopatinRepository.countByEstado("en mantenimiento")).thenReturn(5L);

        // Act
        Map<String, Long> result = monopatinServicio.obtenerConteoPorEstado();

        // Assert
        assertEquals(10L, result.get("enOperacion"));
        assertEquals(5L, result.get("enMantenimiento"));
        Mockito.verify(monopatinRepository, times(1)).countByEstado("disponible");
        Mockito.verify(monopatinRepository, times(1)).countByEstado("en mantenimiento");
    }
}
