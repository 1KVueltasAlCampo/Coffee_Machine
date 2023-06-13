import java.util.ArrayList;

import com.zeroc.Ice.Current;
import gateway.Alarma;

import gateway.ObservablePrx;

public class ProxyCacheImp implements gateway.Observer {

    private ArrayList<String> recetasCache;

    private ObservablePrx server;

    public ProxyCacheImp(ObservablePrx server) {
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
