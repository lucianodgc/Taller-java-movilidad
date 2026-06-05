package moduloClientes.dominio;

import jakarta.persistence.*;

@Entity(name = "CuentaUTEClientes")
@DiscriminatorValue("UTE")
public class CuentaUTE extends MedioPago {
    @Column(name = "numero_cuenta")
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