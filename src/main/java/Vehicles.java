import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Vehicles
{

    private final Crossroad crossroad;
    private CrossroadController crossroadController;
    private List<VehicleModel> vehicles = new LinkedList<>();
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
            VehicleModel vehicle = new VehicleModel(crossroad, getAllTypesOfMovedElement(), getRandomStartpoint());
            VehicleController vehicleController = new VehicleController(vehicle);
            vehicle.addObserver(vehicleController);

            crossroadController.getChildren().add(vehicleController);
            vehicles.add(vehicle);
        }
    }


    private VehicleType getAllTypesOfMovedElement()
    {
        counterTypeOfMovedElements ++;
        if(counterTypeOfMovedElements >= VehicleType.values().length){
            counterTypeOfMovedElements = 0;}
        return VehicleType.values()[counterTypeOfMovedElements];
    }


    public Direction getRandomStartpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(Direction.values().length);
        return Direction.values()[rndNumber];
    }


    public void calculatePositions(float secondsElapsedCapped)
    {
        for (int i = 0; i < vehicles.size(); i++)
        {
            vehicles.get(i).setNewPosition(secondsElapsedCapped);
        }
    }
}
