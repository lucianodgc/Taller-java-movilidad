package moduloCargas.interfase.rest.dto.Request;

import java.time.LocalTime;

public class FinalizarCargaRequest {
    private int carga;
    private LocalTime recargo;

    public FinalizarCargaRequest() {
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
