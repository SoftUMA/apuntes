# Tema 2: Búsqueda en grafos

Si la estructura usada en el algoritmo general de búsqueda en grafos es una queue (FIFO), se trata de una búsqueda en anchura.  
Si se trata de un stack (LIFO), es búsquda en profundidad.

Ambos algoritmos acaban no cuando añaden el nodo objetivo a la estructura de datos (se genera), sino cuando se selecciona para expansión.

## Ejemplo pizarra

~~~
f(e) = g(e) + h(e)
e -> nodo
f -> función
g -> profundidad
h -> heurística
~~~

| 2 | 8 | 3 |
|---|---|---|
| 1 | 6 | 4 |
| 7 |   | 8 |

__Orden__: LEFT > UP > RIGHT > DOWN

-----

## Propiedades (de qué ?)

~~~
f*(n) = g*(n) + h*(n)

f(n) = g(n) + h(n)
  ALGORITMO A
g -> profundidad
if h -> cumple: h(n) <= h*(n) para todo 'n'
  then -> ALGORITMO A*

A* es admisible: si existe solución, se encuentra, y será óptima.
~~~

Por ejemplo, en la búsqueda en anchura, al usar una queue `h(n)` siempre vale `0`, y `0 <= h*(n) para todo 'n'`.  
Por lo que la búsqueda en anchura es un __caso particular__ de algoritmo A\*.

## Ejemplos

### 8-puzzle

h<sub>1</sub> = \# piezas descolocadas -> ADMISIBLE  
h<sub>2</sub> = sumatorio dis. manhattan de cada pieza a su posición objetivo -> ADMISIBLE

0 < h<sub>1</sub> <= h<sub>2</sub> <= h*

### Problema de las 8 reinas

Colocar 8 reinas en un tablero de ajedrez de manera que no se coman entre ellas

| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---|---|---|---|---|---|---|---|
| . |   |   | Q |   |   |   |   |
| . | Q |   |   |   |   |   |   |
| . |   |   |   |   |   |   | Q |
| . |   | A |   | B | C |   |   |
| . |   |   |   |   |   |   |   |
| . |   |   |   |   |   |   |   |
| . |   |   |   |   |   |   |   |
| . |   |   |   |   |   |   | . |

> Ignorar los malditos puntos, es para que la tabla tenga dimensiones decentes...

h<sub>1</sub> = \# de casillas sin amenazar -> ADMISIBLE ? NO  
h<sub>2</sub> = \# de casillas sin amenazar en la fila más desfavorable -> ADMISIBLE ? SI, prestar atención a las columnas que quedan libres, dejaran libres como máximo ese número, y si tenemos en cuenta las diagonales, disminuirá.

h<sub>1</sub>(A) = 8  
h<sub>1</sub>(B) = 9  
h<sub>1</sub>(C) = 10  
__h<sup>\*</sup> = 4__ -> h<sub>1</sub> NO ES ADMISIBLE

## Propiedades

1. Algoritmo EXPLORAGRAF termina en grafos finitos.
1. En cualquier momento antes de que A\* termine, existe un nodo `n'` perteneciente a ABIERTOS tal que `f(n') <= C*`.
1. Si existe un camino de S a {t<sub>i</sub>}, entonces EXPLORAGRAF termina __siempre__.
1. A\* es admisible.
1. Para todo nodo `n'` seleccionado para su expansión, `f(n') <= C*`.
