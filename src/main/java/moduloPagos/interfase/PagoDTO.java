package moduloPagos.interfase;


import moduloPagos.dominio.MedioPago;

import java.time.LocalDate;

public class PagoDTO {
    private Long id;
    private String ciCliente;
    private Float monto;
    private LocalDate fechaPago;
    private MedioPago medioPago;

    public PagoDTO(Long id, String ciCliente, Float monto, LocalDate fechaPago, MedioPago medioPago) {
        this.id = id;
        this.ciCliente = ciCliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
    }

    public Long getId() {
        return id;
    }

    public String getCliente() {
        return ciCliente;
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
