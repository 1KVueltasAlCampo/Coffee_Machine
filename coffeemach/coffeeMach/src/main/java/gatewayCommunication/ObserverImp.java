package gatewayCommunication;

import java.util.ArrayList;

import com.zeroc.Ice.Current;

import McControlador.ControladorMQ;
import gateway.ObservablePrx;

public class ObserverImp implements gateway.Observer {


    private ObservablePrx server;
    private ControladorMQ controler;
    

    public ObserverImp(ObservablePrx server, ControladorMQ controler) {
        this.server = server;
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
