package moduloCargas.interfase.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import moduloCargas.dominio.Carga;
import moduloCargas.dominio.Cargador;
import moduloCargas.dominio.EstacionCarga;
import moduloCargas.interfase.ICargasService;
import moduloCargas.interfase.dto.CargadorDTO;
import moduloCargas.interfase.dto.EstacionCargaDTO;
import moduloCargas.interfase.rest.dto.Request.AltaCargadorRequest;
import moduloCargas.interfase.rest.dto.Request.AltaEstacionRequest;
import moduloCargas.interfase.rest.dto.Request.FinalizarCargaRequest;
import moduloCargas.interfase.rest.dto.Request.IniciarCargaRequest;
import moduloCargas.interfase.rest.dto.Response.CargaResponse;
import moduloCargas.interfase.rest.dto.Response.CargadorResponse;
import moduloCargas.interfase.rest.dto.Response.EstacionResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Path("/cargas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CargasAPI {

    @Inject
    ICargasService cargaService;

    @POST
    @Path("/iniciar")
    public Response iniciarCarga(IniciarCargaRequest request) {
        try {
            cargaService.iniciarCarga(request.getCiCliente(), request.getIdCargador(), request.getReferenciaMedioPago());
            return Response.status(Response.Status.CREATED).entity("Carga iniciada con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/actual/{ciCliente}")
    public Response verCargaActual(@PathParam("ciCliente") String ciCliente) {
        try {
            Carga carga = cargaService.verCargaActual(ciCliente);
            CargaResponse dto = new CargaResponse();
            dto.setIdCarga(carga.getId());
            dto.setEstado(carga.getEstado().toString());
            dto.setIdCargador(carga.getCargador().getId());
            dto.setNombreEstacion(carga.getCargador().getEstacion().getDescripcion());
            dto.setFechaInicio(carga.getFechaInicio());

            return Response.ok(dto).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/historico/{ciCliente}")
    public Response verHistorico(
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

            List<Carga> cargas = cargaService.verHistorico(ciCliente, fechaInicio, fechaFin);
            List<CargaResponse> cargasResponse = new ArrayList<>();
            for (Carga carga : cargas) {
                CargaResponse cargaResponse = new CargaResponse();
                cargaResponse.setIdCarga(carga.getId());
                cargaResponse.setEstado(carga.getEstado().toString());
                cargaResponse.setIdCargador(carga.getCargador().getId());
                cargaResponse.setNombreEstacion(carga.getCargador().getEstacion().getDepartamento());
                cargaResponse.setFechaInicio(carga.getFechaInicio());
                cargaResponse.setFechaFin(carga.getFechaFin());
                cargaResponse.setImporteTotal(carga.getImporteTotal());
                cargasResponse.add(cargaResponse);
            }

            return Response.ok(cargasResponse).build();
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de fecha inválido. Use YYYY-MM-DDTHH:mm:ss").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/finalizar")
    public Response finalizarCarga(FinalizarCargaRequest request) {
        try {
            cargaService.finalizarCarga(request.getIdCargador(), request.getCarga(), request.getRecargo());
            return Response.ok("Carga finalizada con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/cargador")
    public Response altaCargador(AltaCargadorRequest request) {
        try {
            CargadorDTO cargadorDTO = new CargadorDTO(
                    request.getTipoCargador(),
                    request.getTipoConector(),
                    request.getPotenciaMinima(),
                    request.isTieneCable(),
                    request.getIdEstacion()
            );
            cargaService.altaCargador(cargadorDTO);
            return Response.status(Response.Status.CREATED).entity("Cargador creado con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/estacion")
    public Response altaEstacion(AltaEstacionRequest request) {
        try {
            EstacionCargaDTO estacionCargaDTO = new EstacionCargaDTO(
                    request.getDescripcion(),
                    request.getCalle(),
                    request.getDepartamento(),
                    request.getLongitud(),
                    request.getLatitud()
            );
            cargaService.altaEstacion(estacionCargaDTO);
            return Response.status(Response.Status.CREATED).entity("Estación de Carga creada con éxito").build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/estaciones")
    public Response obtenerEstaciones() {
        try {
            List<EstacionCarga> estaciones = cargaService.obtenerEstaciones();
            List<EstacionResponse> estacionesResponse = new ArrayList<>();
            for (EstacionCarga estacion : estaciones) {
                EstacionResponse estacionResponse = new EstacionResponse();
                estacionResponse.setId(estacion.getId());
                estacionResponse.setDescripcion(estacion.getDescripcion());
                estacionResponse.setCalle(estacion.getCalle());
                estacionResponse.setDepartamento(estacion.getDepartamento());
                List<Cargador> cargadores = estacion.getCargadores();
                for (Cargador cargador : cargadores) {
                    CargadorResponse cargadorResponse = new CargadorResponse();
                    cargadorResponse.setId(cargador.getId());
                    cargadorResponse.setTipoConector(cargador.getTipoConector().toString());
                    cargadorResponse.setEstado(cargador.getEstado().toString());

                    estacionResponse.agregarCargador(cargadorResponse);
                }
                estacionesResponse.add(estacionResponse);
            }
            return Response.ok(estacionesResponse).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
