package traffic_lights;

import util.Observable;

public interface TrafficLightInterface extends Observable {
    TrafficLightState getState();
    void setState(TrafficLightState state);
}
