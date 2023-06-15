package Logistic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zeroc.Ice.Current;

public class LogisticImp implements servicios.ServicioComLogistica {

    private List<String> alarms;

    public LogisticImp() {
        alarms = new ArrayList<>();
    }

    @Override
    public List<String> asignacionMaquina(int codigoOperador, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignacionMaquina'");
    }

    @Override
    public List<String> asignacionMaquinasDesabastecidas(int codigoOperador, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignacionMaquinasDesabastecidas'");
    }

    @Override
    public boolean inicioSesion(int codigoOperador, String password, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicioSesion'");
    }

    @Override
    public void receiveAlarms(String[] alarms, Current current) {
        this.alarms = Arrays.asList(alarms);

        System.out.println("Logistic received alarms: ");
        for (String alarm : alarms) {
            System.out.println(alarm);
            System.out.println("--------------------------");
        }
    }
    
}
