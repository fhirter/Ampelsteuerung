package crossroad;

import traffic_lights.TrafficLight;
import util.Observable;

/**
 *
 * @autor Class NIN
 * @date 14.11.2018
 */
public class Road extends Observable {
    private boolean hasPedestrianStripes;
    private boolean hasBicycleLane;


    private TrafficLight trafficLight;
    private boolean isVisible = true;

    public Road() {
        trafficLight = new TrafficLight();
    }

    public void setVisibility(boolean visible) {
        this.isVisible = visible;
        notifyObservers();
    }

    public boolean hasPedestrianStripes() {
        return hasPedestrianStripes;
    }

    public boolean hasBicycleLane() {
        return hasBicycleLane;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setHasPedestrianStripes(boolean hasPedestrianStripes) {
        this.hasPedestrianStripes = hasPedestrianStripes;
        notifyObservers();
    }

    public void setHasBicycleLane(boolean hasBicycleLane) {
        this.hasBicycleLane = hasBicycleLane;
        notifyObservers();
    }

    public TrafficLight getTrafficLightModelCar() {
        return trafficLight;
    }
}








