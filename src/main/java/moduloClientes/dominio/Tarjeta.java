package moduloClientes.dominio;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "TarjetaClientes")
@DiscriminatorValue("TARJETA")
public class Tarjeta extends MedioPago {
    @Column(name = "numero")
    String numero;
    @Column(name = "fecha_vencimiento")
    LocalDate fechaVencimiento;
    @Column(name = "digito_verificacion")
    String digitoVerificacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tarjeta")
    TipoTarjeta tipoTarjeta;

    protected Tarjeta() {
    }

    public Tarjeta(String referencia, Cliente cliente, String numero, LocalDate fechaVencimiento, String digitoVerificacion, TipoTarjeta tipoTarjeta) {
        super(referencia, cliente);
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificacion = digitoVerificacion;
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }
}
