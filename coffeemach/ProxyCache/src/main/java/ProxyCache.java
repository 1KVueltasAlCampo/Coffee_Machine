import com.zeroc.Ice.*;

import publisher_subscriber.ProxyCacheObservableImp;
import publisher_subscriber.ProxyCacheObserverImp;

import pubsub.ObservablePrx;
import pubsub.ObserverPrx;

import java.util.*;

public class ProxyCache {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();

    try(Communicator communicator = Util.initialize(args, "proxycache.cfg", extPar)) {

      //Endpoints from server
      ObservablePrx gateway = ObservablePrx.checkedCast(
        communicator.propertyToProxy("gateway")).ice_twoway();
        
      ObjectAdapter adapter = communicator.createObjectAdapter("ProxyCache");
     
      ProxyCacheObservableImp proxyCache = new ProxyCacheObservableImp();
      proxyCache.setCommunicator(communicator);

      //Endpoints 
      adapter.add(proxyCache, Util.stringToIdentity("ProxyCache"));

      ProxyCacheObserverImp proxyCacheImp = new ProxyCacheObserverImp(gateway);
      proxyCacheImp.setObservableImp(proxyCache);

      ObjectPrx objectPrx = adapter.add(proxyCacheImp, Util.stringToIdentity("proxyCache"));
      
      ObserverPrx prx = ObserverPrx.uncheckedCast(objectPrx);
      gateway.attach(prx);
      System.out.println("ProxyCache running");
      adapter.activate();
      communicator.waitForShutdown();

    }
  }
}
