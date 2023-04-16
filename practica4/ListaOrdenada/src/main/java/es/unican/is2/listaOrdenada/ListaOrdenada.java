package es.unican.is2.listaOrdenada;

import java.util.ArrayList;

/**
 * Clase que implementa una lista ordenada en base al orden natural Los
 * elementos de la lista deben implementar la interfaz Comparable<E>
 */
public class ListaOrdenada<E extends Comparable<E>> implements IListaOrdenada<E> {

	private ArrayList<E> lista = new ArrayList<E>();

	@Override
	public E get(int indice) {
		if (indice > lista.size()) {
			return null;
		}
		return lista.get(indice);
	}

	@Override
	public void add(E elemento) {
		int indice = 0;
		if (lista.size() != 0) {
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) > 0) {
				indice++;
			}
		}
		lista.add(indice, elemento);
	}

	@Override
	public E remove(int indice) {
		if (indice > lista.size()) {
			return null;
		}
		E borrado = lista.remove(indice);
		return borrado;
	}

	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public void clear() {
		lista.clone();
	}
}