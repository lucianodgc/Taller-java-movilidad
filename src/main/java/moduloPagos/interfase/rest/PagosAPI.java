package moduloPagos.interfase.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import moduloPagos.dominio.Pago;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.rest.dto.Response.PagoResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Path("/pagos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagosAPI {

    @Inject
    private IPagosService pagosService;

    /*ESTO NO VA PROBABLEMENTE
    @POST
    public Response pagarCarga(PagarCargaRequest request) {
        try {
            PagoDTO pagoDTO = new PagoDTO(
                    request.getCiCliente(),
                    request.getMonto(),
                    request.getFechaPago(),
                    request.getMedioPago()
            );
            pagosService.pagarCarga(pagoDTO);
            return Response.status(Response.Status.CREATED).entity("Carga pagada con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    */
    @GET
    @Path("/consultar/{ciCliente}")
    public Response consularPagos(
            @PathParam("ciCliente") String ciCliente,
            @QueryParam("fechaInicio") String fechaInicioStr,
            @QueryParam("fechaFin") String fechaFinStr
    ) {
        try {
            LocalDateTime fechaInicio = null;
            LocalDateTime fechaFin = null;

            if (fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
                fechaInicio = LocalDateTime.parse(fechaInicioStr);
            }

            if (fechaFinStr != null && !fechaFinStr.isEmpty()) {
                fechaFin = LocalDateTime.parse(fechaFinStr);
            }

            List<Pago> pagos = pagosService.consultarPagos(ciCliente, fechaInicio, fechaFin);
            List<PagoResponse> pagosResponse = new ArrayList<>();

            for (Pago pago : pagos) {
                PagoResponse pagoResponse = new PagoResponse();
                pagoResponse.setId(pago.getId());
                pagoResponse.setMonto(pago.getMonto());
                pagoResponse.setFechaPago(pago.getFechaPago());
                pagoResponse.setReferenciaMedioPago(pago.getMedioPago().getReferencia());
                pagoResponse.setIdCarga(pago.getCarga().getId());
                pagosResponse.add(pagoResponse);
            }

            return Response.ok(pagosResponse).build();

        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de fecha inválido. Use YYYY-MM-DDTHH:mm:ss").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
