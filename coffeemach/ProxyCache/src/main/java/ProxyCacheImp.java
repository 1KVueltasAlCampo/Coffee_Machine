import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zeroc.Ice.Current;

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
