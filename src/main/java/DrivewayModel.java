/**
 * Class DrivewayModel: Data model for the crossroad
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class DrivewayModel extends Observable {

    private boolean pedestrianStripes = false;
    private boolean bicyclePath = false;
    private boolean publicTrafficRail = false;
    private boolean carDriveway = false;


    /**
     * Constructor DrivewayModel: Constructor for the DrivewayModel
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   30.11.2018
     */
    public DrivewayModel(boolean pedestrianStripes, boolean bicyclePath, boolean publicTrafficRail, boolean carDriveway) {

        this.pedestrianStripes = pedestrianStripes;
        this.bicyclePath = bicyclePath;
        this.publicTrafficRail = publicTrafficRail;
        this.carDriveway = carDriveway;

    }

    /**
     * Method setPedestrianStripes: Setter for pedestrian Stripes
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public void setPedestrianStripes(boolean PedestrianStripes) {
            this.pedestrianStripes = PedestrianStripes;
            notifyObservers();
    }
    /**
     * Method getCarDriveway: Getter for Car Driveway
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */

    public boolean getPedestrianStripes() {
        return pedestrianStripes;
    }
    /**
     * Method setBicyclePatch: Setter for bicyclePath
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public boolean getCarDriveway() {
        return carDriveway;
    }
    /**
     * Method setCarDriveway: Setter for Car Driveway
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public void setCarDriveway(boolean carDriveway) {
        this.carDriveway = carDriveway;
    }
    /**
     * Method getPedestrianStripes: Getter for Pedestrian Stripes
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public void setBicyclePath (boolean BicyclePath){
        this.bicyclePath = BicyclePath;
        notifyObservers();
    }
    /**
     * Method getBicyclePatch: Getter for bicyclePath
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public boolean getBicyclePatch (){
        return bicyclePath;
    }
    /**
     * Method setPublicTrafficRail: Setter for Public Trafficrrail
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public void setPublicTrafficRail (boolean PublicTrafficRail){
        this.publicTrafficRail = PublicTrafficRail;
        notifyObservers();
    }
    /**
     * Method getPublicTrafficRail: Getter for Public Trafficrail
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public boolean getPublicTrafficRail () {
        return publicTrafficRail;
    }

}




