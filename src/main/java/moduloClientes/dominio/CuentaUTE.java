package moduloClientes.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("UTE")
public class CuentaUTE extends MedioPago{
    String numeroCuenta;

    protected CuentaUTE() {
    }

    public CuentaUTE(String referencia, Cliente cliente, String numeroCuenta) {
        super(referencia, cliente);
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
}
