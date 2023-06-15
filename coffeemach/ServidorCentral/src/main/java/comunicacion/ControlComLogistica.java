package comunicacion;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import ServerControl.*;
import reliable_message.RMImp;
import servicios.ServicioComLogisticaPrx;


public class ControlComLogistica implements  servicios.logisticCommunication, Runnable {
 
	private ServerControl control;
	private ServicioComLogisticaPrx logistic;
	private Communicator communicator;
	private RMImp reliableMessage;

	public ControlComLogistica(ServerControl control) {
		this.control = control;
	}

	/**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

		/**
     * @param reliableMessage the communicator to set
     */
    public void setRealiableMessage(RMImp realiableMessage) {
		this.reliableMessage = realiableMessage;
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
		logistic.receiveAlarms(reliableMessage.getQueue().toArray(new String[reliableMessage.getQueue().size()]), null);
	}

}
