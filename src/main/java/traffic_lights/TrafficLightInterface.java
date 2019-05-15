package traffic_lights;

public interface TrafficLightInterface {
    TrafficLightState getState();

    void setState(TrafficLightState trafficLightState);

    void setRed();

    void setGreen();

    void setYellowFlash();

    void setDark();
}
