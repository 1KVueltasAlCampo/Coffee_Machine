import java.util.ArrayList;

import com.zeroc.Ice.Current;

import gateway.ObservablePrx;

public class ProxyCacheImp implements gateway.Observer {


    private ObservablePrx server;

    private ObservableImp observableImp;

    public ProxyCacheImp(ObservablePrx server) {
        this.server = server;
    }

            /**
     * @param observableImp the observableImp to set
     */
    public void setObservableImp(ObservableImp observableImp) {
        this.observableImp = observableImp;
    }

    @Override
    public void update(String[] recetasNuevas, Current current) {
        System.out.println("Proxy Actualizado");

        ArrayList<String> updatedRecipes = new ArrayList<>();

        for (String recipe : recetasNuevas) {
            updatedRecipes.add(recipe);
        }

        observableImp.setCacheRecipes(updatedRecipes);

        //Makes the observable proxy cache to notify all the machines
        observableImp.notifyObservers(current);
        
    }

}
