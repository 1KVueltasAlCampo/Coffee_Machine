import java.util.*;
import com.zeroc.Ice.*;

import Logistic.LogisticImp;
import servicios.ServicioComLogisticaPrx;
import servicios.logisticCommunicationPrx;

public class CmLogistics {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "CmLogistic.cfg", extPar)) {
            
            //Endpoints from server
            logisticCommunicationPrx logistic = logisticCommunicationPrx.checkedCast(
                communicator.propertyToProxy("logisticServer")).ice_twoway();

            ObjectAdapter adapter = communicator.createObjectAdapter("CmLogistic");

            LogisticImp logisticImp = new LogisticImp();

            ObjectPrx objectPrx = adapter.add(logisticImp, Util.stringToIdentity("logistic"));

            ServicioComLogisticaPrx prx = ServicioComLogisticaPrx.uncheckedCast(objectPrx);

            logistic.registerLogisticCenter(prx);

            adapter.activate();
            communicator.waitForShutdown();

        }
    }
}
