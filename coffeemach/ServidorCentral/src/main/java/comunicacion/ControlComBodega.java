package comunicacion;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import ServerControl.ServerControl;
import reliable_message.RMImp;
import servicios.ServicioAbastecimientoPrx;
import servicios.WarehouseServicePrx;

public class ControlComBodega implements servicios.WarehouseCommunication, Runnable{

    private ServerControl control;
    private WarehouseServicePrx warehouse;
    private Communicator communicator;
    private RMImp reliableMessage;

    public ControlComBodega(ServerControl control) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


    @Override
    public void registerWarehouse(WarehouseServicePrx warehouseService, Current current) {
        warehouse = warehouseService;
    }

        @Override
    public void sendOrdersToWarehouse(Current current) {
        String[] generatedOrders = new String[reliableMessage.getQueue().size()];

        for (int i = 0; i < generatedOrders.length; i++) {
            generatedOrders[i] = "Order for alarma: " + reliableMessage.getQueue().poll();
        }

        warehouse.receiveOrder(generatedOrders, null);
    }
    
}
