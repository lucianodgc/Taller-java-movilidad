package moduloCargas.interfase.rest.dto.Request;

import java.time.LocalTime;

public class FinalizarCargaRequest {
    private String idCargador;
    private int carga;
    private LocalTime recargo;

    public FinalizarCargaRequest() {
    }

    public String getIdCargador() {
        return idCargador;
    }

    public void setIdCargador(String idCargador) {
        this.idCargador = idCargador;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public LocalTime getRecargo() {
        return recargo;
    }

    public void setRecargo(LocalTime recargo) {
        this.recargo = recargo;
    }
}
