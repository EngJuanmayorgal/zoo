# 📚 Proyecto Final - Gestión de Zoológico con Patrones de Diseño (Java Web)

## 🌟 Resumen del Proyecto

Este proyecto es una aplicación web dinámica, desarrollada en **Java con Servlets y JSP**, enfocada en la gestión de un zoológico. El objetivo principal fue aplicar **Modelos de Programación y Patrones de Diseño** (**Strategy, DAO, Singleton**) para crear una solución **modular, extensible y de bajo acoplamiento**.

El sistema permite la autenticación de usuarios (administradores y cuidadores), la visualización de animales por zona, y la generación automatizada de reportes de alimentación especializados basados en la dieta de cada animal.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje de Programación:** Java (JDK 8+)
* **Servidor de Aplicaciones:** Apache Tomcat
* **Tecnologías Web:** Servlets, JSP (JavaServer Pages)
* **Base de Datos:** MySQL
* **Acceso a Datos:** JDBC (Java Database Connectivity)
* **Diagramación:** PlantUML (para la documentación)

## 🏗️ Arquitectura y Patrones de Diseño Implementados

El proyecto se estructura bajo el patrón **MVC** y se adhiere a los principios de **Separación de Responsabilidades (SRP)** y **Abierto/Cerrado (OCP)** gracias a la implementación de los siguientes patrones de diseño GoF:

### 1. Patrón Strategy (Estrategia)

Se utiliza para modelar el comportamiento cambiante de la alimentación según la dieta de cada animal.

| Componente | Clase(s) | Rol en el Patrón | Justificación Clave |
| :--- | :--- | :--- | :--- |
| **Strategy (Interfaz)** | `EstrategiaAlimentacion.java` | Define el método `alimentar()`. | Permite cambiar el comportamiento sin modificar la clase Contexto. |
| **Concrete Strategies** | `AlimentacionCarnivora`, `AlimentacionHerbivora`, `AlimentacionOmnivora` | Implementan el algoritmo de cálculo de raciones y tipo de comida. | **OCP:** Se pueden añadir nuevas dietas creando nuevas clases sin alterar el código existente. |
| **Context** | `GestorAlimentacion.java` | Instancia y ejecuta la estrategia adecuada en tiempo de ejecución. | Su responsabilidad es gestionar la estrategia, no implementarla. |

### 2. Patrón Data Access Object (DAO)

Aísla la lógica de acceso a datos de la lógica de negocio.

| Componente | Clase(s) | Propósito | Justificación Clave |
| :--- | :--- | :--- | :--- |
| **DAO** | `AnimalDAO.java`, `UsuarioDAO.java` | Abstraen las operaciones CRUD (SQL/JDBC). | **SRP y Mantenibilidad:** Permite cambiar el motor de base de datos o el framework de persistencia modificando solo estas clases. |

### 3. Patrón Singleton

Garantiza que la clase de conexión tenga una única instancia.

| Componente | Clase | Propósito | Justificación Clave |
| :--- | :--- | :--- | :--- |
| **Singleton** | `Conexion.java` | Proporciona un punto de acceso único a la conexión JDBC. | **Eficiencia de Recursos:** Controla el uso de la conexión a la base de datos, que es un recurso limitado y costoso. |

## 📐 Diagrama de Clases UML

El diagrama completo se puede generar usando el código PlantUML proporcionado en la documentación adjunta, que muestra las relaciones de composición y dependencia de los patrones implementados.

<img width="1323" height="573" alt="image" src="https://github.com/user-attachments/assets/5472df60-e3a2-4447-966d-d159e3c031ba" />

## 📁 Estructura del Código

## 🚀 Instalación y Configuración

Sigue estos pasos para levantar el proyecto localmente:

### 1. Configuración de la Base de Datos (MySQL)

**A. Creación de la Base de Datos y Tablas:**

Ejecuta el siguiente script completo (`Zoo.sql`) en tu cliente MySQL para crear la base de datos `zoologico` y sus tablas.

**B. Configuración de Conexión Java:**

1.  Asegúrate de que el driver de MySQL (`mysql-connector-java.jar`) esté en el classpath de tu proyecto o servidor Tomcat.
2.  Verifica que las credenciales en `datos/Conexion.java` coincidan con tu configuración local. Si usas las credenciales por defecto, no es necesario hacer cambios:
    ```java
    static String dbName = "zoologico";
    static String user = "root";
    static String pass = "mysql";
    ```

### 2. Despliegue en Servidor

1.  Importa el proyecto en tu IDE (Eclipse, NetBeans, IntelliJ) como un proyecto web dinámico.
2.  Compila y despliega el proyecto en un servidor **Apache Tomcat**.
3.  Accede a la aplicación en tu navegador: `http://localhost:8080/.../`

## 📝 Uso del Sistema

* **Autenticación:** Permite el registro e ingreso de usuarios con roles `user` (cuidador) o `admin` (administrador).
    * **Credenciales de prueba:** Usuario: `juan1`, Contraseña: `123`, Rol: `admin`.
* **Listado de Animales:** Muestra los animales cargados en la base de datos por zona.
* **Reporte de Alimentación (Patrón Strategy):** El sistema genera automáticamente el plan de alimentación (tipo de comida, cantidad, frecuencia) para cada animal, instanciando la estrategia específica basada en la dieta del animal (`GestorAlimentacion.crearPara(animal)`).

## 🧑‍💻 Autores
* **Materia:** Modelos de Programación
* Juan David Mayorga Lopez
* Mariam Betin Escobar -20232020300
