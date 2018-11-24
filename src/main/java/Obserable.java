import java.util.LinkedList;
import java.util.List;

abstract class Obserable  {

    List<Observer> observers = new LinkedList<>();


    protected void addObserver(Observer observer){
        observers.add(observer);
    }

    protected void notifyObservers(){
        for (Observer i : observers) {
         i.update();
        }
    }
}
