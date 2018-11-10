package com.company;

public class Driveway {

    private boolean PedestrianStripes;
    private boolean TrafficLightVehicle;
    private boolean TrafficLightPedestrian;


    public void SetPedestrainStripe (boolean PedestrianStripes) {
            this.PedestrianStripes = PedestrianStripes;
    }

    public boolean GetPedestrianStripes() {
        return PedestrianStripes;
    }

}
