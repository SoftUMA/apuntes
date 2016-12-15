import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Huffman extends HuffmanAbstract {

	/**
	 * Permite generar el arbol de codigos huffman sin establecer los c—digos.
	 * Este paso se realizar‡ en el mŽtodo generarListaCodigo de la clase
	 * HuffmanAbstract
	 */
	public void generarArbol() {

		// Mientras que no haya recorrido la lista completa de nodos...
		while (primero.getDerecha() != null) {
			// Mirar los elementos mas pequeños de la tabla y crear nodos nuevos
			// con la suma de sus pesos "nodos intermedios"
			Nodo aux = new Nodo('\0', 0, false); // Nodo a insertar

			// Al nuevo nodo aux le pongo de frecuencia la suma del nodo por
			// el que voy mas el siguiente
			aux.setFrecuencia(primero.getFrecuencia()
					+ primero.getDerecha().getFrecuencia());

			// Ubico (para mantener el orden en la lista) el nuevo nodo
			aux = ubicarNodo(aux, primero);

			// Le referencio los hijos al nuevo nodo
			aux.setHijoIzq(primero);
			aux.setHijoDer(primero.getDerecha());

			// Itero por la lista
			primero = primero.getDerecha().getDerecha();

			// Coloco la referencia de la tabla hacia el nuevo nodo (elimino los
			// ya utilizados)
		}
		raizArbol = primero;
	}

	/**
	 * Metodo principal para realizar pruebas
	 */
	public static void main(String arg[]) throws FileNotFoundException,
			IOException {
		HuffmanAbstract h = new Huffman();
		String text = "j'aime aller sur le bord de l'eau les jeudis ou les jours impairs";
		InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
		h.readOrigen(is);
		h.imprimirArbol(h.raizArbol, "");
		h.imprimirListaCodigos();
	}

}
