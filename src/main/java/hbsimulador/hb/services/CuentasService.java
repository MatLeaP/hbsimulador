package hbsimulador.hb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hbsimulador.hb.entities.Cliente;
import hbsimulador.hb.entities.Cuentas;
import hbsimulador.hb.model.request.CuentasRequest;
import hbsimulador.hb.repo.ClienteRepo;
import hbsimulador.hb.repo.CuentasRepo;

@Service
public class CuentasService {
    @Autowired
    CuentasRepo cuentasRepo;

    @Autowired
    ClienteRepo clienteRepo;

    @Autowired
    ClienteService clienteService;

    public boolean grabar(Cuentas cuentas){
        cuentasRepo.save(cuentas);
        return true;
    }
    
    
    public Cuentas crearCuenta(CuentasRequest cuentasRequest) throws Exception{
        Optional<Cliente> optional = clienteService.findById(cuentasRequest.idCliente);
        Cuentas cuenta = new Cuentas(optional.get(), cuentasRequest.saldo);
        grabar(cuenta);
        return cuenta;
    }
    

    
    public List<Cuentas> findAllCuentasById(int id){
        Optional<Cliente> clienteOp = clienteRepo.findById(id);
        if (clienteOp.isEmpty()){
            return new ArrayList();
        }
        return clienteOp.get().getCuentas();
    }
    
    public List<Cuentas> listarCuentas(){
        return cuentasRepo.findAll();
    }
   
    public Optional<Cuentas> listarById(int numDeCuenta){
        Optional<Cuentas> cuentaOp =  cuentasRepo.findById(numDeCuenta);
        if (cuentaOp.isEmpty()){
            return Optional.empty();
        }
        return cuentaOp;
    }

    public Cuentas retirarDinero(int id, CuentasRequest cuentaRequest) throws Exception{
        Optional<Cuentas> cuentaOptional = listarById(id);
        if (cuentaOptional.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }
        Cuentas cuenta = cuentaOptional.get();
        int saldo = cuenta.getSaldo();
        int saldoARetirar = cuentaRequest.saldo;
        if (saldo < saldoARetirar) {
            throw new Exception("No posee saldo suficiente");
        }
        cuenta.setSaldo(saldo - saldoARetirar);
        try {
            grabar(cuenta);
            return cuenta;
        } catch (Exception e) {
            throw new Exception("Error");
        }

    }


    public Cuentas ingresarDinero(int id, CuentasRequest cuentaRequest) throws Exception{
        Optional<Cuentas> cuentaOptional = listarById(id);
        if (cuentaOptional.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }
        Cuentas cuenta = cuentaOptional.get();
        int saldo = cuenta.getSaldo();
        int saldoAIngresar = cuentaRequest.saldo;
        cuenta.setSaldo(saldo + saldoAIngresar);
        try {
            grabar(cuenta);
            return cuenta;
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }


    public int obtenerSaldoTotalDeCuentas(int idCliente) throws Exception {
        List<Cuentas> cuentas = findAllCuentasById(idCliente);
        if(cuentas.isEmpty()){
            throw new Exception("El cliente solicitado no existe no posee cuentas activas.");
        }
        return cuentas.stream()
                    .mapToInt(Cuentas::getSaldo)
                    .sum();
    }

}
