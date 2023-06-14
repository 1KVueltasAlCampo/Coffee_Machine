import com.zeroc.Ice.*;

import McControlador.ControladorMQ;
import pubsub.ObservablePrx;
import pubsub.ObserverPrx;
import RM.ReliableMessagePrx;
import publisher_subscriber.ObserverImp;

import java.util.*;
import servicios.*;

public class CoffeeMach {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "coffeMach.cfg", extPar)) {

      AlarmaServicePrx alarmaS = AlarmaServicePrx.checkedCast(
          communicator.propertyToProxy("alarmas")).ice_twoway();
      VentaServicePrx ventas = VentaServicePrx.checkedCast(
          communicator.propertyToProxy("ventas")).ice_twoway();
      RecetaServicePrx recetaServicePrx = RecetaServicePrx.checkedCast(
          communicator.propertyToProxy("recetas")).ice_twoway();
      //ObservablePrx proxyCache = ObservablePrx.checkedCast(
        //communicator.propertyToProxy("proxycache")).ice_twoway();
      ReliableMessagePrx reliableMessage = ReliableMessagePrx.checkedCast(
        communicator.propertyToProxy("reliableMessage")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");

      ControladorMQ service = new ControladorMQ();
      service.setAlarmaService(alarmaS);
      service.setVentas(ventas);
      service.setRecetaServicePrx(recetaServicePrx);
      service.setGateway(reliableMessage);

      //ObserverImp observerImp = new ObserverImp(proxyCache,service);
      //ObjectPrx objectPrx = adapter.add(observerImp, Util.stringToIdentity("observer"));

      service.run();   
      adapter.add((ServicioAbastecimiento) service, Util.stringToIdentity("abastecer"));
      adapter.activate();

      //ObserverPrx prx = ObserverPrx.uncheckedCast(objectPrx);
      
      //proxyCache.attach(prx);
      communicator.waitForShutdown();
    }
  }
}
