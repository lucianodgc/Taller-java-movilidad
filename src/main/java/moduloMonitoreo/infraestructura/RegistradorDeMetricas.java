package moduloMonitoreo.infraestructura;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistradorDeMetricas {
    
    private MeterRegistry registry;
    private final AtomicInteger cargasActivas = new AtomicInteger(0);
    private Counter cargaCompletaCounter;
    private Counter pagosUteCounter;
    private Counter pagosTarjetaCounter;
    private Counter pagosFallidosCounter;
    private Counter reclamosNegativosCounter;

    @PostConstruct
    public void init() {
        InfluxConfig config = new InfluxConfig() {
            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public String uri() {
                return "http://localhost:8086";
            }

            @Override
            public String db() {
                return "sistemaGestionMovilidad";
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(2);
            }
        };

        this.registry = new InfluxMeterRegistry(config, Clock.SYSTEM);
        registry.gauge("cargas.activas", cargasActivas);
        cargaCompletaCounter = registry.counter("cargas.realizadas");
        pagosUteCounter = registry.counter("pagos.realizados", "medio", "UTE");
        pagosTarjetaCounter = registry.counter("pagos.realizados", "medio", "TARJETA");
        pagosFallidosCounter = registry.counter("pagos.error", "medio", "TARJETA");
        reclamosNegativosCounter = registry.counter("reclamos.procesados", "resultado", "NEGATIVO");
    }

    public void cargaIniciada() {
        cargasActivas.incrementAndGet();
    }

    public void cargaFinalizada() {
        cargasActivas.decrementAndGet();
        cargaCompletaCounter.increment();
    }

    public void pagoRealizado(String tipo) {
        if ("CUENTA_UTE".equals(tipo)) {
            pagosUteCounter.increment();
        } else if ("TARJETA".equals(tipo)) {
            pagosTarjetaCounter.increment();
        }
    }

    public void pagoFallido(String tipo) {
        if ("TARJETA".equals(tipo)) {
            pagosFallidosCounter.increment();
        }
    }

    public void reclamoNegativoProcesado() {
        reclamosNegativosCounter.increment();
    }

    public void resetearBaseDeDatos() {
        try {
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

            java.net.http.HttpRequest dropRequest = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create("http://localhost:8086/query?q=" + java.net.URLEncoder.encode("DROP DATABASE sistemaGestionMovilidad", "UTF-8")))
                    .POST(java.net.http.HttpRequest.BodyPublishers.noBody())
                    .build();
            client.send(dropRequest, java.net.http.HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup() {
        if (registry != null && !registry.isClosed()) {
            registry.close();
        }
    }
}
