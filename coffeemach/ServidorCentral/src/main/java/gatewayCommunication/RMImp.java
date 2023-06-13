package gatewayCommunication;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import gateway.ObserverPrx;
import receta.ProductoReceta;

public class RMImp implements gateway.ReliableMessage {

    private Communicator communicator;

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void notifyAlarm(String a, Current current) {
        String[] partsOfAlarm = a.split("@s");

        switch(Integer.parseInt(partsOfAlarm[0])){
            
        }
    }

}
