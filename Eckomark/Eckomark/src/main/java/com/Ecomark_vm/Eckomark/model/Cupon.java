package com.Ecomark_vm.Eckomark.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cupon")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cupon {
    
    @Id
    @Column( length= 25, nullable = true)
    private String codigo;

    @Column(nullable = false)
    private BigDecimal descuento;

    @Column(nullable = false)
    private Boolean activo;
    
    
    
}
