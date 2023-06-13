import com.zeroc.Ice.*;


import gateway.ObservablePrx;
import gateway.ObserverPrx;

import java.util.*;

public class ProxyCache {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();

    try(Communicator communicator = Util.initialize(args, "proxycache.cfg", extPar)) {
     ObservablePrx gateway = ObservablePrx.checkedCast(
        communicator.propertyToProxy("gateway")).ice_twoway();
        
      ObjectAdapter adapter = communicator.createObjectAdapter("ProxyCache");

      ProxyCacheImp proxyCacheImp = new ProxyCacheImp(gateway);

      ObjectPrx objectPrx = adapter.add(proxyCacheImp, Util.stringToIdentity("proxyCache"));

      adapter.activate();

      ObserverPrx prx = ObserverPrx.uncheckedCast(objectPrx);

      gateway.attach(prx);

      communicator.waitForShutdown();

    }
  }
}
