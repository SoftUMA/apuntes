# Requisitos no funcionales

## Concepto y comprensión de NFR

- Los requisitos __funcionales__ describen las __capacidades__ del sistema (__qué hace__).
- Los requisitos __no funcionales__ describen __cualidades__ del sistema (__cómo lo hace__).

Muchos requisitos tienen una mezcla de ambos tipos (ej. *seguridad*).  
Para facilitar su tratamiento, generalmente los consideraremos como NFR.

Los NFR son los que más influyen en la complejidad del proyecto y son los más complejos de verificar y validar. Esto se debe a que:

- Su realización no se puede "*localizar*" en una parte concreta del sistema.
- La trazabilidad de los mismos es más compleja porque se propagan a múltiples componentes.
- Su especificación, verifiación y prueba son más complejas porque no se basan en qué debe hacer el sistema sino en cómo debe hacerlo.
- El cumplimiento de los NFR no suele ser "*binario*", sino que, normalmente, responde a escalas de valores cualitativos o cuantitativos.
- Los NFR llevan asociados múltiples "*modificadores*" como asunciones y restricciones.
- Por su naturaleza global, la mayoría de NFR interfieren entre sí.  
  Esto implica que su especificación es más compleja porque tiene que hacerse de forma conjunta.

## Relación con requisitos funcionales

- Los NFR suelen implicar la introducción de requisitos funcionales adicionales.
- Los NFR limitan la forma de realizar los FR y establecen restricciones de diseño.
- La mayoría de NFR tienen un impacto en la arquitectura del sistema y en su despliegue.
- Los FR pueden ser incompatibles con los NFR.
- Cumplir algunos FR puede requerir cambios en cómo se realizan los NFR.
- Casi cualquier otro tipo de relación (directa o indirecta) entre FR y NFR es posible.

## Clasificaciones de NFRs

- Por objeto:
    - Del sistema: cómo debe ser el sistema.
    - Del proceso: cómo debe desarrollarse el proceso.
    - Del proyecto: cómo debe ser el proyecto.
- Por tipo de propiedad:
    - Externos: fiabilidad, usabilidad, interoperabilidad, eficiencia, seguridad, privacidad...
    - Internos: legibilidad, flexibilidad, observabilidad, mantenibilidad, portabilidad...

## Especificación de NFRs

- En la especificación:
    - En general, los FR suelen referirse al sistema completo o a una parte.  
      Esto hace que especificar el objeto del requisito sea secundario.
    - Muchos NFR suelen referirse a elementos concretos.  
      *Confidencialidad de un dato, integridad de un mensaje, rendimiento de una conexión...*
- En la implementación:
    - Los FR suelen asociarse a un conjunto concreto de elementos del sistema.
    - Muchos NFR son difíciles de asociar a elementos del sistema, excepto a los elementos funcionales que se usan para realizar dicho NFR.
- Por tanto, las formas tradicionales de especificar FRs, basadas en artefactos independientes del modelo del sistema, no son las más adecuadas para todos los NFRs.
    - Especificar NFRs de ese modo requiere un gran esfuerzo y resulta en especificaciones incompletas y ambigüas.
    - Esto, como veremos, tiene un gran impacto en la trazabilidad y en la especificación de conflictos y compromisos (*trade-offs*).
    - Especificar NFRs en los modelos del sistema resulta más adecuado, pero este tipo de especificaciones aún no se han extendido en la práctica.

## Conflictos y compromisos (*trade-offs*)

Por su naturaleza global, la mayoría de NFRs interfieren entre sí:

- A veces pueden ser incompatibles: __conflicto__.  
  Es necesario especificar bajo qué circunstancias debe prevalecer uno sobre otro.
- A veces son opuestos pero no incompatibles: __compromiso__.  
  Es necesario especificar cuál es la relación óptima entre ellos.

Esto implica que la especificación de NFRs relacionados debe hacerse de forma conjunta o referenciada.

## Trazabilidad de NFRs

### Propagación

Cada tipo de NFR tiene unas reglas de propagación. ej:

- *Confidencialidad de un atributo de una clase* `->` *se propagaa todas las operaciones que accedan a ese atributo, y a todos los resultados de las mismas que no sean "irreversibles"*.
- *Ancho de banda (bandwidth) de una conexión* `->` *en general no se propagaría*.

Es general es inasumible por un humano controlar dicha propagación.  
Es necesario el uso de herramientas de soporte automatizado.

### Trazabilidad

Se trata de saber qué requisitos originan o influyen en cada elemento del sistema.

Se puede usar en ambos sentidos:
- `->`: *Qué componentes se han desarrollado para cumplir un requisito?*
- `<-`: *Qué requisitos se verían afectados por un cambio en un componente?*

Se usa para:
- Validación.
- Auditoría y certificación.
- Mantenimiento y evolución.
- Análisis de impacto de cambios.
- Optimización.

Se gestiona mediante diferentes técnicas:
- Matrices de trazabilidad.
- Estrategias orientadas a modelos.

## Validación de NFRs

En general, los mecanismos de validación (testing, monitoring, formal methods) están muy orientados a la función.
- Los FR se validan fácilmente mediante testing.  
  El requisito especifica la función `->` el test prueba la función.
- Los NFR requieren conocimientos adicionales para su validación.  
  El requisito especifica la cualidad `->` el test necesita encontrar funciones que sirvan para validar esa cualidad.

No es realista esperar que un desarrollador tenga todos los conocimientos necesarios para validar NFRs. Una vez más se necesita soporte automatizado.

La validación de NFRs suele depender de asunciones.

Los NFR son más sensibles al entorno, por lo que la validación "*en laboratorio*" es menos fiable y menos duradera.
- En general se necesita una re-validación cada cierto tiempo.
- Si falla `->` mantenimiento `->` trazabilidad.

## Conceptos relacionados

### Garantía (*Assurance*)

Mecanismos para garantizar que el sistema cumple los requisitos.  
En general son mecanismos proactivos que indican cómo gestionar el desarrollo con vistas a ese cumplimiento.

### Conformidad (*Compliance*)

Mecanismos para comprobar que el sistema cumple los requisitos.  
Son mecanismos que indican cómo verificar ese cumplimiento.

### Certificación

Mecanismos para atestiguar por un organismo independiente que el sistema cumple los requisitos.  
Son mecanismos que sirven para generar pruebas de cumplimiento y para comprobar la validez de esas pruebas.

# Requisitos de seguridad

## Enfoques

### *Attack trees* (Árboles de ataques)

- Modelan los ataques en una estructura en árbol.
- Se usa para calcular el coste y el riesgo de ataques potenciales y decidir las contramedidas.

### *Abuse frames* (Marcos de abuso)

- Representan amenazas de seguridad y derivan requisitos como necesidades de evitar esas amenazas.
- Usan la notación de los Marcos de Problemas (*Problem Frames*).
- Se relacionan con diferentes dominios: máquina, víctima, usuarios, maliciosos...
- Se definen los elementos críticos (*critical assets*).

### *Abuser stories* (Historias de abuso)

- Son historias en lenguaje natural sobre cómo los atacantes abusan del sistema.
- Son el equivalente de las Historias de Usuario en los métodos ágiles.
- Son simples e informales y requieren poco esfuerzo.
- Proporcionan trazabilidad a requisitos de seguridad.
- Las escriben los usuarios, con el apoyo de los desarrolladores.

### *Misuse cases* (Casos de mal uso)

- Extiende los casos de uso de UML para representar comportamientos indeseables.
- Hay un actor especial "*mis-actor*" ("malhechor").
- Los casos de mal uso se relacionan con los casos de uso con tres relaciones especiales: "*prevent*", "*detect*", "*include*".

### *Abuse cases* (Casos de abuso)

- Extiende los casos de uso de UML para representar acciones nocivas para el sistema.
- Usa una estructura en árbol para los ataques.
- Incluye descripciones textuales de los actores, sus capacidades y objetivos.
- Son útiles como mecanismo de comunicación entre las partes.

### *Mal-activity diagrams* (Diagramas de mala actividad)

- Usa diagramas de actividad de UML (con ma misma sintáxis y semántica) para representar las acciones de los actores maliciosos.

### *Security use cases* (Casos de uso de seguridad)

- Se usan casos de uso estándar.
- Complementan los casos de uso para representar requisitos de seguridad.

### *Security problem frames* (Marcos de problemas de seguridad)

- Usan un tipo de patrones para representar modelos de amenazas y requisitos de seguridad.
- Usan diagramas de marcos en los que se representan problemas y soluciones.

### *Barrier analysis diagrams* (Diagramas de análisis de defensas)

- Método gráfico para identificar y documentar requisitos de seguridad que indican tanto las amenazas como las defensas que se establecen.
- Representa cadenas desde las amenazas, pasando por las diferentes defensas, hasta los recursos que deben protegerse.
- Usa una meta-notación para añadir detalles de seguridad.

### Restricciones

- Considera los requisitos de seguridad como restricciones que se materializan en objetivos de seguridad.
- Relaciona esos objetivos con requisitos funcionales y recursos.

### *SI\**

- Se trata de una versión especializada para seguridad del lenguaje de objetivos *i\**, que se usa para capturar requisitos de seguridad desde el punto de vista organizacional.
- Añade cuatro nociones a *i\**: permiso, delegación, confianza y supervisión.

### *Secure Tropos*

- Es una extensión de la metodología Tropos para seguridad.
- Se basa en *i\**, usando los conceptos de objetivo, objetivo suave, tarea, recurso, restricción de seguridad, etc.

### *UMLSec*

- Se trata de una extensión de varios artefactos de UML para representar requisitos de seguridad. Usa estereotipos, etiquetas y restricciones para representar requisitos de seguridad.
- Enfocado a conceptos concretos como control de acceso y confidencialidad.

### *SecureUML*

- Se trata de una serie de estereotipos usados para representar políticas de control de acceso basado en roles de diagramas de clases.
- Se define como un metamodelo y restricciones OCL predefinidas.

### *The FML* (*Formal Methods and Modeling Language*)

- FML es un marco de trabajo basado en UML para representar semánticas de seguridad en entornos de desarrollo, que incluyen los procesos de negocio y el modelo del sistema.
- Combina UML y métodos formales.

### *GridUCSec-Profile*

- Es un perfil de UML para representar casos de uso estereotipados para sistemas grid.
- Usa una redefinición del metamodelo de UML.

### *SecFutur*

- Define un requisito de seguridad como la __aplicación de una serie de propiedades de seguridad a un elemento del sistema__.
- Define una jerarquía de metamodelos, análoga al enfoque MDA, aunque no igual estrictamente hablando.
- Se basa en el concepto de patrón de seguridad para representar las soluciones.
- Cubre todo el proceso de ingeniería, por lo que la especificación de requisitos se realiza con vistas a servir de base a dicho proceso.
- Proporciona mecanismos robustos de trazabilidad y validación de modelos.

> ### Resumen

- La especificación de requisitos de seguridad necesita más precisión y detalles que otro tipo de requisitos.
- En la mayoría de propuestas, los requisitos de seguridad siguen estando desconectados del resto del proceso de ingeniería, desarrollo, despliegue y mantenimiento/evolución.
- La trazabilidad en todo ese proceso aún está mal cubierta.
- Aún no se tiene una solución satisfactoria, pero nos acercamos poco a poco, especialmente con los últimos avances.
- Es frecuente que el hecho de añadir más requisitos de seguridad de los necesarios resulte en un sistema menos seguro.
