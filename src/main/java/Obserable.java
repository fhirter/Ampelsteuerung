import java.util.LinkedList;
import java.util.List;

abstract class Obserable  {

    private final List<Observer> observers = new LinkedList<>();


    public void addObserver(Observer observer){
        observers.add(observer);
    }

    void notifyObservers(String string, Object obj){
        for (Observer i : observers) {
         i.update(string, obj);
        }
    }

    Object notifyObserversWithResponse(String string){
        for (Observer i : observers) {
            return i.update(string);
        }
        return 0;
    }


}
