package config;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import moduloCargas.dominio.TipoCargador;
import moduloCargas.dominio.TipoConector;
import moduloCargas.interfase.ICargasService;
import moduloCargas.interfase.dto.CargadorDTO;
import moduloCargas.interfase.dto.EstacionCargaDTO;
import moduloClientes.dominio.TipoCliente;
import moduloClientes.dominio.TipoMedioPago;
import moduloClientes.dominio.TipoProfesional;
import moduloClientes.dominio.TipoTarjeta;
import moduloClientes.interfase.IClientesService;
import moduloClientes.interfase.dto.ClienteDTO;
import moduloClientes.interfase.dto.MedioPagoDTO;
import moduloPagos.interfase.IPagosService;

import java.time.LocalDate;

@Singleton
@Startup
public class DataInitializer {

    @Inject
    private IClientesService clientesService;
    @Inject
    private ICargasService cargasService;

    @Inject
    private IPagosService pagosService;

    @PostConstruct
    public void init() {

        //CLIENTES

        ClienteDTO clienteDTO;
        //REGISTAR ADMIN
        clienteDTO = new ClienteDTO("admin", "admin", null, "passAdmin", null, 0, TipoCliente.COMUN);
        clientesService.registrarCliente(clienteDTO);

        MedioPagoDTO medioPagoDTO;
        String cedula;
        //CLIENTE COMUN
        cedula = "55415692";
        clienteDTO = new ClienteDTO(cedula, "Luciano Di Giovanni", "092098912", "pass1234", null, 0, TipoCliente.COMUN);
        clientesService.registrarCliente(clienteDTO);

        medioPagoDTO = new MedioPagoDTO(null,"4500123456789013", LocalDate.of(2028, 12, 31), "123", TipoTarjeta.VISA, TipoMedioPago.TARJETA);
        clientesService.altaMedioPago(cedula, medioPagoDTO);

        medioPagoDTO = new MedioPagoDTO("UTE-123456",null, null, null, null, TipoMedioPago.CUENTA_UTE);
        clientesService.altaMedioPago(cedula, medioPagoDTO);

        //CLIENTE PROFESIONAL
        cedula = "12345678";
        clienteDTO = new ClienteDTO(cedula, "Igncaio Sugo", "091592573", "pass4321", TipoProfesional.TAXI, 30, TipoCliente.PROFESIONAL);
        clientesService.registrarCliente(clienteDTO);

        medioPagoDTO = new MedioPagoDTO(null,"5341235641236313", LocalDate.of(2029, 6, 21), "321", TipoTarjeta.OCA, TipoMedioPago.TARJETA);
        clientesService.altaMedioPago(cedula, medioPagoDTO);

        medioPagoDTO = new MedioPagoDTO("UTE-987654",null, null, null, null, TipoMedioPago.CUENTA_UTE);
        clientesService.altaMedioPago(cedula, medioPagoDTO);


        //CARGAS

        EstacionCargaDTO estacionCargaDTO;
        CargadorDTO cargadorDTO;
        //ALTA ESTACION
        estacionCargaDTO = new EstacionCargaDTO("Estación Piriapolis", "Misiones", "Maldonado", -58.1523, -24.9205);
        cargasService.altaEstacion(estacionCargaDTO);

        estacionCargaDTO = new EstacionCargaDTO("Estación Punta Carretas", "Bulevar Artigas 123", "Montevideo", -56.1523, -34.9205);
        cargasService.altaEstacion(estacionCargaDTO);

        //ALTA CARGADOR
        cargadorDTO = new CargadorDTO(TipoCargador.RAPIDO, TipoConector.TIPO_2, 50, true, "1");
        cargasService.altaCargador(cargadorDTO);

        cargadorDTO = new CargadorDTO(TipoCargador.RAPIDO, TipoConector.TIPO_2, 50, true, "2");
        cargasService.altaCargador(cargadorDTO);
    }
}