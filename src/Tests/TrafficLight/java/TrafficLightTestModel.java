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


    public void setModelsForTesting(TrafficLightModel model)
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
        {i.setYellow();}
    }


    public void setDark()
    {
        for(TrafficLightModel i : model)
        {i.setDark();}
    }


    public void setAllOn()
    {
        for(TrafficLightModel i : model)
        {i.setAllOn();}
    }

}




