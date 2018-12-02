/**
 * Class DrivewayModel: Data model for the crossroad
 *
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class DrivewayModel extends Obserable {

    private boolean pedestrianStripes = false;
    private boolean bicyclePath = false;
    private boolean publicTrafficRail = false;
    private boolean carDriveway = false;

    public boolean getCarDriveway() {
        return carDriveway;
    }

    public void setCarDriveway(boolean carDriveway) {
        this.carDriveway = carDriveway;
    }


    private DrivewayType drivewayType;

/*
    public DrivewayModel(DrivewayType drivewayType ) {
        this.drivewayType = drivewayType;
        }
*/

    public DrivewayModel(boolean pedestrianStripes, boolean bicyclePath, boolean publicTrafficRail, boolean carDriveway) {

        this.pedestrianStripes = pedestrianStripes;
        this.bicyclePath = bicyclePath;
        this.publicTrafficRail = publicTrafficRail;
        this.carDriveway = carDriveway;
    }

    public void setPedestrianStripes(boolean PedestrianStripes) {
            this.pedestrianStripes = PedestrianStripes;
            notifyObservers();
    }


    public boolean getPedestrianStripes() {
        return pedestrianStripes;
    }

    public void setBicyclePatch (boolean BicyclePath){
        this.bicyclePath = BicyclePath;
        notifyObservers();
    }

    public boolean getBicyclePatch (){
        return bicyclePath;
    }

    public void setPublicTrafficRail (boolean PublicTrafficRail){
        this.publicTrafficRail = PublicTrafficRail;
        notifyObservers();
    }

    public boolean getPublicTrafficRail () {
        return publicTrafficRail;
    }

}




