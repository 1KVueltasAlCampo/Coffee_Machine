
import java.util.ArrayList;
import java.util.List;
import com.zeroc.Ice.*;
import comunicacion.*;
import interfaz.ControladorRecetas;
import publisher_subscriber.ServerObservableImp;
import receta.ProductoReceta;
import servicios.*;
import ventas.VentasManager;
import ServerControl.*;
import alarma.Alarma;
import alarma.AlarmasManager;
import reliable_message.RMImp;

public class ServidorCentral {

    public static void main(String[] args) {
        List<String> params = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "server.cfg", params)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("Server");

            ServerControl control = new ServerControl(communicator);

            Alarma alarma = new Alarma(new AlarmasManager(communicator));

            ProductoReceta recetas = new ProductoReceta();
            recetas.setCommunicator(communicator);

            VentasManager ventas = new VentasManager();
            ventas.setCommunicator(communicator);

            ServerObservableImp gateway = new ServerObservableImp();
            gateway.setCommunicator(communicator);

            ControlComLogistica logistic = new ControlComLogistica(control);
            logistic.setCommunicator(communicator);

            ControlComBodega warehouse = new ControlComBodega(control);
            warehouse.setCommunicator(communicator);

            RMImp reliableMessage = new RMImp();
            reliableMessage.setCommunicator(communicator);

            //Endpoints
            adapter.add(alarma, Util.stringToIdentity("Alarmas"));
            adapter.add(ventas, Util.stringToIdentity("Ventas"));
            adapter.add(recetas, Util.stringToIdentity("Recetas"));
            adapter.add(gateway, Util.stringToIdentity("Gateway"));
            adapter.add(reliableMessage, Util.stringToIdentity("ReliableMessage"));
            adapter.add(logistic, Util.stringToIdentity("Logistic"));
            adapter.add(warehouse, Util.stringToIdentity("Warehouse"));
            
            ControladorRecetas controladorRecetas = new ControladorRecetas(gateway, logistic, warehouse);
            controladorRecetas.setRecetaService(recetas);

            gateway.setRecetaService(recetas);

            controladorRecetas.run();
            adapter.activate();
            communicator.waitForShutdown();

        }
    }
}
