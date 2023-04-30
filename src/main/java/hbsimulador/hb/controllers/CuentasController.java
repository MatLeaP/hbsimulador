package hbsimulador.hb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hbsimulador.hb.entities.Cuentas;
import hbsimulador.hb.model.request.CuentasRequest;
import hbsimulador.hb.services.ClienteService;
import hbsimulador.hb.services.CuentasService;

@RestController
public class CuentasController {
    @Autowired
    CuentasService cuentasService;

    @Autowired
    ClienteService clienteService;

    @PostMapping("/crearCuenta")
    public ResponseEntity<Cuentas> crearCuenta(@RequestBody CuentasRequest cuentasRequest) throws Exception {
        Cuentas cuenta = cuentasService.crearCuenta(cuentasRequest);
        return ResponseEntity.ok(cuenta);
    }

    
    @GetMapping("/cuentas/cliente/{id}")
    public ResponseEntity<List<Cuentas>> obtenerCuentasByClienteId(@PathVariable int id){
    List<Cuentas> cuenta = cuentasService.findAllCuentasById(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping("/cuentas")
    public ResponseEntity<List<Cuentas>> obtenerCuentas() {
        List<Cuentas> cuentas = cuentasService.listarCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Cuentas> obtenerCuentasById(@PathVariable int id) {
        Optional<Cuentas> cuenta = cuentasService.listarById(id);
        return ResponseEntity.ok(cuenta.get());

    }

    @PutMapping("/ingresarDinero/{id}")
    public ResponseEntity<Cuentas> depositoEnCuenta(@PathVariable int id, @RequestBody CuentasRequest cuentaRequest) throws Exception {
        Cuentas  cuenta = cuentasService.ingresarDinero(id, cuentaRequest);
        return ResponseEntity.ok(cuenta);
    }

    @PutMapping("/retirarDinero/{id}")
    public ResponseEntity<Cuentas> extraccionEnCuenta(@PathVariable int id, @RequestBody CuentasRequest cuentaRequest) throws Exception {
        Cuentas cuenta = cuentasService.retirarDinero(id, cuentaRequest);
        return ResponseEntity.ok(cuenta) ;
    }

    @GetMapping("/saldoEnCuentas/{id}")
    public int saldoEnCuenta(@PathVariable int id) throws Exception {
        int cuenta = cuentasService.obtenerSaldoTotalDeCuentas(id);
        return cuenta;
    }
}
/*
 * crear cuenta de cliente(post)
 * listar cantidad de cuentas(get)
 * mostrar datos de cuenta de x cliente(get)
 * ingresar dinero(put)
 * retirar dinero(put)
 */