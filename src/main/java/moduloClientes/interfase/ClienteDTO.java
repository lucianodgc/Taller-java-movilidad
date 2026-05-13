package moduloClientes.interfase;

import moduloClientes.dominio.TipoCliente;
import moduloClientes.dominio.TipoProfesional;

public class ClienteDTO {
    String cedula;
    String nombreCompleto;
    String telefono;
    String contraseña;
    TipoProfesional tipoProfesional;
    Float descuento;
    TipoCliente tipo;

    public String getCedula() {return cedula;}
    public String getNombreCompleto() {return nombreCompleto;}
    public String getTelefono() {return telefono;}
    public String getContraseña() {return contraseña;}
    public TipoProfesional getTipoProfesional() {return tipoProfesional;}
    public Float getDescuento() {return descuento;}
    public TipoCliente getTipo() {return tipo;}
}
