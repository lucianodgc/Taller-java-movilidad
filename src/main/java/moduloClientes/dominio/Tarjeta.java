package moduloClientes.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("TARJETA")
public class Tarjeta extends MedioPago {
    String numero;
    LocalDate fechaVencimiento;
    String digitoVerificacion;
    TipoTarjeta tipo;

    protected Tarjeta() {
    }

    public Tarjeta(String referencia, Cliente cliente, String numero, LocalDate fechaVencimiento, String digitoVerificacion, TipoTarjeta tipo) {
        super(referencia, cliente);
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificacion = digitoVerificacion;
        this.tipo = tipo;
    }

}
