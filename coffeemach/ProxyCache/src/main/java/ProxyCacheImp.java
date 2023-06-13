import java.util.HashMap;
import java.util.Map;

import com.zeroc.Ice.Current;

import gateway.ObservablePrx;

public class ProxyCacheImp implements gateway.Observer {

    private Map<String, Object> cache = new HashMap<>();

    private ObservablePrx server;

    public ProxyCacheImp(ObservablePrx server) {
        this.server = server;
    }

    @Override
    public void update(String[] recetasCompletas, Current current) {
        System.out.println("Proxy Actualizado");
        for (String string : recetasCompletas) {
            System.out.println(string);
        }
    }

    
}
