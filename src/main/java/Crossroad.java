import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.List;

public class Crossroad extends Observable {

    private final String[] algorithms = {"Algorithm A",
            "Algorithm B",
            "Algorithm C",
            "Algorithm D",
            "Algorithm E"};
    private final Point2D referencePoint;

    private Integer numberOfDriveways = 4;
    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();

    private Area turningArea;

    private class Area {
        private int size;
        private Point2D center;


        public Area(int size, Point2D center){
            this.size = size;
            this.center = center;
        }; // just use default values

        public boolean isInside(Position position) {
            if(position.x > (center.getX()-size/2) && position.x < (center.getX()+size/2) ) {
                if(position.y > (center.getY()-size/2) && position.y < (center.getY()+size/2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Crossroad: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     * @param ref
     */

    public Crossroad(Point2D ref)
    {
        this.referencePoint = ref;
        turningArea = new Area(180, referencePoint);


        /* Loop to create all driveways */
        for (int i = 0; i < 4; i++) {
            DrivewayRoute drivewayRoute = new DrivewayRoute();
            drivewayRoutes.add(drivewayRoute);
        }
    }

    /**
     * CrossRoad: get the algorithms
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public String[] getAlgorithms()
    {
        return algorithms;
    }
    /**
     * Crossroad: get Max of DrivewayRoutes
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public Integer getNumberOfDriveways() {
        return numberOfDriveways;
    }

    /**
     * Crossroad: get the Object DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public List<DrivewayRoute> getDrivewayRoutes()
    {
        return drivewayRoutes;
    }

    /**
     * Crossroad: set number of Driveways
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg centerPaneModel (Object of the Center)
     */
    public void setNumberOfDriveways(Integer numberOfDriveways){
        this.numberOfDriveways = numberOfDriveways;
    }

    public boolean canITurn(Position position) {
        if(turningArea.isInside(position)) {
            return true;
        }
        return false;
    }

}


