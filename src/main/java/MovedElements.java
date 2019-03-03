import javafx.animation.AnimationTimer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MovedElements
{
    private Crossroad crossroad;
    private CrossroadController crossroadController;
    private List<VehicleModel> vehicleModelList = new LinkedList<>();
    private List<VehicleController> vehicleControllerList = new LinkedList<>();
    private int numberOfCrossroad;
    private int counterTypeOfMovedElements = 0;

    public MovedElements(Crossroad crossroad)
    {
        this.crossroad = crossroad;
        GameLoop gameLoop = new GameLoop(this);
        gameLoop.start();
    }


    public void startNewConfiguration(CrossroadController crossroadController, int countOfMovedElements)
    {
        this.crossroadController = crossroadController;
        this.numberOfCrossroad = crossroad.getNumberOfDriveways();
        generateMovedElements(countOfMovedElements);
    }


    public void generateMovedElements(int count)
    {
        if(vehicleModelList.size() != 0)
        {
            vehicleModelList.clear();
            for (int i = crossroadController.getChildren().size()-1; i > 0; i--)
            {
                if(vehicleControllerList.indexOf(crossroadController.getChildren().get(i)) != -1)
                {
                    crossroadController.getChildren().remove(i);
                }
            }
            vehicleControllerList.clear();
        }

        for(int i = 0; i < count; i++)
        {
            VehicleModel vehicleModel = new VehicleModel(crossroad, getAllTypesOfMovedElement(), getRandomStartpoint(), numberOfCrossroad);
            VehicleController vehicleController = new VehicleController(vehicleModel);
            vehicleControllerList.add(vehicleController);
            vehicleModel.addObserver(vehicleController);

            crossroadController.getChildren().add(vehicleController);
            vehicleModelList.add(vehicleModel);
        }
    }


    private MovedElement getAllTypesOfMovedElement()
    {
        counterTypeOfMovedElements ++;
        if(counterTypeOfMovedElements >= MovedElement.values().length){
            counterTypeOfMovedElements = 0;}
        return MovedElement.values()[counterTypeOfMovedElements];
    }


    public FixPoint getRandomStartpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(numberOfCrossroad);
        return FixPoint.values()[rndNumber];
    }


    public void calculatePositions(float secondsElapsedCapped)
    {
        for (int i = 0; i < vehicleModelList.size(); i++)
        {
            vehicleModelList.get(i).setNewPosition(secondsElapsedCapped);
        }
    }
}
