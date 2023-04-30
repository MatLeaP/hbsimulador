package hbsimulador.hb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hbsimulador.hb.entities.Cliente;
import hbsimulador.hb.model.request.ClienteRequest;
import hbsimulador.hb.repo.ClienteRepo;
import hbsimulador.hb.services.ClienteService;

@RestController
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepo clienteRepo;



    @PostMapping("/crearCliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteRequest clienteRequest){
        Cliente cliente = clienteService.crearCliente(clienteRequest);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/listaClientes")
    public ResponseEntity<List<Cliente>> obtenerClientes(){
        List<Cliente> clientes = clienteService.obtenerClientes();
        return ResponseEntity.ok(clientes);
        
    }

    @GetMapping("/listaClientes/{id}")
    public ResponseEntity<Cliente> obtenerClientesPorId(@PathVariable int id) throws Exception{
        Optional<Cliente> clienteOp = clienteService.findById(id);
        return ResponseEntity.ok(clienteOp.get());
    }

    @GetMapping("/datosClientes")
    public ResponseEntity<List<String>> obtenerApellidos() throws Exception{
        List<String> clienteAp = clienteService.apellidosDeClientes();
        return ResponseEntity.ok(clienteAp);
    }

    @PutMapping("/actualizarCliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody ClienteRequest clienteRequest) throws Exception{
        Cliente cliente = clienteService.updateCliente(id, clienteRequest);
        return ResponseEntity.ok(cliente);
    }


    @DeleteMapping("/eliminarCliente/{id}")
    public ResponseEntity<List<Cliente>> eliminarClientesPorId(@PathVariable int id){
        clienteService.deleteCliente(id);
        List<Cliente> clientes = clienteService.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }
}


/*
    crear cliente(post)
    listar clientes(get)
    eliminar clientes(delete)
    actualizar datos clientes(put)
    */