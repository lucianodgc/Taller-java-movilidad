package moduloCargas.infraestructura.rateLimiter;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RateLimiter {
	
	private Map<String, Bucket> buckets = new ConcurrentHashMap<>();
	private boolean activo = true;
	
	private Bucket crearBucket() {
		Bandwidth limit = 
			Bandwidth.builder()
					.capacity(5)
					.refillGreedy(5, Duration.ofSeconds(2))
					.build();

		return Bucket.builder()
				.addLimit(limit)
				.build();
	}

	public boolean consumir(String usuario) {
		Bucket bucket = buckets.computeIfAbsent(
			usuario,
			k -> crearBucket()
		);

		return bucket.tryConsume(1);
	}
	
	public void activarRateLimiter(boolean estado) {
		System.out.println("Ratelimitir estado: " + estado);
		this.activo = estado;
	}

	public boolean isActivo() {
		return this.activo;
	}
}
