import java.util.LinkedList;

/*****************
 *
 *  Innerhalb der Methoden werde alle Objekte gemaess LinkedList model aufgerufen.
 *
 *  Da es sich lediglich um eine Testprozedure handelt, wurde kein globaler Observer implementiert.
 *
 *
 */
public class TrafficLightTestModel
{
    LinkedList<TrafficLightModel> model = new LinkedList<TrafficLightModel>();


    public void addModelsForTesting(TrafficLightModel model)
    {
        this.model.add(model);
    }


    public void setRed()
    {
        for(TrafficLightModel i : model)
        {i.setRed();}
    }


    public void setGreen()
    {
        for(TrafficLightModel i : model)
        {i.setGreen();}
    }


    public void setYellow()
    {
        for(TrafficLightModel i : model)
        {i.setYellowFlash();}
    }


    public void setDark()
    {
        model.get(0).setRed();
//        for(TrafficLightModel i : model)
//        {i.setDark();}
    }


    public void setAllOn()
    {
        model.get(1).setRed();
//        for(TrafficLightModel i : model)
//        {i.setSIMULATION();}
    }

}




