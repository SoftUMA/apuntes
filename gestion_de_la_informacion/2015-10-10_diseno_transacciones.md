# Tema 1.3: Diseño de transacciones en bases de datos

## Diseño de transacciones

Las transacciones en una BD de usan para representar cómo evoluciona el sistema.  
El diseño de transacciones consiste en definir cómo se insertan, borran y modifican ocurrencias de todos los objetos del sistema.

- __Operaciones de acceso__ a los datos:
    - __Inserción__ de ocurrencias de entidad o relación.
    - __Borrado__ de ocurrencias de entidad o relación.
    - __Modificación__ del valor de los atributos de ocurrencias de entidad o relación.

-----

Una transacción es una __secuencia de operaciones__ de acceso a los datos que constituye una unidad lógica de ejecución.

- *__A__tomicity*.
- *__C__onsistency*.
- *__I__solation*.
- *__D__urability*.

#### ACID ! (*ISO/IEC 10026-1*).

## Metodología de diseño de transacciones

- __Objetivo__: obtener un conjunto de transacciones mínimas a partir del diagrama E/R.

Este conjunto incluirá, para cada entidad y relación del diagrama, una transacción mínima de __inserción__, una de __borrado__ y varias de __modificación__.

- __Transacciones mínimas__: única forma permitida de modificar la información almacenada. Se pueden usar en transacciones más complejas.

## Transacción mínima

Incluye todas las operaciones que son necesarias para que la modificación del sistema de información sea válida.  
Constará siempre de una __operación básica__ (inserción, borrado o modificación) sobre el objeto (entidad o relación) para el que se está definiendo la transacción.  
Puede incorporar operaciones sobre otras entidades o relaciones con las que esté vinculado.

## Análisis de transacciones

La determinación de las operaciones necesarias para las transacciones mínimas __se deduce lógicamente del E/R__, al considerar las restricciones representadas en el mismo.

En diagramas que no tienen restricciones de integridad añadidas, se [*cocreta*](https://vimeo.com/15774937) de la siguiente manera:

### Inserción

- __Entidades__: si una entidad tiene restricción de existencia en alguna relación `R`, hay que insertar en `R`. En casos particulares hay que insertar en varias entidades a la vez.
- __Relaciones__: si la relación `R` en la que se está insertando participa como objeto agregado con restricción de existencia en otra relación `S`, hay que insertar en `S`.

![](http://i.imgur.com/TbMGKiP.png)

![](http://i.imgur.com/jGTJkk9.png)

### Borrado

- __Entidades__:
    - __Restrictivo respecto a una relación `R`__: la transacción sólo incluye la operación de borrado de la entidad.
    - __En cascada respecto a una relación `R`__: la transacción incluye, además de la operación de borrado de la entidad, el borrado de las ocurrencias de `R` en las que interviene la entidad. Si la entidad tiene restricción de existencia respecto a `R`, el borrado siempre tendrá que ser de este tipo.
- __Relaciones__:
    - __Restrictivo respecto a una entidad o relación__: no hay que añadir ninguna operación.
    - __En cascada respecto a una entidad o relación__: si alguna de las entidades que participan en la relación tienen restricción de existencia en ella, hay que incluir también el borrado de dicha entidad par los casos en los que la ocurrencia de relación borrada sea la última en la que participaba la entidad.
    - Además, si la relación participa como objeto agregado en otra relación, sea `S`, en la transacción se incluye el borrado de `S` para las ocurrencias en las que interviene el agregado.
    - En el borrado en cascada o en la inserción de entidades con restricciones de existencia, las operaciones añadidas a la transacción pueden requerir a su vez una propagación, que también hay que incluir en la transacción.

![](http://i.imgur.com/rQp2Z7f.png)

![](http://i.imgur.com/nXnnNSv.png)

![](http://i.imgur.com/xNWlOAh.png)

### Modificación

- Puede haber muchas transacciones de modificación diferentes.
- Deben diseñarse las transacciones que posibiliten modificar cualquier atributo de cualquier objeto que sea necesario.

## Arquitectura de gestión de transacciones

El __SGBD__ actúa como interfaz entre los programas (transacciones) y el sistema operativo (métodos de acceso a ficheros) para realizar la recuperación física de las páginas o bloques físicos de datos (unidades mínimas de transferencia entre la memoria externa y la memoria principal).  

### Subsistemas SGBD

![](http://i.imgur.com/BfMzu5X.png)

Se diferencian los siguientes subsistemas:

- Gestor de transacciones (*GT*).
- Planificador de tareas o gestor de transacciones concurrentes (*PT*).
- Gestor de memoria caché (*GM*).
- Gestor de recuperación de la base de datos (*GR*).

### Gestor de transacciones (*GT*)

Recibe peticiones de las transacciones.  
Es capaz de servir los datos de dichas transacciones como retorno a su petición, así como los códigos de estado, que informan del nivel de finalización de dicha petición.

### Planificador de tareas (*PT*)

Gestiona el ámbito de concurrencia de dichas transacciones.

En función de las políticas y algoritmos implementados para resolver los conflictos de concurrencia que pueden originarse (*bloqueo*, *métodos de ordenación de transacciones*, *gestión optimista de transacciones*...).

El planificador (__scheduler__) puede decidir, y en qué orden, si acepta o rechaza determinadas peticiones que recibe del gestor de transacciones, implementando primitivas específicas de cancelación (__ABORT__) o reinicio (__RESTART__) de transacciones.

### Gestor de memoria caché (*GM*)

Trata de optimizar el rendimiento global de transacciones (por segundo) del sistema.  
Para ello la política se basa en acaparar la memoria rápida disponible (*buffer pool* (BP) del SGBD).

En el *BP* se alojan los gránulos o páginas intercambiados con el almacenamiento externo.  
El *GM* procura retener estas páginas una vez cargadas en el *BP*.  
Las transacciones que generan modificaciones de estas páginas generan en el *BP* una nueva versión de dicha página denominada __página sucia__ (*dirty page*).

Por otro lado, dado que el *BP* se aloja en memoria volátil, es imposible mantener indefinidamente una página sucia en el mismo, teniendo lugar, antes o después, una descarga de dichas páginas a la memoria externa o base de datos física (*BDF*).

El *GM* tiene que gestionar todas estas operaciones buscando el mejor rendimiento global del sistema.

### Gestor de recuperación de la base de datos (*GR*)

Tiene que implementar los procedimientos adecuados para garantizar la representación de estados persistentes (almacenados en la *BDF*) consistentes.

El *GR* requiere una estructura adicional de datos en memoria externa para garantizar sus actuaciones:

- El __diario__ (*log*) de transacciones:
    - Mantiene en éste un registro de la actividad de todas las transacciones.
    - Mantiene en éste un registro de los procesos de descarga masiva de páginas del *BP* a la *BDF*.

Los registros del *log* están representados por:

- __Puntos de sincronismo de transacciones__: `Begin(T)`, `Commit(T)` y `Abort(T)`. Marcan la terminación, normal o anormal, de una transacción.
- __Puntos de control del sistema__: `CheckPoint`. Marcan la actividad de descarga masiva de páginas del *BP* a la *BDF*.
- __Preimágenes de páginas__: es el estado de una página antes de la actualización. Las preimágenes permitirán devolver, en caso de fallo, el estado de la base de datos al estado consistente inicial.
- __Postimágenes de páginas__: es el estado de una página (página sucia en principio) después de la actualización (inserción, borrado o modificación) generada por una transacción.  
  Las postimágenes permitirán reconstruir, en case de fallo, la actividad de una transacción hacia delantes (*forward recovery*) hasta el punto de confirmación de la misma, si éste se produjo.

Todos estos registros se almacenan en el *log* cronológicamentes, asociando a los mismos la transacción correspondiente.
