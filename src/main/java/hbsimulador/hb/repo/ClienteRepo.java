package hbsimulador.hb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hbsimulador.hb.entities.Cliente;


@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer>{
    
}
