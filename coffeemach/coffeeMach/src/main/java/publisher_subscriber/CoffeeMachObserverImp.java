package publisher_subscriber;

import java.util.ArrayList;

import com.zeroc.Ice.Current;

import McControlador.ControladorMQ;
import pubsub.ObservablePrx;

public class CoffeeMachObserverImp implements pubsub.Observer {


    private ObservablePrx proxyCache;
    private ControladorMQ controler;
    

    public CoffeeMachObserverImp(ObservablePrx proxyCache, ControladorMQ controler) {
        this.proxyCache = proxyCache;
        this.controler = controler;
    }


    @Override
    public void update(String[] newRecipes, Current current) {
        ArrayList<String> updatedRecipes = new ArrayList<>();

        //We cast newRecipes from array to arraylist because setUpdateRecipes() receives an arraylist of the recipes.
        for (String recipe : newRecipes) {
            updatedRecipes.add(recipe);
        }

        controler.setUpdatedRecipes(updatedRecipes);
        
        controler.cargarRecetaMaquinas();
    }

}
