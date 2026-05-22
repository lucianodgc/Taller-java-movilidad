package moduloPagos.interfase;

import java.time.LocalDateTime;

public class CargaDTO {
    private String ciCliente;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public CargaDTO(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.ciCliente = ciCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
}
