package com.Ecomark_vm.Eckomark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomark_vm.Eckomark.model.Envio;
import com.Ecomark_vm.Eckomark.repository.EnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;


    public List<Envio> listarEnvios() {
        return envioRepository.findAll();
    }

    public Optional<Envio> obtenerEnvio(Long id) {
        return envioRepository.findById(id);
    }

    public List<Envio> buscarPorEstado(String estado) {
        return envioRepository.findByEstado(estado);
    }

    public List<Envio> buscarPorDestino(String destino) {
        return envioRepository.findByDestino(destino);

    }
      public Envio findById(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public void delete(Long id) {
        envioRepository.deleteById(id);
    }
}
    

