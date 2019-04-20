import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleBinaryOperator;

public class Vehicles
{

    private final Crossroad crossroad;
    private CrossroadController crossroadController;
    private List<Vehicle> vehicles = new LinkedList<>();
    private int counterTypeOfMovedElements = 0;

    public Vehicles(Crossroad crossroad, CrossroadController crossroadController, int vehicleCount)
    {
        this.crossroad = crossroad;
        this.crossroadController = crossroadController;
        generateVehicles(vehicleCount);
    }


    public void generateVehicles(int count)
    {
        for(int i = 0; i < count; i++)
        {
            Vehicle vehicle = new Vehicle(crossroad);
            VehicleController vehicleController = new VehicleController(vehicle);
            vehicle.addObserver(vehicleController);

            crossroadController.getChildren().add(vehicleController);
            vehicles.add(vehicle);
        }
    }




    public void calculatePositions(Double secondsElapsedCapped)
    {
        for (int i = 0; i < vehicles.size(); i++)
        {
            vehicles.get(i).setNewPosition(secondsElapsedCapped);
        }
    }

    public boolean isDestinationValid(Direction destination) {
        if(crossroad.getRoadCount() == 3 && destination == Direction.SOUTH) {
            return false;
        } else {
            return true;
        }
    }
}
