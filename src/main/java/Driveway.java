/**
 * Class Driveway: Data model for the crossroad
 *
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class Driveway {

    private boolean PedestrianStripes;
    private boolean BicyclePath;
    private boolean PublicTrafficRail;

    public Driveway(boolean PedestrianStripes, boolean BicyclePath, boolean PublicTrafficRail) {
    }


    public void SetPedestrainStripes (boolean PedestrianStripes) {
            this.PedestrianStripes = PedestrianStripes;
    }

    public boolean GetPedestrianStripes() {
        return PedestrianStripes;
    }

    public void SetBicyclePatch (boolean BicyclePath){
        this.BicyclePath = BicyclePath;
    }

    public boolean GetBicyclePatch (){
        return BicyclePath;
    }

    public void SetPublicTrafficRail (boolean PublicTrafficRail){
        this.PublicTrafficRail = PublicTrafficRail;
    }

    public boolean GetPublicTrafficRail () {
        return PublicTrafficRail;
    }

}
