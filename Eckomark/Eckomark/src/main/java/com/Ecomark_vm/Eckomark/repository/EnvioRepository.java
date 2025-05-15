package com.Ecomark_vm.Eckomark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecomark_vm.Eckomark.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long>{

    List<Envio> findByEstado(String estado);
    List<Envio> findByDestino(String destino);
}



