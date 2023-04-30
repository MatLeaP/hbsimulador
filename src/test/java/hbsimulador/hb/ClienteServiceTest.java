package hbsimulador.hb;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import hbsimulador.hb.entities.Cliente;
import hbsimulador.hb.model.request.ClienteRequest;
import hbsimulador.hb.repo.ClienteRepo;
import hbsimulador.hb.services.ClienteService;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

	@InjectMocks
	ClienteService clienteService;

	@Mock
	ClienteRepo clienteRepo;

	@BeforeEach
	public void setup() {
    MockitoAnnotations.initMocks(this);
	}
	@org.junit.jupiter.api.Test
	public void crear_cliente_con_parametros(){
		String nombre = "Matias";
		String apellido = "Perona";
		int dni = 21212121;
		int edad = 854;

		Cliente cliente2 = new Cliente(nombre, apellido, dni, edad);
		
		ClienteRequest cr = new ClienteRequest();
		cr.nombre = nombre;
		cr.apellido = apellido;
		cr.dni = dni;
		cr.edad = edad;

		when(clienteRepo.save(any())).thenReturn(cliente2);

		Cliente cliente = clienteService.crearCliente(cr);

		assertTrue(cliente.getApellido().equals(apellido));
	}
	
	@Test
    public void testObtenerClientes() {

        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Matias");
        cliente1.setApellido("Perona");
		cliente1.setDni(111110);
		cliente1.setEdad(22);
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Leandro");
        cliente2.setApellido("Perona");
		cliente1.setDni(2222220);
		cliente1.setEdad(23);
        List<Cliente> clientesFicticios = Arrays.asList(cliente1, cliente2);

        when(clienteRepo.findAll()).thenReturn(clientesFicticios);

        List<Cliente> clientesObtenidos = clienteService.obtenerClientes();

        assertEquals(clientesFicticios, clientesObtenidos);
    }
	
	@Test
	public void testFindById_Existente() throws Exception {

        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Matias");
        cliente1.setApellido("Perona");
		cliente1.setDni(111110);
		cliente1.setEdad(22);;

		Cliente cliente2 = new Cliente();
        cliente2.setNombre("Leandro");
        cliente2.setApellido("Perona");
		cliente1.setDni(2222220);
		cliente1.setEdad(23);

        when(clienteRepo.findById(2)).thenReturn(Optional.of(cliente2));

        Optional<Cliente> clienteObtenido = clienteService.findById(2);

        assertEquals(cliente2, clienteObtenido.get());
    }

	@Test
	public void updateCliente() throws Exception{

		String nombre = "Matias";
		String apellido = "Diaz";
		int dni = 111110;
		int edad = 55;

		Cliente cliente1 = new Cliente();
        cliente1.setNombre("Matias");
        cliente1.setApellido("Perona");
		cliente1.setDni(111110);
		cliente1.setEdad(22);

		when(clienteRepo.findById(1)).thenReturn(Optional.of(cliente1));
		when(clienteRepo.save(any())).thenReturn(cliente1);	
		
		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.nombre = nombre;
		clienteRequest.apellido = apellido;
		clienteRequest.dni = dni;
		clienteRequest.edad = edad;

		Cliente clienteActualizado = clienteService.updateCliente(1, clienteRequest);
		

		assertTrue(clienteActualizado.getNombre().equals(cliente1.getNombre()));
	}

	@Test
	public void deleteCliente(){

		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();

		
	}

}
