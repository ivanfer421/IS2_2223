package es.unican.is2.listaOrdenada.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.*;

import es.unican.is2.listaOrdenada.ListaOrdenada;

public class ListaOrdenadaTest {

	private String elemento1;
	private String elemento2;
	private String elemento3;
	private String elemento4;
	private String elemento5;

	ListaOrdenada lo = new ListaOrdenada();
	ListaOrdenada lo2 = new ListaOrdenada();

	@BeforeEach
	public void setUp() throws Exception {
		elemento1 = new String("A");
		elemento2 = new String("A");
		elemento3 = new String("A");
		elemento4 = new String("A");
		elemento5 = new String("A");

		lo.add(elemento1);
		lo.add(elemento2);
		lo.add(elemento3);
		lo.add(elemento4);
		lo.add(elemento5);
	}

	@Test
	public void testGet() {
		// casos validos
		assertEquals(elemento1, lo.get(0));
		assertEquals(elemento2, lo.get(1));
		assertEquals(elemento3, lo.get(2));
		assertEquals(elemento4, lo.get(3));
		assertEquals(elemento5, lo.get(4));

		// casos no validos
		try {
			assertEquals(elemento1, lo.get(lo.size() + 5));
			fail("Deberia lanzar error al intentar acceder a un elemento fuera del rango");
		} catch (Exception e) {
		}
	}

	@Test
	public void testAdd() {
		try {
			lo2.add(elemento1);
			lo2.add(elemento2);
			lo2.add(elemento3);
		} catch (Exception e) {
			fail("No deberia lanzar error");
		}

	}

	@Test
	public void testRemove() {
		try {
			lo.remove(4);
			lo.remove(1);
			lo.remove(1);
		} catch (Exception e) {
			fail("No deberia lanzar error");
		}

		try {
			lo.remove(lo.size() + 4);
			fail("Deberia lanzar error al intentar borrar un elemento que no existe");
		} catch (Exception e) {
		}
	}
}
