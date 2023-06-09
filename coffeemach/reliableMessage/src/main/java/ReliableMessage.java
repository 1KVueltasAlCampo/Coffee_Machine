import RM.ReliableMessagePrx;

import java.util.List;
import java.util.ArrayList;

import com.zeroc.Ice.*;




public class ReliableMessage {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();
        System.out.println("Hola soy RM");

        try (Communicator communicator = Util.initialize(args, "ReliableMessage.cfg", extPar)) {
            //Endpoint from server
            ReliableMessagePrx gateway = ReliableMessagePrx.checkedCast(communicator.propertyToProxy("relSer")).ice_twoway();


            ObjectAdapter adapter = communicator.createObjectAdapter("ProxyRM");

            ProxyReliableMessageImp proxyReliableMessageImp = new ProxyReliableMessageImp(gateway);
            proxyReliableMessageImp.setCommunicator(communicator);
            
            //Endpoint
            adapter.add(proxyReliableMessageImp, Util.stringToIdentity("RM"));
            

            System.out.println("Reliable Message running");

            adapter.activate();

            System.out.println("Adapter activated v2");

            proxyReliableMessageImp.start();

            communicator.waitForShutdown();
        }
    }
}