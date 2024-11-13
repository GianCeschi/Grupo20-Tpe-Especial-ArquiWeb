package org.example.microusuario.service;

import jakarta.transaction.Transactional;
import org.example.microusuario.dto.RequestUsuarioDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.entity.Usuario;
import org.example.microusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("UsuarioServicio")
public class UsuarioServicio {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioDTO save(RequestUsuarioDTO request ) {
        Usuario usuario = new Usuario(request);
        var resultado = usuarioRepository.save(usuario);
        return new UsuarioDTO(resultado.getApellido(), resultado.getNombre(), resultado.getTelefono());

    }

    public UsuarioDTO update(Long id, RequestUsuarioDTO nuevoUsuario) throws Exception {
        Usuario usuario = new Usuario(nuevoUsuario);
        usuario.setIdUsuario(id);
        try{
            var resultado = usuarioRepository.save(usuario);
            return new UsuarioDTO(resultado.getApellido(), resultado.getNombre(), resultado.getTelefono());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<String> delete(Long id) {
        String message;
        try{
            if(usuarioRepository.existsById(id)){
                Usuario usuario = usuarioRepository.findById(id).get();
                this.usuarioRepository.delete(usuario);
                message = "Se eliminó con exito el usuario con id: " + id.toString();
                return ResponseEntity.ok().body( message );
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public List<Usuario> getAll() {
        var resultado = usuarioRepository.findAll();
        resultado.stream().map(usuario -> new UsuarioDTO(usuario.getApellido(),
                                                         usuario.getNombre(),
                                                          usuario.getTelefono())).collect(Collectors.toList());
        return resultado;
    }

    public ResponseEntity<Usuario> getById(Long id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        else return ResponseEntity.notFound().build();
    }
}
