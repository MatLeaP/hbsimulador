package hbsimulador.hb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hbsimulador.hb.entities.Cuentas;

@Repository
public interface CuentasRepo extends JpaRepository<Cuentas, Integer>  {
    
}
