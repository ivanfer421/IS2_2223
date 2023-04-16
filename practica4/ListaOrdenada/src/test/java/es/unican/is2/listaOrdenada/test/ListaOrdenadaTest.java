package es.unican.is2.listaOrdenada.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenada.ListaOrdenada;

public class ListaOrdenadaTest {

	private String elemento1;
	private String elemento2;
	private String elemento3;
	private String elemento4;
	private String elemento5;
	
	private String elemento6;
	private String elemento7;
	private String elemento8;
	private String elemento9;
	private String elemento10;

	ListaOrdenada lo = new ListaOrdenada();
	ListaOrdenada lo2 = new ListaOrdenada();

	@BeforeEach
	public void setUp() throws Exception {
		lo.clear();
		
		elemento1 = new String("c");
		elemento2 = new String("f");
		elemento3 = new String("j");
		elemento4 = new String("x");
		elemento5 = new String("v");
		
		elemento6 = new String("e");
		elemento7 = new String("r");
		elemento8 = new String("z");
		elemento9 = new String("l");
		elemento10 = new String("a");

		lo.add(elemento1);
		lo.add(elemento2);
		lo.add(elemento3);
		lo.add(elemento4);
		lo.add(elemento5);
		
		lo.add(elemento6);
		lo.add(elemento7);
		lo.add(elemento8);
		lo.add(elemento9);
		lo.add(elemento10);
	}

	@Test
	public void testGet() {
		// casos validos
		assertEquals(elemento10, lo.get(0));
		assertEquals(elemento1, lo.get(1));
		assertEquals(elemento6, lo.get(2));
		assertEquals(elemento2, lo.get(3));
		assertEquals(elemento3, lo.get(4));
		
		// casos no validos
		try {
			assertEquals(null, lo.get(lo.size() + 5));
		} catch (Exception e) {
			fail("No deberia lanzar error, solo deberia devolver null");
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
			assertEquals(null, lo.remove(lo.size() + 4));
		} catch (Exception e) {
			fail("No deberia lanzar error, solo deberia devolver null");
		}
	}
}
