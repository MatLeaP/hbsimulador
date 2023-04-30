package hbsimulador.hb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hbsimulador.hb.entities.Cliente;
import hbsimulador.hb.model.request.ClienteRequest;
import hbsimulador.hb.repo.ClienteRepo;

@Service
public class ClienteService {
    

    @Autowired
    ClienteRepo clienteRepo;

    public Cliente grabar(Cliente cliente){
        return clienteRepo.save(cliente);
    }

    public Cliente crearCliente(ClienteRequest clienteRequest){
        Cliente cliente = new Cliente(clienteRequest.nombre , clienteRequest.apellido,clienteRequest.dni ,clienteRequest.edad);
        return grabar(cliente);
    }

    

    public List<Cliente> obtenerClientes(){
        return clienteRepo.findAll();
    }

    public Optional<Cliente> findById(int id) throws Exception{
        Optional<Cliente> clienteOp = clienteRepo.findById(id);
        if (clienteOp.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        return clienteOp;
    }

    public Cliente updateCliente(int id, ClienteRequest clienteRequest ) throws Exception{
        Cliente cliente = findById(id).get();
        cliente.setNombre(clienteRequest.nombre);
        cliente.setApellido(clienteRequest.apellido);
        cliente.setDni(clienteRequest.dni);
        cliente.setEdad(clienteRequest.edad);
        return grabar(cliente);
    }

    public List<Cliente> deleteCliente(int id){
        Optional<Cliente> cliente = clienteRepo.findById(id);
        if (cliente.isPresent()){
            clienteRepo.delete(cliente.get());
        }else{
            System.out.println("el cliente no existe");
        }
        return clienteRepo.findAll();

    }

    public List<String> apellidosDeClientes() {
        List<Cliente> clientes = clienteRepo.findAll();
        return clientes.stream()
                    .map(c -> "El cliente " + c.getNombre() + ", " + c.getApellido() + " tiene " + c.getCuentas().size() + " cuentas.")
                    .collect(Collectors.toList());
    }


}
