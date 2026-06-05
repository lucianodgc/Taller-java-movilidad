package config;

import java.util.Base64;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import moduloClientes.dominio.repositorio.IClienteRepository;

//@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

    @Inject
    private IClienteRepository clienteRepository;

    @Override
    public void filter(ContainerRequestContext ctx) {

        String auth = ctx.getHeaderString("Authorization");

        if (auth == null || !auth.startsWith("Basic ")) {
            ctx.abortWith(Response.status(401).build());
            return;
        }

        String encoded = auth.substring("Basic ".length());

        String credentials = new String(Base64.getDecoder().decode(encoded));

        String[] parts = credentials.split(":");

        String usuario = parts[0];
        String password = parts[1];

        String rol = null;

        if (usuario.equals("admin") && password.equals("1234")) {
            rol = "gestor";
        } else if (usuario.equals("cliente1") && password.equals("1234")) {
            rol = "movil";
        } else {

            ctx.abortWith(Response.status(401).build());
            return;
        }

        String path = ctx.getUriInfo().getPath();

        if(path.startsWith("pagos") && !rol.equals("gestor")) {

            ctx.abortWith(Response.status(403)
                        .entity("Solo gestores")
                        .build());
        }
    }
}
