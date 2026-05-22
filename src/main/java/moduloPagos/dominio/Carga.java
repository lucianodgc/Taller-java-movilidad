package moduloPagos.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ciCliente;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    protected Carga() {
    }

    public Carga(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.ciCliente = ciCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


}
