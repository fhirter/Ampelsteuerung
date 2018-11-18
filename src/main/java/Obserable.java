import java.util.LinkedList;
import java.util.List;

public abstract class Obserable  {

    List<Observer> observers = new LinkedList<>();


    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(String string, Object obj){
        for (Observer i : observers) {
         i.update(string, obj);
        }
    }

    public Object notifyObserversWithResponse(String string){
        for (Observer i : observers) {
            return i.update(string);
        }
        return 0;
    }


}
