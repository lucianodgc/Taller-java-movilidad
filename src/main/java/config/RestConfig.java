package config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@DeclareRoles({"gestor", "movil"})
@ApplicationPath("/api")
public class RestConfig extends Application {}
