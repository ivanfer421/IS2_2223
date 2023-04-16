package es.unican.is2.common.test;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

import es.unican.is2.common.Cobertura;
import es.unican.is2.common.Seguro;
import es.unican.is2.excepciones.OperacionNoValida;

public class SeguroTest {
	
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
	private Seguro seguro11;
	private Seguro seguro12;
	private Seguro seguro13;
	
	@BeforeEach
	public void setUp() throws Exception { 
		seguro1 = new Seguro("1111AAA", 120, Cobertura.TERCEROS, LocalDate.now().minusMonths(5));
		seguro2 = new Seguro("2222BBB", 120, Cobertura.TERCEROSLUNAS, LocalDate.now().minusMonths(5));
		seguro3 = new Seguro("3333CCC", 120, Cobertura.TODORIESGO, LocalDate.now().minusMonths(5));
		
		seguro4 = new Seguro("4444DDD", 100, Cobertura.TERCEROS, LocalDate.now().minusYears(1));
		seguro5 = new Seguro("5555EEE", 100, Cobertura.TERCEROSLUNAS, LocalDate.now().minusYears(1));
		seguro6 = new Seguro("6666FFF", 100, Cobertura.TODORIESGO, LocalDate.now().minusYears(1));
		
		seguro7 = new Seguro("7777GGG", 80, Cobertura.TERCEROS, LocalDate.now().minusYears(2));
		seguro8 = new Seguro("8888HHH", 80, Cobertura.TERCEROSLUNAS, LocalDate.now().minusYears(2));
		seguro9 = new Seguro("9999JJJ", 80, Cobertura.TODORIESGO, LocalDate.now().minusYears(2));
	}
	
	
	@Test 
	public void testConstructor() {
		// casos validos
		
		assertEquals("1111AAA", seguro1.getMatricula());
		assertTrue(120 == seguro1.getPotencia());
		assertTrue(Cobertura.TERCEROS == seguro1.getCobertura()); 
		
		assertEquals("2222BBB", seguro2.getMatricula());
		assertTrue(120 == seguro2.getPotencia());
		assertTrue(Cobertura.TERCEROSLUNAS == seguro2.getCobertura()); 
		
		assertEquals("3333CCC", seguro3.getMatricula());
		assertTrue(120 == seguro3.getPotencia());
		assertTrue(Cobertura.TODORIESGO == seguro3.getCobertura()); 
		
		
		// casos no validos
		
		try {
			seguro10 = new Seguro(null, 100, Cobertura.TERCEROS, LocalDate.now());
			fail("Deberia lanzar excepcion. Matricula incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro11 = new Seguro("5555EEE", -10, Cobertura.TERCEROS, LocalDate.now());
			fail("Deberia lanzar excepcion. Potencia negativa.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro12 = new Seguro("6666FFF", 100, null, LocalDate.now());
			fail("Deberia lanzar excepcion. Cobertura incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro13 = new Seguro("6666FFF", 100, null, LocalDate.now().plusYears(2));
			fail("Deberia lanzar excepcion. Fecha incorrecta.");
		} catch (OperacionNoValida e) {
		}
	}
	
	
	@Test 
	public void testPrecio() {
		// casos validos
		
		assertEquals(384.0, seguro1.precio(), 0.001); // 120, terceros, < 1 anho
		assertEquals(576.0, seguro2.precio(), 0.001); // 120, terceros lunas, < 1 anho
		assertEquals(960.0, seguro3.precio(), 0.001); // 120, todo riesgo, < 1 anho
		assertEquals(378.0, seguro4.precio(), 0.001); // 100, terceros, < 2 anho
		assertEquals(567.0, seguro5.precio(), 0.001); // 100, terceros lunas, < 2 anho
		assertEquals(945.0, seguro6.precio(), 0.001); // 100, todo riesgo, < 2 anho
		assertEquals(400.0, seguro7.precio(), 0.001); // 80, terceros, > 2 anho
		assertEquals(600.0, seguro8.precio(), 0.001); // 80, terceros lunas, > 2 anho
		assertEquals(1000.0, seguro9.precio(), 0.001); // 80, todo riesgo, > 2 anho
		
		
		// casos no validos
		
		try {
			seguro10 = new Seguro("1010ASD", 100, null, LocalDate.now());
			seguro10.precio();
			fail("Deberia lanzar excepcion. Seguro con cobertura incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro11 = new Seguro("1111ASD", -50, Cobertura.TERCEROS, LocalDate.now());
			seguro11.precio();
			fail("Deberia lanzar excepcion. Seguro con potencia incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
		try {
			seguro12 = new Seguro("1212ASD", 80, Cobertura.TERCEROS, LocalDate.now().plusYears(6));
			seguro12.precio();
			fail("Deberia lanzar excepcion. Seguro con fecha incorrecta.");
		} catch (OperacionNoValida e) {
		}
		
	}

}
