import gateway.ReliableMessagePrx;

public class ProxyReliableMessage {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "ReliableMessage.cfg", extPar)) {

            ObservablePrx gateway = ReliableMessagePrx.checkedCast(communicator.propertyToProxy("gateway")).ice_twoway();

            ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessage");
            
            ObservableImp proxyRM = new ObservableImp();
            proxyRM.setCommunicator(communicator);

            adapter.add(proxyRM, Util.stringToIdentity("ProxyRM"));

            ProxyReliableMessageImp proxyReliableMessageImp = new ProxyReliableMessageImp(gateway);

            ObjectPrx objectPrx = adapter.add(proxyReliableMessageImp, Util.stringToIdentity("proxyCache"));

            proxyReliableMessageImp.run();

            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}
