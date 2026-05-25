Taller de Java 2026

Esta aplicación consiste en un sistema de gestión de movilidad eléctrica  desarrollado en java utilizando Jakarta EE. La estructura está organizada en tres módulos diferentes, cada uno con su propia lógica, persistecia y servicios.

---- MODULOS ------------------------------------------------------------------------------------------------------- 

Cada modulo se compone de:
- interfase: contratos, dtos
- aplicacion: implementacion de la lógica de negocio
- dominio: modelos y reglas del negocio
- infraestructura(persistencia): acceso a datos

Se usa también Jakarta EE/CDI para:
- @Inject: para inyección de dependencias
- @Transactional: para transacciones de servicios

Los modulos que componen el sistema son los siguientes:

- moduloClientes
Se encarga de gestionar clientes y sus métodos de pago, es responzable de registrar clientes, dar de alta medios de pago, consultar clientes, obtener clientes por ci y registrar reclamos.

- moduloCarga
Gestiona la infraestructura de carga y las sesiones, es responzable de registrar estaciones de carga, registrar cargadores, inicar una carga para un cliente, consultar la carga actual, consultar el historial de cargas, finalizar una carga y calcular importes/recargos.

- moduloPagos
se encarga de gestionar pagos relacionados con cargas, es responsable de registrar el pago de una carga y consultar pagos de un cliente en un rango de fechas.

---- EVENTOS -------------------------------------------------------------------------------------------------------

El proyecto además, hace uso de eventos:
- ClientesNuevoCliente (Clientes)
se publica cuando se registra un nuevo cliente en moduloClientes, contiene la cedula del cliente.

- ClientesNuevoMedioPago (Clientes)
se publica cuando un cliente agrega un nuevo medio de pago, contiene la referencia del medio de pago y la ci del cliente.

- CargasNuevaCarga (Carga)
se publica cuando finaliza una carga en moduloCarga, contiene el id de la carga la ci del cliente y la fecha de inicio.

Los eventos son publicados por sus respectivos modulos:

- moduloClientes publica publicarNuevoCliente() después de crear un cliente y publicarNuevoMedioPago() después de registrar un medio de pago.

- moduloCargas publica publicarNuevaCarga(cargaAciva) cuando finaliza una carga.

Quién escucha los eventos:

- moduloCargas, ObserverModuloCargas escucha ClientesNuevoCliente y llama a altaCliente(...), ClientesNuevoMedioPago
Llama a altaMedioPago(...). Esto permite que el módulo de cargas mantenga información básica de clientes y medios de pago sin depender directamente de la lógica interna de moduloClientes.

- moduloPagos, ObserverModuloPagos escucha a ClientesNuevoCliente y llama a pagosService.altaCliente(...), ClientesNuevoMedioPago llama a pagosService.altaMedioPago(...), CargasNuevaCarga llama a pagosService.altaCarga(...)
Así moduloPagos se entera de nuevos clientes, nuevos medios de pago y nuevas cargas para mantener su propia información.
