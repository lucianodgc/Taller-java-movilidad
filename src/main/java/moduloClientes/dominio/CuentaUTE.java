package moduloClientes.dominio;

public class CuentaUTE extends MedioPago{
    String numeroCuenta;

    public CuentaUTE(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
}
