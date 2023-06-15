import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import bodega.WareHouseImpl;
import servicios.ServicioComLogisticaPrx;
import servicios.WarehouseCommunicationPrx;
import servicios.WarehouseServicePrx;
import servicios.logisticCommunicationPrx;

public class BodegaCentral {

    public static void main(String[] args) {
            List<String> extPar = new ArrayList<>();
            try (Communicator communicator = Util.initialize(args, "warehouse.cfg", extPar)) {
            
            //Endpoints from server
            WarehouseCommunicationPrx wareHouse = WarehouseCommunicationPrx.checkedCast(
                communicator.propertyToProxy("warehouseServer")).ice_twoway();

            ObjectAdapter adapter = communicator.createObjectAdapter("Warehouse");

            WareHouseImpl wareHouseImpl = new WareHouseImpl();

            ObjectPrx objectPrx = adapter.add(wareHouseImpl, Util.stringToIdentity("warehouse"));

            WarehouseServicePrx prx = WarehouseServicePrx.uncheckedCast(objectPrx);

            wareHouse.registerWarehouse(prx);

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
