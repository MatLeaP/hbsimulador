package hbsimulador.hb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import hbsimulador.hb.entities.Cliente;
import hbsimulador.hb.entities.Cuentas;
import hbsimulador.hb.model.request.ClienteRequest;
import hbsimulador.hb.model.request.CuentasRequest;
import hbsimulador.hb.repo.ClienteRepo;
import hbsimulador.hb.repo.CuentasRepo;
import hbsimulador.hb.services.ClienteService;
import hbsimulador.hb.services.CuentasService;

@ExtendWith(MockitoExtension.class)
public class CuentasServiceTest {

    @InjectMocks
	CuentasService cuentasService;

    @Mock
	ClienteService clienteService;

	@Mock
	ClienteRepo clienteRepo;

    @Mock 
    CuentasRepo cuentasRepo;

    @BeforeEach
	public void setup() {
    MockitoAnnotations.initMocks(this);
	}


    @Test
    void testCrearCuenta() throws Exception {
        
        int saldo = 2000;

        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Matias");
        cliente1.setApellido("Perona");
        cliente1.setDni(111110);
        cliente1.setEdad(22);

        CuentasRequest cuentasRequest = new CuentasRequest();
        cuentasRequest.idCliente = 1;
        cuentasRequest.saldo = saldo;

        Cuentas cuenta = new Cuentas();
        
        cuenta.setSaldo(2000);

        when(clienteService.findById(1)).thenReturn(Optional.of(cliente1));
        when(cuentasRepo.save(any())).thenReturn(cuenta);

        Cuentas cuenta2 = cuentasService.crearCuenta(cuentasRequest);

        Assertions.assertEquals(cuenta2.getSaldo(), 2000);
    }

    @Test
    public void findAllCuentasById() throws Exception{

        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Matias");
        cliente1.setApellido("Perona");
		cliente1.setDni(111110);
		cliente1.setEdad(22);;


        Cuentas cuenta1 = new Cuentas();
        cuenta1.setIdCliente(cliente1);
        cuenta1.setSaldo(1500);

        Cuentas cuenta2 = new Cuentas();
        cuenta2.setIdCliente(cliente1);
        cuenta2.setSaldo(45100);

        List<Cuentas> cuentas = new ArrayList<>();
        List<Cuentas> cuentas2 = new ArrayList<>();

        cuentas.add(cuenta1);
        cuentas.add(cuenta2);

        cliente1.setCuentas(cuentas);

        when(clienteRepo.findById(1)).thenReturn(Optional.of(cliente1));

        List<Cuentas> result = cuentasService.findAllCuentasById(1);

        assertEquals(cuentas, result);
        
    }

    @Test
    public void listarCuentas(){

        Cuentas cuenta1 = new Cuentas();
        Cuentas cuenta2 = new Cuentas();

        List<Cuentas> cuentas = Arrays.asList(cuenta1, cuenta2);

        when(cuentasRepo.findAll()).thenReturn(cuentas);

        List<Cuentas> cuentasObtenidas = cuentasService.listarCuentas();

        assertEquals(cuentas, cuentasObtenidas);

    }


    @Test
    public void listarById(){

        Cuentas cuenta1 = new Cuentas();
        Cuentas cuenta2 = new Cuentas();

        when(cuentasRepo.findById(1)).thenReturn(Optional.of(cuenta1));

        Optional<Cuentas> cuentaObtenida = cuentasService.listarById(1);

        assertEquals(cuenta1, cuentaObtenida.get());
    }

    @Test
    public void retirarDinero() throws Exception{

        Cuentas cuenta1 = new Cuentas();
        cuenta1.setSaldo(5000);

        CuentasRequest cuentaRequest = new CuentasRequest();
        cuentaRequest.idCliente = 1;
        cuentaRequest.saldo = 4000;

        when(cuentasRepo.findById(1)).thenReturn(Optional.of(cuenta1));
        when(cuentasRepo.save(any())).thenReturn(cuenta1);

        Cuentas cuentaObtenida = cuentasService.retirarDinero(1, cuentaRequest);

        Assertions.assertEquals(cuentaObtenida.getSaldo(), 1000);

    }

    @Test
    public void ingresarDinero() throws Exception{

        Cuentas cuenta1 = new Cuentas();
        cuenta1.setSaldo(0);

        CuentasRequest cuentaRequest = new CuentasRequest();
        cuentaRequest.idCliente = 1;
        cuentaRequest.saldo = 1000;

        when(cuentasRepo.findById(1)).thenReturn(Optional.of(cuenta1));
        when(cuentasRepo.save(any())).thenReturn(cuenta1);

        Cuentas cuentaObtenida = cuentasService.ingresarDinero(1, cuentaRequest);

        Assertions.assertEquals(cuentaObtenida.getSaldo(), 1000);

    }
}


    

