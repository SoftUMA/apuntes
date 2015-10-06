# Juegos de 1 jugador

## Estrategias de búsqueda

Tres características:

1. Base de datos: situaciones distintas
1. Reglas
1. Estrategias

## Sistemas de producción

- __Bases de datos (*estados*)__: __espacio de estados__. Determina la complejidad del algoritmo
	- Base de datos inicial (*estado inicial*)
	- Base de datos objetivo (*estado objetivo*). No se define como una conceptualización, sino como un conjunto de reglas que lo definen
- __Reglas de producción__: reglas lógicas que se aplican a estados y realizan la conversión de un estado en su sucesor. Están sujetas a condiciones. Suelen tener un coste asociado. No todas son aplicables en un momento `t`. Determina la complejidad del algoritmo. Características:
	1. Precondiciones
	1. Antecedente
	1. Consecuente
- Conjunto de __reglas aplicables__
- __Estrategia de control__: determina qué regla se va a aplicar en un momento `t`. Aquí reside la toma de decisiones ("inteligencia").
	- Llevar un registro
	- Determina la dificultad
		- Tiempo
		- Memoria

## Ejemplo

### 8-puzzle (`n-puzzle`)

| 2 | 8 | 3 |
|---|---|---|
| 7 | 1 | 4 |
|   | 6 | 5 |

> Llevar esta configuración a una predeterminada (ordenada)

### Base de datos

__Inicial__:

| 2 | 8 | 3 |
|---|---|---|
| 7 | 1 | 4 |
|   | 6 | 5 |

__Objetivo__:

| 1 | 2 | 3 |
|---|---|---|
| 8 |   | 4 |
| 7 | 6 | 5 |

### Reglas de producción

- Ficha `1` mueve arriba
- Ficha `1` mueve abajo
- Ficha `1` mueve derecha
- ...
- Ficha `8` mueve izquierda

__Total__: 32

__Aplicables__:

- En el mejor de los casos: 4
- En el peor de los casos: 2

### Sintetizamos las reglas de producción

Esta simplificación, a parte de ser mejor que la anterior por el menor número de reglas, es aplicable a cualquier *n-puzzle*

- Blanco arriba
	- Precond: `b.fila != 0`
- Blanco abajo
	- Precond: `b.fila != 2`
- Blanco derecha
	- Precond: `b.col != 2`
- Blanco izquierda
	- Precond: `b.col != 0`

__Total__: 4

__Aplicables__: 2, 3 o 4 en un determinado `t`

### Estrategia de control

Dos tipos principalmente:

- __Irrevocables__ (*función de escalada/descenso*): una vez la decisión de aplicar una regla, no hay marcha atrás. Eficientes desde el punto de vista computacional
- __Tentativas__: las decisiones son explorables en profundidad (sus consecuencias) y es posible dar marcha atrás tras ello
	- __Retroactivas__: eficientes desde el punto de vista de consumo
	- __Exploración de grafos__: eficientes desde el punto de vista de la solución óptima

### Ejemplo irrevocable

__Inicial__:

| 2 | 8 | 3 |
|---|---|---|
| 1 | 6 | 4 |
| 7 |   | 5 |

__Objetivo__:

| 1 | 2 | 3 |
|---|---|---|
| 8 |   | 4 |
| 7 | 6 | 5 |

__Función de escalada__:

~~~
f(e) = - # piezas fuera de su sitio respecto al estado objetivo
~~~

> Paso 0

| 2 | 8 | 3 |
|---|---|---|
| 1 | 6 | 4 |
| 7 |   | 5 |

~~~
f = -4
>>> blanco arriba (f = -3)
~~~

> Paso 1

| 2 | 8 | 3 |
|---|---|---|
| 1 |   | 4 |
| 7 | 6 | 5 |

~~~
f = -3
no hay una opción que lo maximice (random)
>>> blanco arriba (f = -3)
~~~

> Paso 2

| 2 |   | 3 |
|---|---|---|
| 1 | 8 | 4 |
| 7 | 6 | 5 |

~~~
f = -3
>>> blanco izquierda (f = -2)
~~~

> Paso 3

|   | 2 | 3 |
|---|---|---|
| 1 | 8 | 4 |
| 7 | 6 | 5 |

~~~
f = -2
>>> blanco abajo (f = -1)
~~~

> Paso 4

| 1 | 2 | 3 |
|---|---|---|
|   | 8 | 4 |
| 7 | 6 | 5 |

~~~
f = -1
>>> blanco derecha (f = 0)
~~~

> The end !

| 1 | 2 | 3 |
|---|---|---|
| 8 |   | 4 |
| 7 | 6 | 5 |

~~~
f = 0
>>> congrats !
~~~

## Ejemplo tentativa retroactiva

__Reglas de retroceso__:

- Estado ya visitado
- Sin posibilidad de movimientos
- Se excede la longitud del límite de la búsqueda

Por consenso, se selecciona una preferencia en las posibilidades de los movimientos: __*LEFT > UP > RIGHT > DOWN*__
