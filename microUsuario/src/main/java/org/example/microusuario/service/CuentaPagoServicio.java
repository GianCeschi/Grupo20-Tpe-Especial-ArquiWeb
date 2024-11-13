package org.example.microusuario.service;

import jakarta.transaction.Transactional;
import org.example.microusuario.dto.CuentaPagoDTO;
import org.example.microusuario.dto.RequestCuentaPagoDTO;
import org.example.microusuario.dto.RequestUsuarioDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.entity.CuentaPago;
import org.example.microusuario.entity.Usuario;
import org.example.microusuario.repository.CuentaPagoRepository;
import org.example.microusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("CuentaPagoServicio")
public class CuentaPagoServicio {
    @Autowired
    private CuentaPagoRepository cuentaPagoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CuentaPagoServicio(CuentaPagoRepository cuentaPagoRepository, UsuarioRepository usuarioRepository) {
        this.cuentaPagoRepository = cuentaPagoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Transactional
    public UsuarioDTO save(Long idUsuario, RequestCuentaPagoDTO request ) {

        CuentaPago cuentaPago = new CuentaPago(request);
        var resultado = cuentaPagoRepository.save(cuentaPago);

        var usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()){
            usuario.get().getCuentasPago().add(cuentaPago);
            usuarioRepository.save(usuario.get());
            return new UsuarioDTO( usuario.get().getApellido(),usuario.get().getNombre(),
                    usuario.get().getTelefono(),usuario.get().getCuentasPago());
        }
        else{
            return null;
        }
    }

    public ResponseEntity<String> delete(Long id) {
        String message;
        try{
            if(cuentaPagoRepository.existsById(id)){
                CuentaPago cuentaPago = cuentaPagoRepository.findById(id).get();
                this.cuentaPagoRepository.delete(cuentaPago);
                message = "Se eliminó con exito la cuentaPago con id: " + id.toString();
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
}
