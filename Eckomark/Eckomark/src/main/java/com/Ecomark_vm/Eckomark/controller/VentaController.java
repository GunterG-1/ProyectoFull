package com.Ecomark_vm.Eckomark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecomark_vm.Eckomark.model.Venta;
import com.Ecomark_vm.Eckomark.service.VentaService;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> ventas = ventaService.listarVentas();
        if(ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }


    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta, @RequestParam(required = false)String codigoCupon){
        try{
            Venta nuevaVenta = ventaService.registrarVenta(venta, codigoCupon);
            return ResponseEntity.ok(nuevaVenta);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }    
}
