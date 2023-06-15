package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import comunicacion.ControlComBodega;
import comunicacion.ControlComLogistica;
import publisher_subscriber.ServerObservableImp;
import receta.ProductoReceta;


public class ControladorRecetas implements Runnable {

	private ProductoReceta recetaService;

	private ServerObservableImp gateway;

	private ControlComLogistica logistic;

	private ControlComBodega warehouse;

	//This variable is used to validate if a new recipe has ingredients associated
	private int idLastRecipeCreated = 0;

	public ControladorRecetas(ServerObservableImp gateway, ControlComLogistica logistic, ControlComBodega warehouse) {
		this.gateway = gateway;
		this.logistic = logistic;
		this.warehouse = warehouse;
	}

	/**
	 * @param recetaService the recetaService to set
	 */
	public void setRecetaService(ProductoReceta recetaService) {
		this.recetaService = recetaService;
	}

	private InterfazRecetas iR;

	ArrayList<String> listaIng = new ArrayList<String>();

	ArrayList<String> listaReceta = new ArrayList<String>();

	ArrayList<String> listaAsociada = new ArrayList<String>();

	@Override
	public void run() {

		try {
			iR = new InterfazRecetas();
			iR.setLocationRelativeTo(null);
			iR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			iR.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		actualizarVista();
		eventos();

	}

	public void actualizarVista() {

		iR.getTextAreaRecetas().setText("");
		iR.getTextAreaIngredientes().setText("");

		String[] listadoRecetas = recetaService.consultarRecetas(null);

		for (int i = 0; i < listadoRecetas.length; i++) {

			iR.getTextAreaRecetas().setText(
					iR.getTextAreaRecetas().getText() + listadoRecetas[i]
							+ "\n");
		}

		String[] listadoIngredientes = recetaService.consultarIngredientes(null);

		for (int i = 0; i < listadoIngredientes.length; i++) {

			iR.getTextAreaIngredientes().setText(
					iR.getTextAreaIngredientes().getText()
							+ listadoIngredientes[i] + "\n");
		}

	}

	public void eventos() {
		//To add a recipe it is neccessary a name and a price
		iR.getBtnAgregarReceta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!iR.getTextFieldNombreRec().getText().isEmpty()
						&& !iR.getTextFieldPrecioRec().getText().isEmpty()) {

					String listadoRec = recetaService.registrarReceta(iR.getTextFieldNombreRec()
							.getText(),
							Integer.parseInt(iR
									.getTextFieldPrecioRec().getText()),
							null);
					
					//Getting the recipe's id
					try {
						idLastRecipeCreated = Integer.parseInt(listadoRec.split("-")[0]);
					} catch (Exception exception) {
						System.out.println("Error getting the recipe's id");
					}

					listaReceta.add(listadoRec);

					actualizarVista();
				}
				

				iR.getTextFieldNombreRec().setText("");
				iR.getTextFieldPrecioRec().setText("");

			}
		});

		iR.getBtnBorrarReceta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!iR.getTextFieldNombreRec().getText().isEmpty()) {

					recetaService.borrarReceta(Integer.parseInt(iR
							.getTextFieldNombreRec().getText()), null);

					actualizarVista();
				}

				iR.getTextFieldNombreRec().setText("");

			}
		});

		/*To associate ingredients to a recipe it is neccessary to have a recipe created
		 * then type the recipe's id, the ingredient's id and the value
		 * in this format idRecipe-idIngredient-value
		 * There are four ingredients to associate
		 * 1: Agua
		 * 2: Cafe
		 * 3: Azucar
		 * 4: Vaso
		*/
		iR.getBtnRIC().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!iR.getTextFieldAsociacion().getText().isEmpty()) {

					String cadenaA = iR.getTextFieldAsociacion().getText();

					String[] split = cadenaA.split("-");

					int idReceta = Integer.parseInt(split[0]);
					int idIngrediente = Integer.parseInt(split[1]);
					int valor = Integer.parseInt(split[2]);

					recetaService.definirRecetaIngrediente(
							idReceta, idIngrediente, valor, null);

					asociar(idReceta, idIngrediente, valor);

				}

				iR.getTextFieldAsociacion().setText("");
			}
		});

		iR.getBtnActualizarMaquinas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				logistic.sendAlarmsToLogistic(null);
				//warehouse.sendOrdersToWarehouse(null);
				
				//if(!recetaService.consultarSiRecetaTieneIngredientesAsociados(idLastRecipeCreated)) {
				//	JOptionPane.showMessageDialog(iR, "La receta debee tener ingredientes asociados", "Error", JOptionPane.WARNING_MESSAGE);
				//}
				//else {
			
				iR.getBtnActualizarMaquinas().setEnabled(false); //Desactivates the button while the operation is in progress
					
				//Change the appearance of the button
				iR.getBtnActualizarMaquinas().setText("Cargando...");

				//Execute the operation in a new thread
				/*The time it takes for the button to change its appearance from "Cargando..." to "Actualizar Maquinas de Cafe" 
				is slightly longer than the actual time it takes to update the observers.*/
				Thread thread = new Thread(() -> {
					try {
						gateway.notifyObservers(null);

								//Restart the button's appearance
								SwingUtilities.invokeLater(() -> {
									iR.getBtnActualizarMaquinas().setText("Actualizar Maquinas de Cafe");
									iR.getBtnActualizarMaquinas().setEnabled(true); //Activates the button again
								});
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						});
				thread.start();

				//}
			}
		});

	}

	public void asociar(int idReceta, int idIngrediente, int valor) {

		String concat = "";

		for (int i = 0; i < listaReceta.size(); i++) {

			String[] splitReceta = listaReceta.get(i).split("-");

			if (Integer.parseInt(splitReceta[0]) == idReceta) {

				concat += splitReceta[0] + "-" + splitReceta[1] + "-"
						+ splitReceta[2];

				for (int i2 = 0; i2 < listaIng.size(); i2++) {

					String[] splitIngrediente = listaIng.get(i2).split("-");

					if (Integer.parseInt(splitIngrediente[0]) == idIngrediente) {

						concat += "#";

						concat += splitIngrediente[0] + "-"
								+ splitIngrediente[1] + "-"
								+ splitIngrediente[2] + "-"
								+ splitIngrediente[3];

						listaAsociada.add(concat);
					}

				}

			}

		}

	}

}
