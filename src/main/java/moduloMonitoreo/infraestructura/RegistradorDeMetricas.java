package moduloMonitoreo.infraestructura;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistradorDeMetricas {
    
    private MeterRegistry registry;
    private final AtomicInteger cargasActivas = new AtomicInteger(0);
    private Counter cargaCompletaCounter;
    private Counter pagosUteCounter;
    private Counter pagosTarjetaCounter;
    private Counter pagosFallidosCounter;

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
                return "metricasTallerJava";
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }
        };

        this.registry = new InfluxMeterRegistry(config, Clock.SYSTEM);
        registry.gauge("cargas.activas", cargasActivas);
        cargaCompletaCounter = registry.counter("cargas.realizadas");
        pagosUteCounter = registry.counter("pagos.realizados", "medio", "UTE");
        pagosTarjetaCounter = registry.counter("pagos.realizados", "medio", "TARJETA");
        pagosFallidosCounter = registry.counter("pagos.error", "medio", "TARJETA");
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
}
