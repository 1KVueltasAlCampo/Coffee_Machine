module servicios{

    sequence<string> StringArr;
    ["java:type:java.util.ArrayList<String>"] 
    sequence<string> StringSeq;
    ["java:serializable:java.util.Date"]
    sequence<byte> Date;

    dictionary<string,int> MapStrInt;


    interface ServicioComLogistica{
      StringSeq asignacionMaquina(int codigoOperador);
	    StringSeq asignacionMaquinasDesabastecidas(int codigoOperador);
	    bool inicioSesion(int codigoOperador, string password);
      void receiveAlarms(StringArr alarms);
    }

    interface ServicioAbastecimiento {
	    void abastecer(int codMaquina, int tipoAlarma);
    }

    enum Moneda{
      CIEN, DOCIENTOS, QUINIENTOS
    }
  


    interface AlarmaService{
      void recibirNotificacionEscasezIngredientes(string iDing, int idMaq);
      void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq);
      void recibirNotificacionEscasezSuministro(string idSumin, int idMaq);
      void recibirNotificacionAbastesimiento(int idMaq, string idInsumo, int cantidad );
      void recibirNotificacionMalFuncionamiento(int idMaq, string descri);

    }

    interface VentaService{
      void registrarVenta(int codMaq, StringArr ventas);
    }

    interface RecetaService{
      StringArr consultarIngredientes();
	    StringArr consultarRecetas();
      StringArr consultarProductos();
      void definirProducto(string nombre, int precio, MapStrInt ingredientes);

	    void borrarReceta(int cod);
	    void definirRecetaIngrediente(int idReceta, int idIngrediente,int valor);
	    string registrarReceta(string nombre, int precio);
	    string registrarIngrediente(string nombre);
    }

    interface logisticCommunication {
      void registerLogisticCenter(ServicioComLogistica* scl);
      void sendAlarmsToLogistic();
    }

        interface WarehouseService {
      void receiveOrder(StringArr orders);
    }

    interface WarehouseCommunication {
      void registerWarehouse(WarehouseService* warehouseService);
      void sendOrdersToWarehouse();
    }
}

module pubsub {
  sequence<string> StringArr; 

  interface Observer{
    void update(StringArr recetasNuevas);
  }

  interface Observable{
    void attach(Observer* o);
    void detach(Observer* o);
    void notifyObservers();
  }

}

module RM {
    interface ReliableMessage{
      void notifyAlarm(string alarma);
    }
}



