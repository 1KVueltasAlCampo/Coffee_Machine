import com.zeroc.Ice.*;

import McControlador.ControladorMQ;
import gateway.ObservablePrx;
import gateway.ObserverPrx;
import gatewayCommunication.ObserverImp;

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
      ObservablePrx gateway = ObservablePrx.checkedCast(
        communicator.propertyToProxy("gateway")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");

      ControladorMQ service = new ControladorMQ();
      service.setAlarmaService(alarmaS);
      service.setVentas(ventas);
      service.setRecetaServicePrx(recetaServicePrx);

      ObserverImp observerImp = new ObserverImp(gateway,service);
      ObjectPrx objectPrx = adapter.add(observerImp, Util.stringToIdentity("observer"));

      service.run();   
      adapter.add((ServicioAbastecimiento) service, Util.stringToIdentity("abastecer"));
      adapter.activate();

      ObserverPrx prx = ObserverPrx.uncheckedCast(objectPrx);
      
      gateway.attach(prx);
      communicator.waitForShutdown();
    }
  }
}
