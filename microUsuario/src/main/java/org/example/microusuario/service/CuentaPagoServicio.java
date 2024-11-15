package org.example.microusuario.service;

import ch.qos.logback.core.net.SyslogOutputStream;
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

import java.util.List;
import java.util.Optional;

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
    public List<CuentaPagoDTO> getAll(){
        var resultado = cuentaPagoRepository.findAll();
        return resultado.stream().map( cp ->new CuentaPagoDTO( cp.getNombre(), cp.getSaldo())).toList();
    }

    @Transactional
    public UsuarioDTO save( RequestCuentaPagoDTO request, long idUsuario ) {

        CuentaPago cuentaPago = new CuentaPago(request);
        var usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            Usuario entidad = usuario.get();
            cuentaPago.getUsuarios().add(entidad);
            cuentaPagoRepository.save(cuentaPago);
            entidad.getCuentasPago().add(cuentaPago);
            usuarioRepository.save(entidad);

            return new UsuarioDTO( entidad.getApellido(),entidad.getNombre(),
                    entidad.getTelefono(),entidad.getCuentasPago().stream().map(cp -> new CuentaPagoDTO(cp.getNombre(),cp.getSaldo())).toList());
        }
        else {
            return new UsuarioDTO("error", "grave", "que problema");
        }
    }
    @Transactional
    public ResponseEntity<String> delete(Long id) {
        String message;
        try{
            if(cuentaPagoRepository.existsById(id)){
                CuentaPago cuentaPago = cuentaPagoRepository.findById(id).get();
                List<Usuario> listaUsuarios = cuentaPago.getUsuarios();

                for (Usuario usuario : listaUsuarios) {
                    usuario.getCuentasPago().remove(cuentaPago);
                }
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

    public ResponseEntity<CuentaPagoDTO> getById(Long id) {
        var resultado = cuentaPagoRepository.findById(id);
        if(resultado.isPresent()){
            CuentaPagoDTO cp = new CuentaPagoDTO(resultado.get().getNombre(),resultado.get().getSaldo());
            return ResponseEntity.ok().body(cp);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    public CuentaPagoDTO update(Long id, RequestCuentaPagoDTO request) throws Exception {
        Optional<CuentaPago>  optCuentaPago = cuentaPagoRepository.findById(id);
        if(optCuentaPago.isPresent()){
            CuentaPago cuentaPago = optCuentaPago.get();
            cuentaPago.setNombre(request.getNombre());
            cuentaPago.setSaldo(request.getSaldo());
            cuentaPagoRepository.save(cuentaPago);
            return new CuentaPagoDTO(cuentaPago.getNombre(),cuentaPago.getSaldo());
        }
        else{
            return null;
        }

    }
}
