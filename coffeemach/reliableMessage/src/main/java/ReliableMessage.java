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
        System.out.println("Hola soy RM");

        try (Communicator communicator = Util.initialize(args, "ReliableMessage.cfg", extPar)) {
            //Endpoint from server
            ReliableMessagePrx gateway = ReliableMessagePrx.checkedCast(communicator.propertyToProxy("rm")).ice_twoway();


            ObjectAdapter adapter = communicator.createObjectAdapter("ProxyRM");

            ProxyReliableMessageImp proxyReliableMessageImp = new ProxyReliableMessageImp(gateway);
            proxyReliableMessageImp.setCommunicator(communicator);
            
            //Endpoint
            adapter.add(proxyReliableMessageImp, Util.stringToIdentity("ProxyRM"));
            

            System.out.println("Reliable Message running");
            proxyReliableMessageImp.run();

            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}
