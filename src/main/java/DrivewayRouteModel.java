import java.util.HashMap;

/**
 * Class DrivewayRouteModel: Data model for the crossroad
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class DrivewayRouteModel extends Observable
{
    private HashMap<String, Boolean> settingsFromCheckBoxes;

    /**
     * Constructor DrivewayRouteModel: Constructor for the DrivewayModel
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   30.11.2018
     */
    public DrivewayRouteModel(HashMap<String, HashMap> settingsForCrossroad)
    {
        this.settingsFromCheckBoxes = settingsForCrossroad.get("checkboxes");
    }


    public HashMap<String, Boolean> getStateFromChckboxes()
    {
        return this.settingsFromCheckBoxes;
    }
}





