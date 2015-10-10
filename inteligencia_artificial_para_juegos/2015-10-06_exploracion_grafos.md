# Juegos de 1 Jugador

## Exploración de grafos

### Algoritmo general

~~~
1. crear G <- s
    ABIERTOS <- (s)
2. crear CERRADOS <- ( )
3. CICLO: si ABIERTOS = ( ) devolver 'FRACASO'
4.      seleccionar 'n' <- primero(ABIERTOS)
        borrar 'n' de ABIERTOS
        añadir 'n' a CERRADOS
5.      si 'n' pertenece al conjunto de los objetivos
            construir y devolver camino solución
6.      expandir el nodo 'n':
        M <- sucesores(n) - antecesores(n), ponerlos en G como suc(n)
7.      para cada n-i perteneciente a M
            si n-i no pertenece a ABIERTOS ni a CERRADOS
                poner puntero n-i -> n
                añadir n-i a ABIERTOS
            si n-i pertenece a ABIERTOS o a CERRADOS
                dirigir su puntero al mejor padre
                si n-i pertenece a CERRADOS ... (y se modificó el puntero)
8.      reordenar ABIERTOS arbitrariamente o según un heurístico
9.      volver a CICLO
~~~

## Ejercicio 1

Existen dos jarras: una de 5 litros (llena) y otra de 2 litros (vacía).  
Se puede vaciar una jarra en la otra o bien en el suelo. Hay que conseguir aislar 1 litro.

### Espacio de estados

La estructura de datos a usar:

~~~
(M, N)
    M = litros en la jarra grande
    N = litros en la jarra pequeña
~~~

__Estado inicial__: `(5, 0)`  
__Estado objetivo__: `(x, 1)`

### Reglas de producción

- Tirar el contenido de las jarras
    - `(M, N) >>> (0, N)`
        - Coste: `1`
        - Precondición: `M != 0`
    - `(M, N) >>> (M, 0)`
        - Coste: `1`
        - Precondición: `N != 0`
- Vaciado *total* de una jarra en otra
    - `(M, N) >>> (0, M + N)`
        - Coste: `1`
        - Precondición: `M + N <= 2 && M != 0`
    - `(M, N) >>> (M + N, 0)`
        - Coste: `1`
        - Precondición: `M + N <= 5 && N != 0`
- Vaciado *parcial* de una jarra en otra
    - `(M, N) >>> (M - (2 - N), 2)`
        - Coste: `1`
        - Precondición: `M + N > 2 && N != 2`
    - `(M, N) >>> (5, N - (5 - M))`
        - Coste: `1`
        - Precondición: `M + N > 5 && M != 5`

## Ejercicio 2

Existe un río. A un lado hay tres misioneros y tres caníbales.  
Hay una barca con capacidad 2. En ningún momento pueden haber más caníbales que misioneros en un lado del río.

### Espacio de estados

Estructura de estados a usar:

~~~
(M, C, B)
    M = misioneros en el lado izq. del río
    C = caníbales en el lado izq. del río
    B = [I, D]
~~~

__Estado inicial__: `(3, 3, I)`  
__Estado final__: `(0, 0, x)`

### Reglas de producción

- Mover una persona
    - `(M, C, I) >>> (M - 1, C, D)`
    - `(M, C, D) >>> (M + 1, C, I)`
    - `(M, C, I) >>> (M, C - 1, D)`
    - `(M, C, D) >>> (M, C + 1, I)`
- Mover dos personas
    - `(M, C, I) >>> (M - 2, C, D)`
    - `(M, C, D) >>> (M + 2, C, I)`
    - `(M, C, I) >>> (M, C - 2, D)`
    - `(M, C, D) >>> (M, C + 2, I)`
    - `(M, C, I) >>> (M - 1, C - 1, D)`
    - `(M, C, D) >>> (M + 1, C + 1, I)`

Coste para __todas__ las reglas: `1`

Precondiciones para __todas__ las reglas:  
- `0 <= M <= 3`
- `0 <= C <= 3`
- `0 <= 3 - M <= 3`
- `0 <= 3 - C <= 3`
- `C <= M && M != 0`

## Ejercicio 3

Tenemos un padre, una madre, dos hijos, dos hijas, un policía y un ladrón.  
Tienen que cruzar el río en una balsa con capacidad 2.  
Solo saben manejar la balsa la madre, el padre y el policía.  
El ladrón no puede permanecer con ningún miembro de la familia sin el policía.  
El padre no puede permanecer con ninguna hija sin que este la madre presente.  
La madre no puede permanecer con ningún hijo sin que este presente el padre.

~~~
Solución:

~~~

## Ejercicio 4

Tenemos las 40 cartas de la baraja española, barajadas [1..7, J, Q, K]. Se ponen en fila.  
Se pueden retirar dos cartas contíguas siempre y cuando sumen 12.  
Se pueden retirar dos cartas contíguas siempre y cuando sean del mismo palo.  
Se pueden retirar dos cartas contíguas siempre y cuando sean del mismo valor.
