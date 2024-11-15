package org.example.microviaje.service;

import jakarta.transaction.Transactional;
import org.example.microviaje.dto.TarifaDTO;
import org.example.microviaje.entity.Tarifa;
import org.example.microviaje.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("TarifaServicio")
public class TarifaServicio {
    @Autowired
    private TarifaRepository tarifaRepository;

    public TarifaServicio(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public ResponseEntity<TarifaDTO> getById(Long id) {
        Optional<Tarifa> result = tarifaRepository.findById(id);
        if (result.isPresent()){
            TarifaDTO tarifaDTO = new TarifaDTO(result.get().getTipo(),result.get().getValor(),
                                                 result.get().getFechaVigencia());
            return ResponseEntity.ok().body(tarifaDTO);
        }
        else return ResponseEntity.notFound().build();
    }

    public List<TarifaDTO> getAll(){
        List<Tarifa> tarifas = tarifaRepository.findAll();
        return tarifas.stream().map(res-> new TarifaDTO(res.getTipo(),res.getValor(),res.getFechaVigencia())).toList();
    }

    @Transactional
    public TarifaDTO save(TarifaDTO request ) {
        Tarifa tarifa = new Tarifa(request);
        var resultado = tarifaRepository.save(tarifa);
        return new TarifaDTO(resultado.getTipo(),resultado.getValor(),resultado.getFechaVigencia());
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) {
        String message;
        try{
                Optional<Tarifa> optionalTarifa = tarifaRepository.findById(id);
                if(optionalTarifa.isPresent()) {
                    this.tarifaRepository.delete(optionalTarifa.get());
                    message = "Se eliminó con exito la tarifa con id: " + id.toString();
                    return ResponseEntity.ok().body(message);
                }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public TarifaDTO update(Long id, TarifaDTO nuevaTarifa) throws Exception {
        Tarifa tarifa = new Tarifa(nuevaTarifa);
        tarifa.setIdTarifa(id);
        try{
            var resultado = tarifaRepository.save(tarifa);
            return new TarifaDTO(resultado.getTipo(),resultado.getValor(),resultado.getFechaVigencia());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
