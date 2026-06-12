package moduloCargas.interfase.evento.out;

import java.time.LocalDateTime;

public class CargasCargaIniciada {
    private Long idCarga;
    private String ciCliente;
    private LocalDateTime fechaInicio;

    public CargasCargaIniciada(Long idCarga, String ciCliente, LocalDateTime fechaInicio) {
        this.idCarga = idCarga;
        this.ciCliente = ciCliente;
        this.fechaInicio = fechaInicio;
    }

    public Long getIdCarga() {
        return idCarga;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
}

