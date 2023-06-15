package bodega;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.zeroc.Ice.Current;

public class WareHouseImpl implements servicios.WarehouseService{

    private List<String> orders;

    public WareHouseImpl() {
        orders = new ArrayList<>();
    }

    @Override
    public void receiveOrder(String[] orders, Current current) {
        this.orders = Arrays.asList(orders);

        System.out.println("Warehouse received orders: ");
        for (String order : orders) {
            System.out.println(order);
            System.out.println("--------------------------");
        }

    }
    
}
