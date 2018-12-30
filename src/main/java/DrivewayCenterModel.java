import java.util.HashMap;


public class DrivewayCenterModel
{
    private HashMap<String, String> allgorithmusAndTypeFromCrossroad;

    /**
     * DrivewayCenterModel(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     * @arg     HashMap<String, HashMap>: (HashMap where the selected settings (only allgorithmus and type) from the crossroad are stored.)
     */
    public DrivewayCenterModel(HashMap<String, HashMap> settingsForCrossroad)
    {
        allgorithmusAndTypeFromCrossroad = settingsForCrossroad.get("allgorithmusAndType");
    }


    /**
     * getAllgorithmusAndTypeFromCrossroad: Returns the selected settings (allgorithmus and type) from the crossroad
     * (is called during initialisation from DrivewayCenterController)
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    28.12.2018
     * @return  HashMap<String, HashMap>: (HashMap where the selected settings from the crossroad are stored.)
     */
    public HashMap<String, String> getAllgorithmusAndTypeFromCrossroad()
    {
        return allgorithmusAndTypeFromCrossroad;
    }
}




