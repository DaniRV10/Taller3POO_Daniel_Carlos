# Taller 3: Programación Orientada a Objetos

## Descripcion del Proyecto
En este universo, la magia domina sobre todas las capacidades, y el sistema permite administrar un registro de magos y un catálogo de hechizos clasificados por elementos (Fuego, Tierra, Planta y Agua). 

El sistema cuenta con dos paneles principales: el **Panel de Administrador**, que permite realizar operaciones CRUD completas sobre magos y hechizos con persistencia e impacto directo e inmediato en los archivos de texto (`.txt`), y el **Panel de Analista**, encargado de procesar la información en tiempo real para generar estadísticas complejas, incluyendo el cálculo de puntuaciones individuales de hechizos mediante fórmulas matemáticas personalizadas y la clasificación automatizada de rankings de alto rendimiento (Top 10 Hechizos y Top 3 Magos) mediante algoritmos de ordenación eficientes.

## Integrantes
- Carlos Alberto Montenegro Pérez - 22.154.893-0 - Akr0yy
- Daniel Alexanders Robles Valdenegro - 20.738.244-2 - DaniRV10


## Estructura del proyecto

El código se encuentra organizado bajo una arquitectura limpia y en capas, separando estrictamente la interfaz de usuario de la lógica del negocio mediante interfaces:

* **`dominio`**: Contiene las clases de las entidades del modelo que representan el mundo mágico.
  * **Mago.java**: Modela a los usuarios de la magia, almacenando su nombre y un repertorio dinámico (de uno a muchos) de los hechizos que domina, incluyendo la lógica para calcular su puntuación total acumulada.
  * **Hechizo.java**: Clase abstracta base que modela las propiedades comunes como el nombre, el tipo de elemento y el daño base.
  * **HechizoFuego.java**: Subclase que extiende de Hechizo, incorporando el atributo específico de *duración de quemadura* y su propia fórmula de puntuación.
  * **HechizoTierra.java**: Subclase que extiende de Hechizo, incorporando el atributo específico de *mejora de defensa* y su propia fórmula de puntuación.
  * **HechizoPlanta.java**: Subclase que extiende de Hechizo, incorporando los atributos específicos de *duración de stun* junto a la *cantidad de plantas* y su propia fórmula de puntuación.
  * **HechizoAgua.java**: Subclase que extiende de Hechizo, incorporando los atributos específicos de *cantidad de heal* junto a la *presión de agua* y su propia fórmula de puntuación.

* **`logica`**: Contiene los contratos y el motor principal que procesa las reglas del software.
  * **ISistema.java**: Interfaz que define el contrato de comportamiento del sistema, declarando los métodos para la gestión de entidades, búsquedas, filtros y solicitudes de datos analíticos.
  * **SistemaImpl.java**: Clase controladora maestra que implementa `ISistema`. Se encarga de almacenar las colecciones en memoria (`ArrayList`), procesar la lectura/escritura de los archivos físicos y ejecutar los algoritmos de ordenamiento.
  * **App.java**: Clase que contiene el método `main`. Actúa puramente como la capa de visualización encargada de renderizar los menús interactivos por consola, capturar las entradas del usuario y validar datos de forma segura mediante bloques try-catch.

* **`txts/`**: Carpeta raíz externa que actúa como la base de datos persistente del juego:
  * **Magos.txt**: Contiene las líneas de registros de magos asociados a sus respectivos repertorios mediante delimitadores de texto.
  * **Hechizos.txt**: Contiene el catálogo de hechizos con sus propiedades polimórficas serializadas.


## Instrucciones de ejecución.

Para ejecutar el programa se debe tener instalado un entorno como **Eclipse** o **Java JDK 21**.

En caso de hacerlo en un entorno que no sea el de eclipse como powershell primero se compila.

```bash
javac -d bin src/dominio/*.java src/logica/*.java
```
Luego se ejecuta con el siguiente codigo.

```bash
java -cp bin logica.App
```
Tambien para que el software funcione correctamente, asegurar que en la carpeta txts encuentren los archivos .txts que se indican en la estructura del proyecto.