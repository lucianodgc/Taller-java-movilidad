Taller de Java 2026

Esta aplicación consiste en un sistema de gestión de movilidad eléctrica  desarrollado en java utilizando Jakarta EE. La estructura está organizada en tres módulos diferentes, cada uno con su propia lógica, persistecia y servicios

Cada modulo se compone de:
- interfase: contratos, dtos
- aplicacion: implementacion de la lógica de negocio
- dominio: modelos y reglas del negocio
- infraestructura(persistencia): acceso a datos

se usa también Jakarta EE/CDI para:
- @Inject: para inyección de dependencias
- @Transactional: para transacciones de servicios

Los modulos que componen el sistema son los siguientes:
- moduloClientes
Se encarga de gestionar clientes y sus métodos de pago, es responzable de registrar clientes, dar de alta medios de pago consultar clientes, obtener clientes por ci y registrar reclamos

- moduloCarga
gestiona la infraestructura de carga y las sesiones, es responzable de registrar estaciones de carga, registrar cargadores, inicar una carga para un cliente, consultar la carga actual, consultar el historial de cargas y finalizar una carga y calcular importes/recargos

- moduloPagos
se encarga de gestionar pagos relacionados con cargas, es responzable de registrar el pago de una carga y consultar pagos de un cliente en un rango de fechas