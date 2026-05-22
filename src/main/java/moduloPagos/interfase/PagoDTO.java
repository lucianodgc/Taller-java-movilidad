package moduloPagos.interfase;

import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.MedioPago;

import java.time.LocalDate;

public class PagoDTO {
    private Cliente cliente;
    private Float monto;
    private LocalDate fechaPago;
    private MedioPago medioPago;

    public PagoDTO(Cliente cliente, Float monto, LocalDate fechaPago, MedioPago medioPago) {
        this.cliente = cliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Float getMonto() {
        return monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }
}
