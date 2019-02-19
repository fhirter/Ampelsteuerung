import javafx.animation.AnimationTimer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MovedElements
{

    private CrossroadController crossroadController;
    private List<VehicleModel> vehicleModelList = new LinkedList<>();
    private int counterTypeOfMovedElements = 0;

    public MovedElements(CrossroadController crossroadController, int countOfMovedElements)
    {
        this.crossroadController = crossroadController;
        generateMovedElements(countOfMovedElements);
    }


    public void generateMovedElements(int count)
    {
        for(int i = 0; i < count; i++)
        {
            //todo: Auskommentiert
            VehicleModel vehicleModel = new VehicleModel(getAllTypesOfMovedElement(), getRandomStartpoint());
            VehicleController vehicleController = new VehicleController(vehicleModel);
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
        rndNumber = random.nextInt(FixPoint.values().length);
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
