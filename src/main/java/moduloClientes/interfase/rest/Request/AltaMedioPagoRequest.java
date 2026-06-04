package moduloClientes.interfase.rest.Request;

import moduloClientes.dominio.TipoMedioPago;
import moduloClientes.dominio.TipoTarjeta;

import java.time.LocalDate;

public class AltaMedioPagoRequest {
    String numeroCuenta;
    String numero;
    LocalDate fechaVencimiento;
    String digitoVerificacion;
    TipoTarjeta tipoTarjeta;
    TipoMedioPago tipo;

    public AltaMedioPagoRequest() {
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public TipoMedioPago getTipo() {
        return tipo;
    }

    public void setTipo(TipoMedioPago tipo) {
        this.tipo = tipo;
    }
}
