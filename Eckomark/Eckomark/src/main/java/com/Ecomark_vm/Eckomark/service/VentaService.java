package com.Ecomark_vm.Eckomark.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomark_vm.Eckomark.model.Cupon;
import com.Ecomark_vm.Eckomark.model.DetalleVenta;
import com.Ecomark_vm.Eckomark.model.Venta;
import com.Ecomark_vm.Eckomark.repository.CuponRepository;
import com.Ecomark_vm.Eckomark.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Autowired
    private JavaMailSender mailSender;

    public List<Venta> listarVentas(){
        return ventaRepository.findAll();
    }

    public Venta registrarVenta(Venta venta , String codigoCupon){
        venta.setFechaVenta(new Date());
        BigDecimal total =venta.getDetalle().stream()
        .map(d->d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
        
   Optional<Cupon> cuponOpt = Optional.ofNullable(codigoCupon)
        .map(String::trim)
        .flatMap(codigo -> cuponRepository.findById(codigo));

    if (cuponOpt.isPresent() && Boolean.TRUE.equals(cuponOpt.get().getActivo())) {
    BigDecimal descuento = cuponOpt.get().getDescuento();
    total = total.subtract(total.multiply(descuento));
    }

    venta.setTotal(total);

    venta.getDetalle().forEach(d ->d.setVenta(venta));
    Venta listarVentas =ventaRepository.save(venta);

    return ventaRepository.save(venta);
    enviarFacturaPorCorreo(listarVentas);
    return listarVentas;

    }
    private void enviarFacturaPorCorreo(Venta venta){
        StringBuilder cuerpo =new StringBuilder();
        cuerpo.append("Factura electronica\n\n");
        cuerpo.append("Cliente:").append(venta.getNom_usuario()).append("\n");
        cuerpo.append("fecha:").append(venta.getFechaVenta()).append("\n");
        cuerpo.append("Total:$").append(venta.getTotal()).append("\n\n");
        cuerpo.append("Datalle:\n");
        for (DetalleVenta d: venta.getDetalle()){
            cuerpo.append(" - ").append(d.getNombreProducto())
            .append(" x ").append(d.getCantidad())
            .append(" = $").append(d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
            .append("\n");
        }
       System.out.println(cuerpo.toString());

    }


    
}


