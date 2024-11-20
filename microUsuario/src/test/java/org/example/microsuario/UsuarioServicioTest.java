package org.example.microsuario;


import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.repository.UsuarioRepository;
import org.example.microusuario.service.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServicioTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServicio usuarioServicio;

    @Test
    void testGetByIdUsuarioNotFound() {
        // Datos de prueba
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Llamada al método
        ResponseEntity<UsuarioDTO> response = usuarioServicio.getById(id);

        // Verificaciones
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioRepository, times(1)).findById(id);
    }
}


