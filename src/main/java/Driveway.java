/**
 * Class Driveway: Data model for the crossroad
 *
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

class Driveway {

    private boolean pedestrianStripes;
    private boolean bicyclePath;
    private boolean publicTrafficRail;
    private boolean driveable;

    public void setPedestrainStripes (boolean PedestrianStripes) {
            this.pedestrianStripes = PedestrianStripes;
    }

    public boolean getPedestrianStripes() {
        return pedestrianStripes;
    }

    public void setBicyclePatch (boolean BicyclePath){
        this.bicyclePath = BicyclePath;
    }

    public boolean getBicyclePatch (){
        return bicyclePath;
    }

    public void setPublicTrafficRail (boolean PublicTrafficRail){
        this.publicTrafficRail = PublicTrafficRail;
    }

    public boolean getPublicTrafficRail () {
        return publicTrafficRail;
    }

    public void setdrivable (boolean driveable) {
        this.driveable = driveable;
    }

    public boolean getdivable () {
        return driveable;
    }
}
