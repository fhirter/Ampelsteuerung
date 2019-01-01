import java.util.HashMap;

public class Algorithmus
{
    private HashMap<String, TrafficLightModel> crossroadControlMap;

    public Algorithmus(HashMap<String, TrafficLightModel> crossroadControlMap)
    {
        this.crossroadControlMap = crossroadControlMap;
    }


    public void testfunktionAmpelspiel(HashMap<String, HashMap> settingsForCrossroad)
    {
        switch(settingsForCrossroad.get("allgorithmusAndType").get("allgorithmusTypes").toString())
        {
            case "Algorithm A":
            {
                crossroadControlMap.get("North_CAR").setYellowFlash();
                crossroadControlMap.get("North_PEDESTRIAN").setYellowFlash();
                crossroadControlMap.get("West_CAR").setYellowFlash();
                crossroadControlMap.get("West_PEDESTRIAN").setYellowFlash();
                crossroadControlMap.get("East_CAR").setYellowFlash();
                crossroadControlMap.get("East_PEDESTRIAN").setYellowFlash();
                crossroadControlMap.get("South_CAR").setYellowFlash();
                crossroadControlMap.get("South_PEDESTRIAN").setYellowFlash();
                break;
            }
            case "Algorithm B":
            {
                crossroadControlMap.get("North_CAR").setSIMULATION();
                crossroadControlMap.get("North_PEDESTRIAN").setSIMULATION();
                crossroadControlMap.get("West_CAR").setSIMULATION();
                crossroadControlMap.get("West_PEDESTRIAN").setSIMULATION();
                crossroadControlMap.get("East_CAR").setSIMULATION();
                crossroadControlMap.get("East_PEDESTRIAN").setSIMULATION();
                crossroadControlMap.get("South_CAR").setSIMULATION();
                crossroadControlMap.get("South_PEDESTRIAN").setSIMULATION();
                break;
            }
            case "Algorithm C":
            {
                crossroadControlMap.get("North_CAR").setGreen();
                crossroadControlMap.get("North_PEDESTRIAN").setRed();
                crossroadControlMap.get("West_CAR").setGreen();
                crossroadControlMap.get("West_PEDESTRIAN").setRed();
                crossroadControlMap.get("East_CAR").setGreen();
                crossroadControlMap.get("East_PEDESTRIAN").setRed();
                crossroadControlMap.get("South_CAR").setGreen();
                crossroadControlMap.get("South_PEDESTRIAN").setRed();
                break;
            }
            case "Algorithm D":
            {
                crossroadControlMap.get("North_CAR").setRed();
                crossroadControlMap.get("North_PEDESTRIAN").setGreen();
                crossroadControlMap.get("West_CAR").setRed();
                crossroadControlMap.get("West_PEDESTRIAN").setGreen();
                crossroadControlMap.get("East_CAR").setRed();
                crossroadControlMap.get("East_PEDESTRIAN").setGreen();
                crossroadControlMap.get("South_CAR").setRed();
                crossroadControlMap.get("South_PEDESTRIAN").setGreen();
                break;
            }
            case "Algorithm E":
            {
                crossroadControlMap.get("North_CAR").setDark();
                crossroadControlMap.get("North_PEDESTRIAN").setDark();
                crossroadControlMap.get("West_CAR").setSIMULATION();
                crossroadControlMap.get("West_PEDESTRIAN").setSIMULATION();
                crossroadControlMap.get("East_CAR").setYellowFlash();
                crossroadControlMap.get("East_PEDESTRIAN").setYellowFlash();
                crossroadControlMap.get("South_CAR").setGreen();
                crossroadControlMap.get("South_PEDESTRIAN").setRed();
                break;
            }
        }
    }
}
