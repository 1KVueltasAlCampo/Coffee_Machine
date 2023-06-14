package publisher_subscriber;

import com.zeroc.Ice.Current;

import McControlador.ControladorMQ;
import pubsub.ObservablePrx;


public class ObserverImp implements pubsub.Observer {


    private ObservablePrx server;
    private ControladorMQ controler; 

    public ObserverImp(ObservablePrx server, ControladorMQ controler) {
        this.server = server;
        this.controler = controler;
    }

    @Override
    public void update(String[] recetasCompletas, Current current) {
        controler.cargarRecetaMaquinas();
    }

    
    
}
