package moduloCargas.infraestructura.rateLimiter;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RateLimiterFiltro implements ContainerRequestFilter {
	
	@Inject
	private RateLimiter rateLimiter;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

        String path = requestContext.getUriInfo().getPath();
        String ciCliente = requestContext.getUriInfo().getQueryParameters().getFirst("ciCliente");

        if (!path.contains("cargas")) {
            return;
        }

        if (ciCliente == null || ciCliente.isBlank()) {
            return;
        }

		if (rateLimiter.isActivo()) {
			boolean sePermiteEjecutar = rateLimiter.consumir(ciCliente);
			if (!sePermiteEjecutar) {
				System.out.println("El servidor no acepta mensajes");
				requestContext.abortWith(
                    Response.status(Response.Status.TOO_MANY_REQUESTS)
                            .entity("Demasiadas solicitudes, Intente más tarde")
                            .build());
			}
		}
	}
}
