package moduloClientes.interfase;

import moduloClientes.dominio.TipoMedioPago;
import moduloClientes.dominio.TipoTarjeta;

import java.time.LocalDate;

public class MedioPagoDTO {
    String numeroCuenta;

    String numero;
    LocalDate fechaVencimiento;
    String digitoVerificacion;
    TipoTarjeta tipoTarjeta;

    TipoMedioPago tipo;

    public MedioPagoDTO(String numeroCuenta, String numero, LocalDate fechaVencimiento, String digitoVerificacion, TipoTarjeta tipoTarjeta) {
        this.numeroCuenta = numeroCuenta;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificacion = digitoVerificacion;
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public TipoMedioPago getTipo() {
        return tipo;
    }
}
