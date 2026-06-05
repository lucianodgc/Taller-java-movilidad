package config;

import java.util.Base64;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.repositorio.IClienteRepository;
import org.mindrot.jbcrypt.BCrypt;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

    @Inject
    private IClienteRepository clienteRepository;

    @Override
    public void filter(ContainerRequestContext ctx) {
        String path = ctx.getUriInfo().getPath().replaceFirst("^/", "");
        String method = ctx.getMethod();

        if (path.equals("clientes") && method.equals("POST")) {
            return;
        }

        String auth = ctx.getHeaderString("Authorization");
        if (auth == null || !auth.startsWith("Basic ")) {
            ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String encoded = auth.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(encoded));
        String[] parts = credentials.split(":");
        String usuarioLogueado = parts[0];
        String passwordDigitada = parts[1];

        String rol = null;
        Cliente cliente;
        cliente = clienteRepository.buscarPorCedula(usuarioLogueado);
        if (cliente.getCedula().equals("admin") && cliente.getNombreCompleto().equals("admin") && BCrypt.checkpw(passwordDigitada, cliente.getContraseña())) {
            rol = "gestor";
        }
        else {
            cliente = clienteRepository.buscarPorCedula(usuarioLogueado);

            if (cliente != null && BCrypt.checkpw(passwordDigitada, cliente.getContraseña())) {
                rol = "movil";
            }
        }

        if (rol == null) {
            ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        if (rol.equals("movil")) {
            boolean accesoPermitido = false;

            if (method.equals("POST") && path.equals("cargas")) {
                accesoPermitido = true;
            }

            if (method.equals("GET") && path.equals("estaciones")) {
                accesoPermitido = true;
            }

            String pathCi = ctx.getUriInfo().getPathParameters().getFirst("ciCliente");
            if (pathCi != null) {
                if (pathCi.equals(usuarioLogueado) && (path.contains("medios-pago") || path.contains("reclamos"))) {
                    accesoPermitido = true;
                }
            }

            if (method.equals("GET") && (path.equals("cargas") || path.equals("cargas/actual"))) {
                String queryCi = ctx.getUriInfo().getQueryParameters().getFirst("ciCliente");
                if (queryCi != null && queryCi.equals(usuarioLogueado)) {
                    accesoPermitido = true;
                }
            }

            if (!accesoPermitido) {
                ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Acceso denegado: No tienes permisos para este recurso o estás intentando ver datos ajenos.")
                        .build());
                return;
            }
        }
    }
}