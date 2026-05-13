package moduloClientes.dominio;

import java.util.Date;

public class Tarjeta extends MedioPago {
    String numero;
    Date fechaVencimiento;
    String digitoVerificacion;
    TipoTarjeta tipo;

    public Tarjeta(String numero, Date fechaVencimiento, String digitoVerificacion, TipoTarjeta tipo) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificacion = digitoVerificacion;
        this.tipo = tipo;
    }
}
