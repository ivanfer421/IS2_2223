package es.unican.is2.main;

import es.unican.is2.business.GestionSeguros;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.IClientesDAO;
import es.unican.is2.dao.ISegurosDAO;
import es.unican.is2.dao.SegurosDAO;
import es.unican.is2.gui.VistaAgente;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoContribuyentes = new ClientesDAO();
		ISegurosDAO daoVehiculos = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);

	}

}
