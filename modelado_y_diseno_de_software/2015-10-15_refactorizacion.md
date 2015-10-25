# Refactorización

## Refactorización de código

Cambios realizados en la estructura interna de un producto software:
- Para facilitar su comprensión.
- Para hacer menos costosa su modificación.
- Sin cambiar su comportamiento observable.

Una refactorización es una pequeña modificación del software.  
El proceso de refactorización suele implicar varios cambios consecutivos.

## Qué es y cuándo refactorizar

La refactorización:
- Mejora el diseño del software.
- Facilita su comprensión.
- Ayuda a encontrar errores.
- Ayuda a pasar del diseño a la implementación de manera más *ágil*.

Aplicaremos refactorización cuando:
- Se añada funcionalidad al sistema.
- Se necesite arreglar un error.
- Se haga una revisión de código.

## Limitaciones

- A menudo implica cambiar la interfaz de las clases.
    - Mantener las interfaces originales junto con las nuevas.
- Puede haber una disminución de prestaciones.
    - Eficiencia vs. legibilidad y mantenibilidad.
- En ocasiones es mejor diseñar un sistema desde cero que aplicar refactorización.
    - Ejemplo:  
      Las bases de datos constituyen un contexto donde la refactorización es difícil de aplicar. Cambios en el esquema de la BD fuerzan a migrar los datos (tarea larga y tediosa).

## Situaciones susceptibles de refactorización

### Duplicación de código

- En la misma clase.
- En distintas subclases de una clase dada.
- En clases no relacionadas directamente.

### Métodos excesivamente largos

- Con muchos parámetros.  
  Difíciles de comprender y utilizar. En ocasiones inconsistentes.
- Con muchas variables temporales.
- Con muchos bucles.

### Clases grandes

- Con muchas variables de instancia.
- Con métodos duplicados.

### Si un simple cambio produce muchos pequeños cambios en clases distintas

### Métodos que acceden en exceso a datos de clases ajenas

### Grupos cohesionados de datos

- Pueden constituir clases independientes.

### Instrucciones de selección de casos

### Jerarquías de herencia paralelas

### Cambio divergente

- Si una clase necesita modificarse de distintas formas, atendiendo a razones independientes.  
  Suele ser conveniente dividir la clase en varias.

### Generalización especulativa

- Previsión de métodos que puedan necesitarse eventualmente en el futuro.

### Uso excesivo de objetos intermediarios

### Alto grado de acoplamiento entre clases

- A menudo motivado por un mal uso de la herencia.

### Bibliotecas de clases incompletas

### Clases innecesarias

## Catálogo de refactorizaciones (*M. Fowler*)

### Renombrar método

- El nombre de un método no ofrece información sobre su propósito.

### Mover método

- Un método usa o es usado por características de una clase distinta a la que lo incluye.

### Extraer método

- Un fragmento de código se puede agrupar según algún criterio.  
  Se debe definir un método que encapsule este framento.

### Introducir asertos

- Una sección de código supone alguna propiedad sobre el estado del programa.  
  Esta propiedad debe hacerse explícita con un aserto.

### Encapsular campo

- Si una clase incluye un campo público.  
  Debe definirse privado y proporcionar funciones de acceso.

### Extracción de clases

- Si una clase hace el trabajo que deberían hacer dos.  
  Debe crearse una nueva clase asociada a ella y trasladar allí los campos y métodos relevantes.

### Preservar la unidad de los objetos

- Si se pasan como parámetros en una invocación datos sobre un mismo objeto.  
  Es conveniente enviar el objeto completo.

### Promoción de métodos hacia arriba

- Si dos métodos están definidos de forma similar en dos subclases.  
  Agruparlos en la clase padre común.

### Construcción de un patrón de método

- Si métodos en dos subclases realizan pasos similares en el mismo orden, pero los pasos son diferentes.  
  Cada paso se encapsula en un método con igual signatura, de forma que los métodos originales llegan a ser idénticos.  
  Entonces, dichos métodos pueden subir en la jerarquía.

### Colapsar jerarquías de herencia

- Si una clase y su subclase no difieren significativamente.  
  Fundirlas en una sola.

### Cambiar asociación bidireccional a unidireccional

- Si se tiene una asociación bidireccional pero una clase no necesita características de la otra.  
  Eliminar el extremo innecesario de la asociación.

### Reemplazar herencia con delegación

- Si una subclase utiliza solo una parte de la interfaz de una superclase, o no quiere heredar datos.  
  Crear un campo para la superclase, ajustar métodos y delegar a la superclase, eliminando la subclasificación.

### Ocultar delegados

- Si un cliente invoca una clase delegada de un objeto.  
  Crear métodos sobre el servidor, para ocultar el delegado.

### Reemplazar condicional con polimorfismo

- Si una estructura condicional selecciona el comportamiento de un objeto dependiendo del tipo.  
  Cambiar cada rama del condicional a un método redefinido en una subclase.

### Introducir objeto nulo

- Si se hacen comprobaciones repetitivas sobre un valor `null`.  
  Reemplazar `null` con un objeto nulo.
