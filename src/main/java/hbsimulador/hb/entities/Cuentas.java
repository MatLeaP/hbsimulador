package hbsimulador.hb.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentas")
@JsonIgnoreProperties(value = {"cliente"})
public class Cuentas{

    @Column(name = "numero_de_cuenta")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numDeCuenta;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @Column(name = "saldo")
    private int saldo;


    
    
    public Cuentas(Cliente cliente, int saldo) {
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public Cuentas(){
        
    }
    
 

    public int getNumDeCuenta() {
        return numDeCuenta;
    }


    //public void setNumDeCuenta(int numDeCuenta) {
      //  this.numDeCuenta = numDeCuenta;
    //}


    public Integer getIdCliente() {
        return this.cliente.getIdCliente();
    }


    public void setIdCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public int getSaldo() {
        return saldo;
    }


    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    
}
