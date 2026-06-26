package moduloClientes.infraestructura.messaging;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@ApplicationScoped
public class EnviarReclamoQueueUtil {

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:jboss/exported/jms/queue/ReclamosQueue")
    private Queue queueReclamos;

    public void enviarMensaje(String mensajeJson) {
        jmsContext.createProducer().send(queueReclamos, mensajeJson);
    }
}