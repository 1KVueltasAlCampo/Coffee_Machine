package publisher_subscriber;
import java.util.ArrayList;

import com.zeroc.Ice.Current;

import pubsub.ObservablePrx;

public class ProxyCacheObserverImp implements pubsub.Observer {

    private ArrayList<String> recetasCache;

    private ObservablePrx server;

    public ProxyCacheObserverImp(ObservablePrx server) {
        this.server = server;
        recetasCache = new ArrayList<>();
    }

    @Override
    public void update(String[] recetasNuevas, Current current) {
        System.out.println("Proxy Actualizado");
        for (String receta : recetasNuevas) {
            recetasCache.add(receta); 
        }
    }

}
