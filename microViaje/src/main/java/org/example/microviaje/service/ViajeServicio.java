package org.example.microviaje.service;

import jakarta.transaction.Transactional;
import org.example.microviaje.dto.ReporteViajeMonopatinDTO;
import org.example.microviaje.dto.RequestViajeDTO;
import org.example.microviaje.dto.ViajeDTO;
import org.example.microviaje.entity.Viaje;
import org.example.microviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service("ViajeServicio")
public class ViajeServicio {
    @Autowired
    private ViajeRepository viajeRepository;

    public ViajeServicio(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    @Transactional
    public ViajeDTO save(RequestViajeDTO request){
        Viaje viaje = new Viaje(request);
        var result = viajeRepository.save(viaje);
        return new ViajeDTO(result.getFechaHoraInicio(), result.getFechaHoraFin(), result.getKmRecorridos(), result.getMontoTotal());
    }

    public ViajeDTO update(Long id, RequestViajeDTO request) throws Exception{
        Viaje viaje = new Viaje(request);
        viaje.setIdViaje(id);
        try{
            var resultado = viajeRepository.save(viaje);
            return new ViajeDTO(resultado.getFechaHoraInicio(),resultado.getFechaHoraFin(),resultado.getKmRecorridos(),resultado.getMontoTotal());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Viaje> getAll(){
        var resultado = viajeRepository.findAll();
        resultado.stream().map(viaje -> new ViajeDTO(viaje.getFechaHoraInicio(),
                                                        viaje.getFechaHoraFin(),
                                                        viaje.getKmRecorridos(),
                                                        viaje.getMontoTotal())).collect(Collectors.toList());
        return resultado;
    }

    public ResponseEntity<Viaje> getById(Long id){
        Optional<Viaje> result = viajeRepository.findById(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> delete(Long id){
        String mensaje;
        try{
            if(viajeRepository.existsById(id)){
                Viaje viaje = viajeRepository.findById(id).get();
                this.viajeRepository.delete(viaje);
                mensaje = "Viaje con id "+id.toString()+" eliminado";
                return ResponseEntity.ok().body( mensaje );
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public List<ReporteViajeMonopatinDTO> getViajesPorMonopatin(int anio, int cant) throws Exception{
        var resultado = viajeRepository.getCantViajesPorAnioPorMonopatin(anio, cant);

        try{
            return resultado.stream().map(reporte-> new ReporteViajeMonopatinDTO(((Number)reporte[0]).intValue(), //ID MONOPATIN
                                                                                ((Number)reporte[1]).intValue() //CANTIDAD
                                                                                    )).collect(Collectors.toList())
                    ;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
