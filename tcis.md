# transporte

## esquina noroeste

1. se asignan unidades desde arriba a la izquierda
2. repeat...

## menor coste

1. se busca el menor coste, en caso de empate arbitrariamente
2. repeat...

## voguel

1. se calculan las penalizaciones (diff de los dos menores de filas y columnas)
2. escoger máxima penalización
3. escoger mínimo coste
4. asignar, reajustar
5. repeat...

## solución óptima

### eslabón

> suputamadre...
> > steb yovs, 1994

1. caminito del rey
2. alternar: `-`, `+`, `-`, `+`...
3. coger el menos negativo
  1. restarlo a los `-`
  2. sumarlo a los `+`

### MODI

1. índices para filas y columnas
  1. 0 para el que tenga más asignadas
  2. coste - idx para las demás (asignadas)
2. costes marginales
  1. coste - idxr - idxc (no asignadas)
3. escoger el más negativo y realizar eslabón

# asignación

## húngaro

1. reducción filas
2. reducción columnas
3. cubrir ceros
4. `while not matrix.isReduced()`
  - reducciones posteriores
5. localizar solución óptima

# locuuuras !

## demanda != oferta

equilibrar (at) obvious (dot) com

## inaceptables

coste `M`: MUUUCHO !

## maximización

- restar cada dato del máximo de su fila
- restar a cada dato el mínimo de su fila

