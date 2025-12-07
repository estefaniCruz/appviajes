# Proyecto_tienda_viajes_java
Proyecto de tienda con java y spring boot

# Starlit: E-commerce de Paquetes de Viajes

## Resumen del Proyecto

**Starlit** es una aplicación web desarrollada como Proyecto en el curso (2º DAW). Nace con el objetivo de simular la **operación real de una agencia de viajes digital**, especializada en paquetes de ensueño. La idea principal fue construir una plataforma funcional y moderna, con un **diseño limpio** y una navegación fluida, demostrando sólidos conocimientos en la arquitectura Java y en la construcción de interfaces web **dinámicas e intuitivas**.

El sistema se divide en dos áreas principales para gestionar el ciclo completo de un e-commerce: la experiencia del usuario final y el control total para el administrador.

---

## Funcionalidades Clave

### 1. Área Pública (Experiencia del Usuario)

El *frontend* está diseñado para ser visualmente atractivo y fácil de usar, permitiendo al usuario:

* **Gestión de Identidad:** Capacidad para **registrarse** como nuevo usuario o **iniciar sesión**.
* **Catálogo:** Navegación por los diferentes paquetes de viaje, con la posibilidad de ver la **ficha detallada** de cada destino.
* **Proceso de Compra:** Flujo de pedido claro y guiado, desde **añadir viajes al carrito** hasta completar el proceso de pago y datos de envío.

### 2. Parte de Administración (Control y Gestión)

Esta es el área de control restringida esencial para mantener la integridad y el contenido del negocio:

* **Control Total:** Acceso a **listados completos** de Viajes, Usuarios y Pedidos.
* **Gestión Completa (CRUD):** Posibilidad de crear nuevos viajes, modificarlos, o eliminar cualquier registro de la base de datos.
* **Validaciones:** El sistema incluye validaciones de seguridad (Validation API) para **asegurar la integridad de los datos** (ej. evitar correos electrónicos duplicados) y prevenir errores en el almacenamiento.

---

## Tecnologías Empleadas

El proyecto **Starlit** es una aplicación Full-Stack construida principalmente sobre el ecosistema Java/Spring Boot.

### A. La Base y el Motor (Backend)

| Tecnología | Versión Clave | Propósito en Starlit |
| :--- | :--- | :--- |
| **Java** | **21** | Lenguaje principal para la lógica de negocio. |
| **Spring Boot** | (3.x) | Framework para montar la estructura de la aplicación y el servidor. |
| **Spring Data JPA** | Hibernate | Gestión de la base de datos y persistencia de datos mediante Repositorios y Entidades. |
| **Validation API** | Hibernate Validator | Asegurar la integridad de los datos recibidos en los formularios (campos obligatorios, formatos, rangos). |

### B. La Interfaz y la Magia (Frontend)

| Tecnología | Rol | Función Específica |
| :--- | :--- | :--- |
| **Thymeleaf** | Motor de Vistas (Server-side) | Creación de las páginas HTML y gestión de formularios de administración. |
| **AJAX y jQuery** | Interactividad y Asincronía | Llamadas rápidas al servidor "por detrás" de la web, simplificando JavaScript. |
| **Mustache** | Renderizado JS (Client-side) | Inyección de contenido dinámico (datos de AJAX) en las plantillas del cliente para actualizaciones instantáneas. |
| **CSS3 y FontAwesome** | Diseño y Estilos | Diseño visual, estilos modernos y la iconografía de la aplicación. |

### C. Almacenamiento y Entorno

* **Base de Datos:** **H2 Database** (Utilizada en modo "en memoria" durante el desarrollo para pruebas ágiles).
* **IDE:** **Eclipse IDE** (Entorno principal para la codificación).
* **Build:** **Maven** (Para la gestión de dependencias y construcción del proyecto).

---

## Instalación y Ejecución Local

Para ejecutar Starlit en tu entorno local, necesitas tener instalado **Java 21 SDK** y **Maven**.

1.  **Clonar el Repositorio:**
    ```bash
    git clone [TU_URL_DE_GITHUB]
    cd [nombre-del-proyecto]
    ```
2.  **Abrir en Eclipse:** Importar el proyecto como un proyecto Maven existente.
3.  **Ejecutar:** Ejecutar la clase principal `*Application.java` como una aplicación Spring Boot.
4.  **Acceso:** El servidor se iniciará en `http://localhost:8080/`.

---

## 🔗 Enlaces


* **[Documentación Técnica Web]** https://stovar.pythonanywhere.com/tienda

---

### Autoría

Desarrollado por **Estefani Tovar de la Cruz** como Proyecto Final (2º DAW, 2025/2026).