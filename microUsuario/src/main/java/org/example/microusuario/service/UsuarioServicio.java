package org.example.microusuario.service;

import jakarta.transaction.Transactional;
import org.example.microusuario.dto.CuentaPagoDTO;
import org.example.microusuario.dto.RequestUsuarioDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.entity.CuentaPago;
import org.example.microusuario.entity.Usuario;
import org.example.microusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return new UsuarioDTO(resultado.getApellido(), resultado.getNombre(), resultado.getTelefono(),resultado.getActivo());

    }

    public UsuarioDTO update(Long id, RequestUsuarioDTO nuevoUsuario) throws Exception {
        Usuario usuario = new Usuario(nuevoUsuario);
        usuario.setIdUsuario(id);
        try{
            var resultado = usuarioRepository.save(usuario);
            return new UsuarioDTO(resultado.getApellido(), resultado.getNombre(), resultado.getTelefono(),resultado.getActivo());
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

    //Dado un Usuario devolvemos lista de sus cuentas en formato CuentaDTO para visualizar en el front.
    private List<CuentaPagoDTO> obtenerListaCuentasDTO(Usuario usuario) {
        List<CuentaPago> list = usuario.getCuentasPago();
        List<CuentaPagoDTO>listCuentasDto = new ArrayList<>();
        for(CuentaPago c : list){
            listCuentasDto.add(new CuentaPagoDTO(c.getNombre(),c.getSaldo()));
        }
        return listCuentasDto;
    }

    public List<UsuarioDTO> getAll() {
        var resultado = usuarioRepository.findAll();

        return resultado.stream().map(usuario -> new UsuarioDTO(usuario.getApellido(),
                                                         usuario.getNombre(),
                                                          usuario.getTelefono(),usuario.getActivo(),obtenerListaCuentasDTO(usuario))).toList();
    }

    public ResponseEntity<UsuarioDTO> getById(Long id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if (result.isPresent()){
            UsuarioDTO usuarioDTO = new UsuarioDTO(result.get().getApellido(),result.get().getNombre(),
                    result.get().getTelefono(),result.get().getActivo(),obtenerListaCuentasDTO(result.get()));
            return ResponseEntity.ok().body(usuarioDTO);
        }
        else return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UsuarioDTO> cambiarEstado(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if (optUsuario.isPresent()){
            Usuario usuario = optUsuario.get();
            usuario.setActivo(!usuario.getActivo());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(new UsuarioDTO(usuario.getApellido(),usuario.getNombre(),
                                                    usuario.getTelefono(),usuario.getActivo(),obtenerListaCuentasDTO(usuario)));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
