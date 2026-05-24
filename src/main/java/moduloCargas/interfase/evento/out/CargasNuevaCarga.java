package moduloCargas.interfase.evento.out;

import java.time.LocalDateTime;

public class CargasNuevaCarga {
    private Long id;
    private String ciCliente;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public CargasNuevaCarga(Long id, String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.ciCliente = ciCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
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
