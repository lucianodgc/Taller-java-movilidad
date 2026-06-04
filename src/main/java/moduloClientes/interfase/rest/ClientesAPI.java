package moduloClientes.interfase.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.ClienteProfesional;
import moduloClientes.dominio.TipoCliente;
import moduloClientes.interfase.IClientesService;
import moduloClientes.interfase.dto.ClienteDTO;
import moduloClientes.interfase.dto.MedioPagoDTO;
import moduloClientes.interfase.rest.Request.AltaMedioPagoRequest;
import moduloClientes.interfase.rest.Request.RealizarReclamoRequest;
import moduloClientes.interfase.rest.Request.RegistrarClienteRequest;
import moduloClientes.interfase.rest.Response.ClienteResponse;
import java.util.ArrayList;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientesAPI {

    @Inject
    private IClientesService clientesService;

    @POST
    public Response registrarCliente(RegistrarClienteRequest request) {
        try {
            ClienteDTO clienteDTO = new ClienteDTO(
                    request.getCedula(),
                    request.getNombreCompleto(),
                    request.getTelefono(),
                    request.getContraseña(),
                    request.getTipoProfesional(),
                    request.getDescuento(),
                    request.getTipo()
            );
            clientesService.registrarCliente(clienteDTO);
            return Response.status(Response.Status.CREATED).entity("Cliente registrado con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response obtenerClientes() {
        try {
            List<ClienteResponse> clientesResponse = new ArrayList<>();
            List<Cliente> clientes = clientesService.obtenerClientes();

            for (Cliente cliente : clientes) {
                ClienteResponse clienteResponse = new ClienteResponse();
                clienteResponse.setCedula(cliente.getCedula());
                clienteResponse.setNombreCompleto(cliente.getNombreCompleto());
                clienteResponse.setTelefono(cliente.getTelefono());
                if (cliente instanceof ClienteProfesional clienteProfesional) {
                    clienteResponse.setTipoProfesional(clienteProfesional.getTipo().toString());
                    clienteResponse.setDescuento(clienteProfesional.getPorcentajeDescuento());
                    clienteResponse.setTipo(TipoCliente.PROFESIONAL.toString());
                } else {
                    clienteResponse.setTipo(TipoCliente.COMUN.toString());
                }
                clientesResponse.add(clienteResponse);
            }
            return Response.ok(clientesResponse).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{ciCliente}/medios-pago")
    public Response altaMedioPago(
            @PathParam("ciCliente") String ciCliente,
            AltaMedioPagoRequest request
    ) {
        try {
            MedioPagoDTO medioPagoDTO = new MedioPagoDTO(
                    request.getNumeroCuenta(),
                    request.getNumero(),
                    request.getFechaVencimiento(),
                    request.getDigitoVerificacion(),
                    request.getTipoTarjeta(),
                    request.getTipo()
            );
            clientesService.altaMedioPago(ciCliente, medioPagoDTO);
            return Response.status(Response.Status.CREATED).entity("Medio de pago registrado con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{ciCliente}/reclamos")
    public Response realizarReclamo(
            @PathParam("ciCliente") String ciCliente,
            RealizarReclamoRequest request
    ) {
        try {
            clientesService.realizarReclamo(ciCliente, request.getComentario());
            return Response.status(Response.Status.CREATED).entity("Reclamo realizado con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}