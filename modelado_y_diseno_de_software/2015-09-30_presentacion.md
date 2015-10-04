# Presentación

## Horario

Clases de grupo grande los lunes.  
Miércoles y Jueves laboratorio.

## Evaluación

- Ejercicios de clase: 10% (__NQE__)
	- 1 o 2 semanales
- Prácticas de laboratorio: 50% (__NPR__)
	- 5 prácticas en total
- Examen final: 100% (__NEX__)

### Nota final:

	NEX * (1 + (NPR + NQE - 5) / 10);

## Prácticas

- Grupos de 5 alumnos
- Entrega cada 2-3 semanas
- Encuestas individuales

```java
if (notaPrX >= 7) {
	examenFinal.remove(prX);
}
```

## Examen Final

```java
if (NQE + NPR < 5) {
	// examen completo
	// calificacion = NEX
} else if (NQE + NPR >= 5 && NQE + NPR < 7) {
	// examen completo
	// calificacion con la formula normal
	// minimo NEX > 3.5
} else if (NQE + NPR >= 7) {
	// examen reducido
	// calificacion con la formula normal
	// minimo NEX > 3.5
}
```

## Bibliografía

- [ ] Object-oriented system analysis and design using UML
	- S. Bennet, J. Rumbaugh, I. Jacobson; McGraw Hill
- [x] Design patterns
	- E. Gamma et al; Addison Wesley
- [ ] Applied java patterns
	- S. Stelting, O. Maassen; Prentice Hall
- [x] Refactoring: improving the design of existing code
	- M. Fowler; Addison Wesley
