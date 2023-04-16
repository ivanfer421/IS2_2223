package es.unican.is2.common.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.common.Cliente;
import es.unican.is2.common.Cobertura;
import es.unican.is2.common.Seguro;
import es.unican.is2.excepciones.OperacionNoValida;

public class ClienteITest {
	
	private Cliente cliente1;
	private Cliente cliente2;
	
	private Seguro seguro1;
	private Seguro seguro2;
	private Seguro seguro3;
	private Seguro seguro4;
	private Seguro seguro5;
	private Seguro seguro6;
	private Seguro seguro7;
	private Seguro seguro8;
	private Seguro seguro9;
	private Seguro seguro10;
	
	private List<Seguro> lista1 = new LinkedList<Seguro>();
	private List<Seguro> lista2 = new LinkedList<Seguro>();
	
	@BeforeEach
	public void setUp() throws Exception { 
		seguro1 = new Seguro("1111AAA", 150, Cobertura.TERCEROS, LocalDate.now().minusMonths(5));
		seguro2 = new Seguro("2222BBB", 180, Cobertura.TERCEROSLUNAS, LocalDate.now().minusMonths(5));
		seguro3 = new Seguro("3333CCC", 200, Cobertura.TODORIESGO, LocalDate.now().minusMonths(5));
		seguro4 = new Seguro("4444DDD", 110, Cobertura.TERCEROS, LocalDate.now().minusYears(1));
		seguro5 = new Seguro("5555EEE", 100, Cobertura.TERCEROSLUNAS, LocalDate.now().minusYears(1));
		seguro6 = new Seguro("6666FFF", 100, Cobertura.TODORIESGO, LocalDate.now().minusYears(1));
		seguro7 = new Seguro("7777GGG", 80, Cobertura.TERCEROS, LocalDate.now().minusYears(2));
		seguro8 = new Seguro("8888HHH", 70, Cobertura.TERCEROSLUNAS, LocalDate.now().minusYears(2));
		seguro9 = new Seguro("9999JJJ", 60, Cobertura.TODORIESGO, LocalDate.now().minusYears(2));
		
		cliente1 = new Cliente("cl1", "12345A", true);
		cliente2 = new Cliente("cl2", "22345A", false);
		
		lista1.add(seguro1);
		lista1.add(seguro3);
		lista1.add(seguro5);
		lista1.add(seguro7);
		lista1.add(seguro9);
		
		lista2.add(seguro2);
		lista2.add(seguro4);
		lista2.add(seguro6);
		lista2.add(seguro8);
		
		cliente1.setSeguros(lista1);
		cliente2.setSeguros(lista2);
	}
	
	
	@Test 
	public void test() {
		// casos de prueba validos
		assertEquals(cliente1.totalSeguros(), (seguro1.precio() + seguro3.precio() + seguro5.precio() + seguro7.precio()
					+ seguro9.precio()) * 0.75);
		assertEquals(cliente2.totalSeguros(), (seguro2.precio() + seguro4.precio() + seguro6.precio() + seguro8.precio()));
		
		
		// casos de prueba no validos
		try {
			seguro10 = new Seguro(null, 100, Cobertura.TERCEROS, LocalDate.now());
			fail("Deberia lanzar excepcion. Matricula incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro10 = new Seguro("5555EEE", -10, Cobertura.TERCEROS, LocalDate.now());
			fail("Deberia lanzar excepcion. Potencia negativa.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro10 = new Seguro("6666FFF", 100, null, LocalDate.now());
			fail("Deberia lanzar excepcion. Cobertura incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro10 = new Seguro("6666FFF", 100, null, LocalDate.now().plusYears(2));
			fail("Deberia lanzar excepcion. Fecha incorrecta.");
		} catch (OperacionNoValida e) {
		}
	}

}
