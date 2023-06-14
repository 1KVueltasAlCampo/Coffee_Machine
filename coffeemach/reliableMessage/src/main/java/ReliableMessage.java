import RM.ReliableMessagePrx;

import java.util.List;
import java.util.ArrayList;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;



public class ReliableMessage {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "ReliableMessage.cfg", extPar)) {

            

            ReliableMessagePrx gateway = ReliableMessagePrx.checkedCast(communicator.propertyToProxy("rm")).ice_twoway();
            ObjectAdapter adapter = communicator.createObjectAdapter("ProxyRM");
            /*
            ObservableImp proxyRM = new ObservableImp();
            proxyRM.setCommunicator(communicator);

            adapter.add(proxyRM, Util.stringToIdentity("ProxyRM"));
            */

            ProxyReliableMessageImp proxyReliableMessageImp = new ProxyReliableMessageImp(gateway);
            //proxyReliableMessageImp.setCommunicator(communicator);

            ObjectPrx objectPrx = adapter.add(proxyReliableMessageImp, Util.stringToIdentity("ProxyRM"));

            proxyReliableMessageImp.run();

            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}
