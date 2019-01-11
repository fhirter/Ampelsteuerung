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
    LinkedList<TrafficLight> model = new LinkedList<TrafficLight>();


    public void addModelsForTesting(TrafficLight model)
    {
        this.model.add(model);
    }


    public void setRed()
    {
        for(TrafficLight i : model)
        {i.setRed();}
    }


    public void setGreen()
    {
        for(TrafficLight i : model)
        {i.setGreen();}
    }


    public void setYellow()
    {
        for(TrafficLight i : model)
        {i.setYellowFlash();}
    }


    public void setDark()
    {
        for(TrafficLight i : model)
        {i.setDark();}
    }


    public void setAllOn()
    {
        for(TrafficLight i : model)
        {i.setSIMULATION();}
    }

}




