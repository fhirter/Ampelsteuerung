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

    private boolean PedestrianStripes;
    private boolean BicyclePath;
    private boolean PublicTrafficRail;

    public void setPedestrainStripes (boolean PedestrianStripes) {
            this.PedestrianStripes = PedestrianStripes;
    }

    public boolean getPedestrianStripes() {
        return PedestrianStripes;
    }

    public void setBicyclePatch (boolean BicyclePath){
        this.BicyclePath = BicyclePath;
    }

    public boolean getBicyclePatch (){
        return BicyclePath;
    }

    public void setPublicTrafficRail (boolean PublicTrafficRail){
        this.PublicTrafficRail = PublicTrafficRail;
    }

    public boolean getPublicTrafficRail () {
        return PublicTrafficRail;
    }

}
