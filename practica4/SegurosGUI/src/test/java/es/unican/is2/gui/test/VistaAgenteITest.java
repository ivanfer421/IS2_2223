package es.unican.is2.gui.test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.business.GestionSeguros;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.SegurosDAO;
import es.unican.is2.gui.VistaAgente;

public class VistaAgenteITest {

	private FrameFixture demo;

	@BeforeEach
	public void setUp() {
		ClientesDAO daoContribuyentes = new ClientesDAO();
		SegurosDAO daoVehiculos = new SegurosDAO();

		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);

		VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void test() {
		// Comprobamos que la interfaz tiene el aspecto deseado
		demo.button("btnBuscar").requireText("Buscar");
		
		//  realiza una busqueda de un cliente que existe
		demo.textBox("txtDNICliente").enterText("12345678S");
		demo.button("btnBuscar").click();		
		
		demo.textBox("txtNombreCliente").requireText("Andrés Ortega");
		assertEquals("PLL9597 TODORIESGO", demo.list("txtSeguros").valueAt(0));
		demo.textBox("txtTotalCliente").requireText("675.0");
		
		
		demo.textBox("txtDNICliente").deleteText();
		demo.textBox("txtNombreCliente").deleteText();
		
		
		
		
		// casos no validos
		
		// intentea realizar una busqueda de un dni que no existe
		
		demo.textBox("txtDNICliente").enterText("0000A");
		demo.button("btnBuscar").click();
		
		// el campo del nombre deberia estar vacio
		demo.textBox("txtNombreCliente").requireText("");
		
		
		
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
