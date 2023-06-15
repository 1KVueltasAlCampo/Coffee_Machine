package comunicacion;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import ServerControl.*;
import servicios.ServicioComLogisticaPrx;


public class ControlComLogistica implements  servicios.logisticCommunication, Runnable {
 
	private ServerControl control;
	private ServicioComLogisticaPrx logistic;
	private Communicator communicator;

	public ControlComLogistica(ServerControl control) {
		this.control = control;
	}

	/**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }


	@Override
	public void run() {
		logistic.receiveAlarms(null, null);
	}

	@Override
	public void registerLogisticCenter(ServicioComLogisticaPrx scl, Current current) {
		logistic = scl;
	}

	@Override
	public void sendAlarmsToLogistic(Current current) {
		String[] arrayDeStrings = {"Hola", "Mundo", "Java", "Programación"};
		logistic.receiveAlarms(arrayDeStrings, null);
	}

}
