package comunicacion;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import ServerControl.ServerControl;
import servicios.ServicioAbastecimientoPrx;
import servicios.WarehouseServicePrx;

public class ControlComBodega implements servicios.WarehouseCommunication, Runnable{

    private ServerControl control;
    private WarehouseServicePrx warehouse;
    private Communicator communicator;

    public ControlComBodega(ServerControl control) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


    @Override
    public void registerWarehouse(WarehouseServicePrx warehouseService, Current current) {
        warehouse = warehouseService;
    }

        @Override
    public void sendOrdersToWarehouse(Current current) {
        String[] arrayDeStrings = {"Orden1", "Orden2", "Orden3", "Orden4"};
        warehouse.receiveOrder(arrayDeStrings, null);
    }
    
}
